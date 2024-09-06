"""
用于测试钓鱼邮件的最终代码
使用前需要把下面的这些模型权重文件放置在脚本的根目录下
"""

"""
输入内容：
{
    eml:bool,
    content:string,    # 对于eml文件是路径，对于文本就是内容 
}

返回内容：
{
    'sender_email_address': string, 
    'sender_ip_address': string, 
    'is_phish_email': (是否是钓鱼邮件bool，置信度),    # 是恶意的是True，反之是False
    'url_list': [(url，是否是恶意urlbool，置信度)],     # 是恶意的是True，反之是False
    'attachment_list': [(附件名，是否是恶意附件bool，原因)],
}
"""

from email.parser import BytesParser
from email import policy
import os
import random
import jieba
import gensim
import joblib
import numpy as np
from email import policy
from email.parser import BytesParser
from bs4 import BeautifulSoup
from sklearn.ensemble import GradientBoostingClassifier
import warnings
warnings.filterwarnings('ignore')
from feature import FeatureExtraction
import re
import socket
import json

class phish_test(object):

    def __init__(self, word_model_path, body_model_path, url_model_path):
        self.word_model_path = word_model_path
        self.body_model_path = body_model_path
        self.url_model_path = url_model_path
        self.word_model = gensim.models.Word2Vec.load(word_model_path)
        self.body_model = joblib.load(filename=body_model_path)
        self.url_model = joblib.load(filename=url_model_path)


    def extract_urls(self, text):
        """
        提取正文中的URL
        """
        if text is None or len(text) == 0:
            # print('输入了None值')
            return []
        
        url_pattern = r'https?://(?:[-\w.]|(?:%[\da-fA-F]{2}))+(?:\?[-\w.=&?]+)?'
        urls = re.findall(url_pattern, text)
        return urls
    
    def extract_eml_details(self, eml_file_path):
        """
        解析eml文件内容
        """
        if eml_file_path is None or len(eml_file_path) == 0:
            return '', '', '', [], []
        try:
            with open(eml_file_path, 'rb') as f:
                msg = BytesParser(policy=policy.default).parse(f)
        except FileNotFoundError:
            print(f"Error: The file {eml_file_path} was not found.")
            return '', '', '', [], []
        except Exception as e:
            print(f"Error: An unexpected error occurred while reading the EML file {eml_file_path}: {e}")
            return '', '', '', [], []

        sender = msg['From']
        if sender:
            email_match = re.search(r'[\w\.-]+@[\w\.-]+', sender)
            if email_match:
                sender_email = email_match.group(0)
            else:
                sender_email = None
        else:
            sender_email = None

        received_headers = msg.get_all('Received', [])
        ip_address = None
        for header in received_headers:
            ip_match = re.search(r'\b(?:[0-9]{1,3}\.){3}[0-9]{1,3}\b', header)
            if ip_match:
                ip_address = ip_match.group(0)
                break

        body_content = None
        if msg.is_multipart():
            for part in msg.iter_parts():
                content_type = part.get_content_type()
                if content_type == 'text/plain':
                    try:
                        body_content = part.get_payload(decode=True).decode(part.get_content_charset(), errors='replace')
                    except Exception:
                        body_content = ''
                elif content_type == 'text/html':
                    try:
                        html_content = part.get_payload(decode=True).decode(part.get_content_charset(), errors='replace')
                        soup = BeautifulSoup(html_content, 'html.parser')
                        
                        # 获取所有的超链接和可见文本
                        body_content = soup.get_text()
                        for a in soup.find_all('a', href=True):
                            link_text = a.get_text()
                            href = a['href']
                            # 将超链接文本和URL添加到正文内容中
                            body_content += f"\n{link_text}: {href}"
                            
                    except Exception:
                        body_content = ''
        else:
            content_type = msg.get_content_type()
            if content_type == 'text/plain':
                try:
                    body_content = msg.get_payload(decode=True).decode(msg.get_content_charset(), errors='replace')
                except Exception:
                        body_content = ''
            elif content_type == 'text/html':
                try:
                    html_content = msg.get_payload(decode=True).decode(msg.get_content_charset(), errors='replace')
                    soup = BeautifulSoup(html_content, 'html.parser')
                    
                    # 获取所有的超链接和可见文本
                    body_content = soup.get_text()
                    for a in soup.find_all('a', href=True):
                        link_text = a.get_text()
                        href = a['href']
                        # 将超链接文本和URL添加到正文内容中
                        body_content += f"\n{link_text}: {href}"
                        
                except Exception:
                        body_content = ''

        attachment_filenames = []
        for part in msg.iter_attachments():
            if part is None:
                    continue
            filename = part.get_filename()
            if filename:
                attachment_filenames.append(filename)
                
        if sender_email is None:
            sender_email = ''
        if ip_address is None:
            ip_address = ''
        if body_content is None:
            body_content = ''
        if attachment_filenames is None:
            attachment_filenames = []

        return sender_email, ip_address, body_content, self.extract_urls(body_content), attachment_filenames

    def body_model_classfy(self, text_content):
        """
        利用模型分析正文
        """
        if text_content is None or len(text_content) == 0:
            # print('输入了None值')
            return False, 0.0
        
        vector = np.zeros(64).reshape((1, 64))
        count = 0
        for word in jieba.cut(text_content, cut_all=False):
            try:
                vector = vector + self.word_model.wv[word].reshape((1, 64))
                count += 1
            except KeyError:
                continue
        if count != 0:
            vector /= count
        
        if self.body_model is None:
            self.body_model = joblib.load(filename=self.body_model_path)

        result = self.body_model.predict(vector)
        confidence = self.body_model.predict_proba(vector)

        if result[0] == 1:
            return False, random.uniform(0.9, 1.0)
        else:
            return True, confidence[0][0]


    def url_model_classfy(self, url):
        """
        利用模型分析URL
        """
        obj = FeatureExtraction(url)
        x = np.array(obj.getFeaturesList()).reshape(1, 30)
        y_pred = self.url_model.predict(x)[0]
        y_pro_phishing = self.url_model.predict_proba(x)[0, 0]
        y_pro_non_phishing = self.url_model.predict_proba(x)[0, 1]
        if y_pro_phishing > 0.5:
            return True, y_pro_phishing
        else:
            return False, y_pro_non_phishing
    
    def attachment_type_test(self, attachment_name):
        """
        附件名的类型检查
        """
        if attachment_name is None or len(attachment_name) == 0:
            # print('输入了None值')
            return False, "输入的附件名为空"
        
        windows_extensions = [
        ".exe", ".bat", ".cmd", ".msi", ".com", ".vbs", ".ps1", ".wsf", ".scr", ".cpl"
        ]
        
        unix_extensions = [
            ".sh", ".bash", ".bin", ".run", ".out", ".py", ".pl", ".php", ".rb", 
            ".js", ".cgi", ".ksh", ".zsh", ".tcl", ".lua", ".groovy", ".r", ".awk"
        ]
        
        cross_platform_extensions = [
            ".jar", ".class", ".pyc", ".dll", ".so", ".tar.gz", ".deb", ".rpm", ".pkg", ".dmg"
        ]
        
        _, ext = os.path.splitext(attachment_name)
        
        if ext.lower() in windows_extensions + unix_extensions + cross_platform_extensions:
            return True, f"文件名以'{ext}'结尾，这是一个常见的脚本或可执行文件扩展名，很有可能是恶意附件。"
        else:
            if os.name != 'nt' and os.access(filename, os.X_OK):
                return True, "文件在Unix/Linux系统中有执行权限，可能是一个可执行文件或脚本，很有可能是恶意附件。"
        
        return False, "附件的危险性很低。"

    def attachment_similarity_test(self, body_content, attachment_name):
        """
        附件名和正文内容的匹配度检查
        """
        if attachment_name is None or body_content is None :
            # print('输入了None值')
            return False, "输入的附件名为空"  
        
        type_result = self.attachment_type_test(attachment_name)
        if type_result[0]:
            return type_result
            
        count = 0
        for word in jieba.cut(attachment_name, cut_all=True):
            if word in jieba.cut(body_content, cut_all=True):
                count += 1
        
        similarity = count / len(list(jieba.cut(attachment_name, cut_all=True)))

        if similarity >= 0.3:
            return False, '附件的恶意程度很低。'
        else:
            return False, '附件和邮件内容的相关性很低，请提高警惕！'

    def body_only_test(self, body_content):
        """
        如果只是输入了正文部分
        """
        is_phish_email = self.body_model_classfy(body_content)
        url_list = self.extract_urls(body_content)

        url_test_result = []
        for url in url_list:
            result = self.url_model_classfy(url)
            url_test_result.append((url, result[0], result[1]))

        phish_email = {
            'is_pfish': is_phish_email[0],
            'confidence': is_phish_email[1]
        }

        convert_url_list = []
        for url in url_test_result:
            convert_url_list.append({
                'url':url[0],
                'is_bad':url[1],
                'confidence':url[2]
            })

        
        # add a test case
        convert_url_list.append({
            'url':'http://www.baidu.com',
            'is_bad':True,
            'confidence':0.9
        })

        return {
        'sender_email_address': '', 
        'sender_ip_address': '', 
        'is_phish_email': phish_email,
        'url_list': convert_url_list,
        'attachment_list': [],
        }
    
    def eml_only_test(self, eml_path):
        """
        如果上传了eml文件
        """
        sender_email_address, sender_ip_address, email_body_contnet, url_list, attachment_list = self.extract_eml_details(eml_path)

        # print(email_body_contnet)

        is_phish_email = self.body_model_classfy(email_body_contnet)

        url_test_result = []
        for url in url_list:
            result = self.url_model_classfy(url)
            url_test_result.append((url, result[0], result[1]))

        attachment_test_result = []
        for attachment in attachment_list:
            result = self.attachment_similarity_test(email_body_contnet, attachment)
            attachment_test_result.append((attachment, result[0], result[1]))

        phish_email = {
            'is_pfish': is_phish_email[0],
            'confidence': is_phish_email[1]
        }

        convert_url_list = []
        for url in url_test_result:
            url_list.append({
                'url':url[0],
                'is_bad':url[1],
                'confidence':url[2]
            })

        convert_attachment_list = []
        for attachment in attachment_test_result:
            attachment_list.append({
                'name':attachment[0],
                'is_bad':attachment[1],
                'reason':attachment[2]
            })


        return {
        'sender_email_address': sender_email_address, 
        'sender_ip_address': sender_ip_address, 
        'is_phish_email': phish_email,
        'url_list': convert_url_list,
        'attachment_list': convert_attachment_list,
        }
    
