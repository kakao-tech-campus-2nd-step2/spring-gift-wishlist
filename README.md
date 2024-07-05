# spring-gift-wishlist

## 프로그래밍 요구사항
- 자바 코드 컨벤션을 지키면서 프로그래밍한다.
  - 기본적으로 Google Java Style Guide를 원칙으로 한다.
  - 단, 들여쓰기는 '2 spaces'가 아닌 '4 spaces'로 한다.
- indent(인덴트, 들여쓰기) depth를 3이 넘지 않도록 구현한다. 2까지만 허용한다.
  - 예를 들어 while문 안에 if문이 있으면 들여쓰기는 2이다.
  - 힌트: indent(인덴트, 들여쓰기) depth를 줄이는 좋은 방법은 함수(또는 메서드)를 분리하면 된다.
- 3항 연산자를 쓰지 않는다.
- 함수(또는 메서드)의 길이가 15라인을 넘어가지 않도록 구현한다.
  - 함수(또는 메서드)가 한 가지 일만 잘 하도록 구현한다.
- else 예약어를 쓰지 않는다.
  - else를 쓰지 말라고 하니 switch/case로 구현하는 경우가 있는데 switch/case도 허용하지 않는다.
  - 힌트: if 조건절에서 값을 return하는 방식으로 구현하면 else를 사용하지 않아도 된다.


## STEP-2 기능 구현 사항
- 사용자가 회원 가입, 로그인, 추후 회원별 기능을 이용할 수 있도록 구현한다.
  - 회원은 이메일과 비밀번호를 입력하여 가입한다.
  - 토큰을 받으려면 이메일과 비밀번호를 보내야 하며, 가입한 이메일과 비밀번호가 일치하면 토큰이 발급된다.
  - 토큰을 생성하는 방법에는 여러 가지가 있다. 방법 중 하나를 선택한다.
  - (선택) 회원을 조회, 추가, 수정, 삭제할 수 있는 관리자 화면을 구현한다.

### 회원가입
Request
```
POST /members/register HTTP/1.1
content-type: application/json
host: localhost:8080

{
"email": "admin@email.com",
"password": "password"
}
```

Response
```
HTTP/1.1 200
Content-Type: application/json

{
"token": ""
}
```

### 회원가입 프로세스
1. /members/register 해당 URL path로 email과 password 값을 지닌 JSON 데이터를 POST 방식으로 Request
2. Request를 통해 받은 email에 해당하는 회원이 존재하는 지 DB조회
3. 존재할 경우, Login으로 Redirect 또는 Exception 
4. 존재하지 않을 경우 해당 JSON 데이터의 항목을 DB에 입력
5. 최종적으로 사용자에게 token을 발급

### 로그인
Request
```
POST /login/token HTTP/1.1
content-type: application/json
host: localhost:8080

{
  "password": "password",
  "email": "admin@email.com"
}
```
Response
```
HTTP/1.1 200
Content-Type: application/json

{
  "accessToken": ""
}
```

### 로그인 프로세스
1. 로그인을 원하는 회원이 email과 password를 POST 방식으로 Request
2. Request를 통해 받은 email에 해당하는 회원이 존재하는 지 DB조회
3. 존재하고, password가 일치 할 경우 -> token 생성
4. 아닐 경우, 403 Forbidden!!
5. 최종적으로 사용자에게 token을 발급

### 그 외 프로세스
1. 어떠한 회원이 로그인 상태를 유지하며 회원 관련 서비스를 이용할 경우
2. 항상 발급된 JWT token이 유효한가 검증 -> Interceptor로 구현
3. 유효하지 않으면 401 Unauthorized

## STEP-3 기능 구현 사항
- 이전 단계에서 로그인 후 받은 토큰을 사용하여 사용자별 위시 리스트 기능을 구현한다.
  - 위시 리스트에 등록된 상품 목록을 조회할 수 있다.
  - 위시 리스트에 상품을 추가할 수 있다.
  - 위시 리스트에 담긴 상품을 삭제할 수 있다.
### 실행 결과
- 사용자 정보는 요청 헤더의 Authorization 필드를 사용한다.
  - Authorization: <유형> <자격증명>
  - 인증 방법은 bearer 인증을 사용한다.
  
      ```Authorization: Bearer token```

### 추가해야될 기능 목록
- 위시 리스트에 대한 기본적 CRUD
- 토큰을 검증하여 접근가능한 회원인지 판단.
  - 이전에 만든 Interceptor와 webConfig에 해당 path 인증 설정
- 기본적인 Controller - Service - Repository 구조로 구성

### DB 스키마 설계
```
CREATE TABLE wish_list_items (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    member_id BIGINT NOT NULL,
    product_name VARCHAR(255) NOT NULL,
    product_price BIGINT NOT NULL,
    FOREIGN KEY (member_id) REFERENCES members(id)
);
```