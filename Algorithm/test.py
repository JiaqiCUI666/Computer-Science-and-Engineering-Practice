import re
import numpy as np
from sklearn.ensemble import GradientBoostingClassifier
import warnings
import pickle
warnings.filterwarnings('ignore')
from feature import FeatureExtraction


file = open("model.pkl","rb")
gbc = pickle.load(file)
file.close()
def extract_urls(text):

    url_pattern =  r'https?://(?:[-\w.]|(?:%[\da-fA-F]{2}))+(?:\?[-\w.=&?]+)?'
    urls = re.findall(url_pattern, text)
    return urls

def check_urls(urls):
    url_probabilities = {}  # 创建一个字典来存储URL和其安全概率
    for url in urls:
        obj = FeatureExtraction(url)
        x = np.array(obj.getFeaturesList()).reshape(1, 30)
        y_pred = gbc.predict(x)[0]
        y_pro_phishing = gbc.predict_proba(x)[0, 0]
        y_pro_non_phishing = gbc.predict_proba(x)[0, 1]
        url_probabilities[url] = y_pro_non_phishing  # 将URL和其安全概率保存到字典中
    return url_probabilities  # 返回包含URL和安全概率的字典

# 使用text文本，这里更换成正文即可
text = "这是一个包含URL的文本，请访问 https://www.bilibili.com/ 获取更多信息，或者访问 https://www.hao123.com/?src=from_pc_logon进行测试。"


urls = extract_urls(text)

url_probabilities = check_urls(urls)

# 打印结果
for url, prob in url_probabilities.items():
    print(f"{url}: {prob:.4f}")