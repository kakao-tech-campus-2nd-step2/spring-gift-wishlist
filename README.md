# spring-gift-wishlist

## 기능 요구 사항
사용자가 로그인하고 사용자별 기능을 사용할 수 있도록 구현한다.

아래 예시와 같이 HTTP 메시지를 주고받도록 구현한다.

**Request**  
```http request
POST /login/token HTTP/1.1
content-type: application/json
host: localhost:8080

{
"password": "password",
"email": "admin@email.com"
}
```

**Response**  
```http request
HTTP/1.1 200
Content-Type: application/json

{
"accessToken": ""
}
```
