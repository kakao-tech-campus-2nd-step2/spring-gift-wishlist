# spring-gift-wishlist

## Step1 - 유효성 검사 및 예외 처리

### 기능 요구 사항

상품을 추가하거나 수정하는 경우, 클라이언트로부터 잘못된 값이 전달될 수 있다. 잘못된 값이 전달되면 클라이언트가 어떤 부분이 왜 잘못되었는지 인지할 수 있도록 응답을 제공한다.

* #### 상품 이름은 공백을 포함하여 최대 15자까지

* #### 특수 문자

    * ( ), [ ], +, -, &, /, _ 만 가능, 그 외 특수 문자 사용 불가

* #### "카카오"가 포함된 문구는 담당 MD와 협의한 경우에만 사용할 수 있다

## Step2 - 인증

### 기능 요구 사항

사용자가 로그인하고 사용자별 기능을 사용할 수 있도록 구현한다.

#### request

```
POST /login HTTP/1.1
content-type: application/json
host: localhost:8080

{
    "email": "admin@email.com"
    "password": "password",
}
```

#### response

```
HTTP/1.1 200 
Content-Type: application/json

{
    "accessToken": "..."
}
```

### 백

- [x] email과 password 전송하면 account table에 저장후 리턴값 받아오기
- [x] account 저장 성공하면 generated된 accessToken과 email을 token table에 저장 후 accessToken response에 보내주기
- [x] 이미 같은 이메일 가지고 있는 사람 있을 시 reject
- [x] 빈("") email, password 혹은 email 형식 안맞을 시 reject
- [ ] 유저 CRUD (선택)
- [x] jwt 토큰 parser
- [x] authenticate (jwt 토큰 유효성 검사)

### 프론트

/signup or /login에 요청 보내고 받은 AccessToken을 어디에 저장...?

- [x] signup 구현
- [x] login 구현

## Step3 - 위시 리스트

사용자 정보는 요청 헤더의 Authorization 필드를 사용한다.

### Authorization: <유형> <자격증명>

```
Authorization: Bearer token
```

- [x] wishlist CRUD (Authrization으로 valid한 토큰을 보낸 사용자만)
- [x] products에 있는 상품들만 add 가능하게
## 코드 리뷰 받은 부분
- [x] " Bearer" 상수화
- [x] User - toString 메서드 컨벤션 맞추기
- [x] User email, password 길이 제약조건과 schema 길이 맞추기
- [x] ProductDAO 생성자 with id 추가 및 JdbcProductRepository.java에서 save->key를 인자로 넣어주어 바로 생성되게
- [x] filterTest
- [x] productNameValidationTest - 한글이랑 영문 각각 사이즈 체크
- [ ] userEmailValidationTest
- [x] productIdValidationTest
- [ ] JdbcWishlistRepositoryTest
- [ ] secret값 application.properties로 옮기기 - ❌