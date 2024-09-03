import socket
import json

# 创建Socket对象
client_socket = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
sss = {
    'is_eml':True,
    'content':"F:\\计算机科学与工程实践\\Computer-Science-and-Engineering-Practice\\email_example\\恶意邮件28.eml", 
}


# 连接到服务器
client_socket.connect(('127.0.0.1', 9999))

# 发送数据
client_socket.send(json.dumps(sss, ensure_ascii=False, indent=4).encode('utf-8'))

# 接收响应
response = client_socket.recv(1024).decode('utf-8')
print(f"来自服务器的响应: {response}")

# 关闭连接
client_socket.close()
