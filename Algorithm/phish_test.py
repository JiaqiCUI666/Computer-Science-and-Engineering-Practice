"""
用于测试钓鱼邮件的最终代码
使用前需要把下面的这些模型权重文件放置在脚本的根目录下
"""

"""
输入内容：
{
    是否是eml文件:bool,
    路径/内容:string,    # 对于eml文件是路径，对于文本就是内容 
}

返回内容：
{
    'sender_email_address': string, 
    'sender_ip_address': string, 
    'is_phish_email': (是否是钓鱼邮件，置信度),
    'url_list': [(url，是否是恶意url，原因)],
    'attachment_list': [(附件名，是否是恶意附件，原因)],
}
"""

from email.parser import BytesParser
from email import policy
from email.header import decode_header
import os
import jieba
import gensim
import joblib
import numpy as np
from email import policy
from email.parser import BytesParser
from bs4 import BeautifulSoup
import re

class phish_test(object):

    def __init__(self, word_model_path, body_model_path):
        self.word_model_path = word_model_path
        self.body_model_path = body_model_path
        self.word_model = gensim.models.Word2Vec.load(word_model_path)
        self.body_model = joblib.load(filename=body_model_path)

    def extract_urls(self, text):
        """
        提取正文中的URL
        """
        if text is None or len(text) == 0:
            # print('输入了None值')
            return []
        
        url_pattern = r'https?://(?:[-\w.]|(?:%[\da-fA-F]{2}))+'
        urls = re.findall(url_pattern, text)
        return urls
    
    def extract_eml_details(self, eml_file_path):
        """
        解析eml文件内容
        """
        if eml_file_path is None or len(eml_file_path) == 0:
            # print('输入了None值')
            return '', '', '', [], []
        
        with open(eml_file_path, 'rb') as f:
            msg = BytesParser(policy=policy.default).parse(f)

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
        html_content = None
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
                        body_content = soup.get_text()
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
                    body_content = soup.get_text()
                except Exception:
                        body_content = ''

        attachment_filenames = []
        for part in msg.iter_attachments():
            if part is None:
                    continue
            filename = part.get_filename()
            if filename:
                attachment_filenames.append(filename)
                
        # return {
        #     'sender_email': sender_email,
        #     'ip_address': ip_address,
        #     'body_content': body_content,
        #     'url_list': self.extract_urls(body_content),
        #     'attachment_filenames': attachment_filenames,
        # }

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
            return True
        
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

        return result[0] == 1


    def url_model_classfy(self, url_list):
        """
        利用模型分析URL
        """
        # print('url分析尚未完成')
        return 'ss', 'sss'
    
    def attachment_type_test(self, attachment_name):
        """
        附件名的类型检查
        """
        if attachment_name is None or len(attachment_name) == 0:
            # print('输入了None值')
            return False, "输入的附件名无效！"
        
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
            return True, f"文件名以'{ext}'结尾，这是一个常见的脚本或可执行文件扩展名。"
        else:
            if os.name != 'nt' and os.access(filename, os.X_OK):
                return True, "文件在Unix/Linux系统中有执行权限，可能是一个可执行文件或脚本。"
        
        return False, "附件的危险性很低。"

    def attachment_similarity_test(self, body_content, attachment_name):
        """
        附件名和正文内容的匹配度检查
        """
        if attachment_name is None or len(attachment_name) == 0 or body_content is None or len(body_content) == 0:
            # print('输入了None值')
            return True, 1.0 
        
        count = 0
        for word in jieba.cut(attachment_name, cut_all=True):
            if word in jieba.cut(body_content, cut_all=True):
                count += 1
        
        similarity = count / len(list(jieba.cut(attachment_name, cut_all=True)))

        return similarity >= 0.5, similarity

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


        return {
        'sender_email_address': '', 
        'sender_ip_address': '', 
        'is_phish_email': is_phish_email,
        'url_list': url_test_result,
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

        return {
        'sender_email_address': sender_email_address, 
        'sender_ip_address': sender_ip_address, 
        'is_phish_email': is_phish_email,
        'url_list': url_test_result,
        'attachment_list': attachment_test_result,
        }
    
def list_files_in_directory(directory_path):
    # 获取指定目录下的所有文件名
    file_names = []
    for root, dirs, files in os.walk(directory_path):
        for file in files:
            file_names.append(file)
    return file_names
    
if __name__ == '__main__':
    tester = phish_test('test.model', 'random_forest.pkl')
    # print(tester.body_only_test(''))
    # print(tester.eml_only_test('C:\\Users\\94843\\Downloads\\【两只老虎】最新通知【编号92177】.eml'))

    # print(list_files_in_directory('F:\\计算机科学与工程实践\\datacon2023-spoof-email-main\\day1'))
    for no in range(1, 8, 1):
        with open(f'day{no}_result.txt', 'a+', encoding='utf-8', errors='ignore') as f:
            for file in list_files_in_directory(f'F:\\计算机科学与工程实践\\datacon2023-spoof-email-main\\day{no}'):
                path = f'F:\\计算机科学与工程实践\\datacon2023-spoof-email-main\\day{no}\\' + file
                all_result = tester.eml_only_test(path)
                result = all_result['is_phish_email']
                f.write(f'{file}\t{result}\t\t\t{all_result}\n')