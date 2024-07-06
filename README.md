# spring-gift-wishlist
# 0단계 :  jdbc 연동 해서 CRUD 구현 완료
# 1단계 : 유효성 검사 및 예외처리
- [x] insert 할때 
- [x] update 할때 
- [x] 상품 이름 길이 유효성 검사 
    - [x] 상품이름의 길이가 1~15인지 확인
    - [x] 유효하지 않는 경우
        - [x] 예외처리
        - [x] http 상태 코드 전송 (400 Bad Request)
        - [x] 응답 메세지 전송 

- [x] 상품이름에 사용불가능한 특수문자가 있는지 유효성 검사 
    - [x] 상품이름에 특수문자가 있는지 확인 
    - [x]  ()[] + - &  / _ 외의 특수문자가 잇는지 확인 
    - [x] 유효하지 않는경우
        - [x] 예외처리
        - [x] http 상태 코드 전송 (400 Bad Request)
        - [x] 응답 메세지 전송

- [x] 상품이름에 "카카오" 가 포함되어 있는지 유효성 검사 
    - [x] "카카오"라는 단어가 포함되어 있는 지 확인
    - [x] 유효하지 않는경우
        - [x] 예외처리
        - [x] http 상태 코드 전송 (400 Bad Request)
        - [x] 응답 메세지 전송


# 2단계 : 회원 로그인 구현 

## 회원가입 기능 
  - [] 이용자로부터 가입을 위해 이메일과 비밀번호를 입력받음 
  - [] 값을 숨겨서 서버로 전송 
  - [] 해당값을 데이터베이스에 저장 

### 회원가입시 주고 받는 http 메시지 예시 

Request
POST /members/register HTTP/1.1
content-type: application/json
host: localhost:8080

{
"email": "admin@email.com",
"password": "password"
}
Response
HTTP/1.1 200
Content-Type: application/json

{
"token": ""
}


## 로그인 기능 
  - [] 이용자로부터 로그인을 위해 이메일과 비밀번호를 입력받음
  - [] 값을 숨겨서 서버로 전송
  - [] 데이터베이스에 해당 이메일과 비밀번호가 있는지 확인 
  - [] 이메일과 비밀번호가 특정회원의 것 과 일치하면 토큰 발급 
  - [] 토큰을 이용자에게 반환 

### 주고받는 회원가입 메시지 예시 

POST /members/login HTTP/1.1
content-type: application/json
host: localhost:8080

{
"email": "admin@email.com",
"password": "password"
}
Response
HTTP/1.1 200
Content-Type: application/json

{
"token": ""
}


### 인증시 사용가능한 기술 
- Basic 인증
Base64로 인코딩된 사용자 ID, 비밀번호 쌍을 인증 정보(credentials) 값으로 사용한다.

Authorization: Basic base64({EMAIL}:{PASSWORD})

- JSON Web Token
JJWT 라이브러리를 사용하여 JWT을 쉽게 만들 수 있다.

dependencies {
compileOnly 'io.jsonwebtoken:jjwt-api:0.12.6'
runtimeOnly 'io.jsonwebtoken:jjwt-impl:0.12.6'
runtimeOnly 'io.jsonwebtoken:jjwt-jackson:0.12.6'
}
String secretKey = "Yn2kjibddFAWtnPJ2AFlL8WXmohJMCvigQggaEypa5E=";
String accessToken = Jwts.builder()
.setSubject(member.getId().toString())
.claim("name", member.getName())
.claim("role", member.getRole())
.signWith(Keys.hmacShaKeyFor(secretKey.getBytes()))
.compact();

### 응답 코드
- Authorization 헤더가 유효하지 않거나 토큰이 유효하지 않은 경우 401 Unauthorized를 반환한다.

401 Unauthorized 클라이언트 오류 상태 응답 코드는 해당 리소스에 유효한 인증 자격 증명이 없기 때문에 
요청이 적용되지 않았음을 나타냅니다. 이 상태는 WWW-Authenticate (en-US) 헤더와 함께 전송되며, 
이 헤더는 올바르게 인증하는 방법에 대한 정보를 포함하고 있습니다. 이 상태는 403과 비슷하지만, 
401 Unauthorized의 경우에는 인증이 가능합니다.

- 잘못된 로그인, 비밀번호 찾기, 비밀번호 변경 요청은 403 Forbidden을 반환한다.

HTTP 403 Forbidden 클라이언트 오류 상태 응답 코드는 서버에 요청이 전달되었지만, 
권한 때문에 거절되었다는 것을 의미합니다. 이 상태는 401과 비슷하지만, 로그인 로직
(틀린 비밀번호로 로그인 행위)처럼 반응하여 재인증(re-authenticating)을 하더라도 
지속적으로 접속을 거절합니다.