# spring-gift-wishlist

## 기능 요구 사항

### Step1
상품을 추가하거나 수정하는 경우, 클라이언트로부터 잘못된 값이 전달될 수 있다. 잘못된 값이 전달되면 클라이언트가 어떤 부분이 왜 잘못되었는지 인지할 수 있도록 응답을 제공한다.

- 상품 이름은 공백을 포함하여 최대 15자까지 입력할 수 있다.
- 특수 문자
  - 가능: ( ), [ ], +, -, &, /, _
  - 그 외 특수 문자 사용 불가
- "카카오"가 포함된 문구는 담당 MD와 협의한 경우에만 사용할 수 있다.

### Step 2
사용자가 회원 가입, 로그인, 추후 회원별 기능을 이용할 수 있도록 구현한다.

- 회원은 이메일과 비밀번호를 입력하여 가입한다.
- 토큰을 받으려면 이메일과 비밀번호를 보내야 하며, 가입한 이메일과 비밀번호가 일치하면 토큰이 발급된다.
- 토큰을 생성하는 방법에는 여러 가지가 있다. 방법 중 하나를 선택한다.
- 아래 예시와 같이 HTTP 메시지를 주고받도록 구현한다.

### 회원 가입
#### Request
```http

POST /members/register HTTP/1.1
content-type: application/json
host: localhost:8080


{
"email": "admin@email.com",
"password": "password"
}
```


#### Response
```http
HTTP/1.1 200
Content-Type: application/json

{
"token": ""
}
```

#### 로그인
```http
POST /members/login HTTP/1.1
content-type: application/json
host: localhost:8080

{
"email": "admin@email.com",
"password": "password"
}
```

#### Response
```http
HTTP/1.1 200
Content-Type: application/json

{
"token": ""
}
```



## 구현
- Validation을 BE FE 이중으로 하는 방식으로 구현
- BE FE 둘 다 정규식을 이용함
- JWT를 사용함
- 인터셉터를 사용함