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

### Step 3
이전 단계에서 로그인 후 받은 토큰을 사용하여 사용자별 위시 리스트 기능을 구현한다.

- 위시 리스트에 등록된 상품 목록을 조회할 수 있다.
- 위시 리스트에 상품을 추가할 수 있다.
- 위시 리스트에 담긴 상품을 삭제할 수 있다.

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
- 토큰은 클라이언트가 로컬에 저장하는 방식으로 구현함
- 제품 목록을 조회하는 것만 토큰이 필요 없고 나머지 /api/product에 관한 요청은 토큰이 필요하게 구현함
- 토큰이 없거나 유효하지 않을 경우 `httpStatus.UNAUTHORIZED`를 반환함
- 그럴 경우 로그인 하라는 로그인 링크가 담긴 뷰가 출력됨
- 아이디나 비밀번호가 올바르지 않을 경우 `httpStatus.FORBIDDEN`을 반환함
- 회원가입 시 이미 있는 이메일일 경우 `httpStatus.CONFLICT`를 반환하도록 함


## 사용 예시
![image](https://github.com/rdme0/spring-gift-wishlist/assets/71927381/3baa4211-40fc-4246-b93e-5d489eb3a0c6)
![image](https://github.com/rdme0/spring-gift-wishlist/assets/71927381/e399ebde-6c06-49ea-b33e-4a6d69dfb0a7)
![image](https://github.com/rdme0/spring-gift-wishlist/assets/71927381/45335a8e-fd91-4a9e-8ad9-7a787919e8b3)
![image](https://github.com/rdme0/spring-gift-wishlist/assets/71927381/1b04e9e9-c7df-4b1b-b693-8364a670ec33)