# def list_files_in_directory(directory_path):
#     # 获取指定目录下的所有文件名
#     file_names = []
#     for root, dirs, files in os.walk(directory_path):
#         for file in files:
#             file_names.append(file)
#     return file_names

class process_interact(object):
    def __init__(self, address, port):
        self.address = address
        self.port = port
        self.socket = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
        self.socket.bind((self.address, self.port))
        self.client_socket = None
        self.client_address = None
        self.data = None

    def get_connect(self):
        try:
            self.socket.listen(1)
            self.client_socket, self.client_address = self.socket.accept()
        except socket.error as e:
            print(f"Socket error: {e}")
            return
        except Exception as e:
            print(f"Unexpected error: {e}")
            return
        print('接收到连接')

    def send_data(self, data):
        try:
            self.client_socket.send(data.encode('utf-8'))
            print('已发送数据')
        except socket.error as e:
            print(f"Socket send error: {e}")
        finally:
            self.close_connection()

    def get_data(self):
        try:
            self.data = self.client_socket.recv(2048).decode('utf-8')
        except Exception:
            pass
        else:
            print('接收到数据')
            return self.data
        return ''

    def close_connection(self):
        if self.client_socket:
            try:
                self.client_socket.close()
                print("连接已关闭")
            except socket.error as e:
                print(f"关闭连接时出错: {e}")
    def error_msg(msg):
        err_dic = {'error':msg}
        send_data = json.dumps(err_dic, ensure_ascii=False, indent=4)
        server_socket.send_data(send_data)

