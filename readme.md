#### 演示三种授权许可类型
##### 资源拥有者凭据许可类型
1.auth
http://localhost:8080/oauth/token?grant_type=password&client_id=userservice1&client_secret=1234&username=writer&password=writer
返回token

2.check
http://localhost:8080/oauth/check_token?client_id=userservice1&client_secret=1234&token=...
返回token验证后解析信息
##### 客户端授权许可类型
http://localhost:8080/oauth/token?grant_type=client_credentials&client_id=userservice2&client_secret=1234
##### 授权码许可类型 
1.code
http://localhost:8080/oauth/authorize?response_type=code&client_id=userservice3&redirect_uri=https://baidu.com

2.token
http://localhost:8080/oauth/token?grant_type=authorization_code&client_id=userservice3&client_secret=1234&code=XKkHGY&redirect_uri=https://baidu.com