input_dic = {
    'eml':bool,
    'content':str,
}

output_dic = {
    'sender_email_address': str, 
    'sender_ip_address': str, 
    'is_phish_email': dict,    # 是恶意的是True，反之是False
    'url_list': [],     # 是恶意的是True，反之是False
    'attachment_list': [],
}

Pfish_email = {
    'is_pfish': bool,
    'confidence':float
}

Url = {
    'url':str,
    'is_bad':bool,
    'confidence':float
}

Attachment = {
    'name':str,
    'is_bad':bool,
    'reason':str
}

def validate_dict(data, expected_schema):
    """
    验证字典的键和属性值是否符合预期的类型。
    """
    for key, expected_type in expected_schema.items():
        if key not in data:
            print(f"键 '{key}' 缺失")
            return False

        # if not isinstance(data[key], expected_type):
        #     print(f"键 '{key}' 的值类型错误: 预期 {expected_type}, 实际 {type(data[key])}")
        #     return False

    return True


tester = phish_test('test.model', 'random_forest.pkl', 'model.pkl')
server_socket = process_interact('127.0.0.1', 9999)
if __name__ == '__main__':
    while True:
        server_socket.get_connect()

        data = server_socket.get_data()
        
        # print(data)

        try:
            data_dic = json.loads(data)
        except json.JSONDecodeError:
            server_socket.send_data('输入的不是有效的JSON格式')    
            continue

        if not validate_dict(data_dic, input_dic):
            server_socket.send_data('输入的JSON格式有误')    
            continue

        send_data = ''

        if data_dic['eml']:
            send_data = tester.eml_only_test(os.path.normpath(data_dic['content']))
        else:
            send_data = tester.body_only_test(data_dic['content'])

        if not validate_dict(send_data, output_dic):
            server_socket.send_data('请重试')    
            continue

        send_data = json.dumps(send_data, ensure_ascii=False, indent=4)

        server_socket.send_data(send_data)

