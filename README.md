# spring-gift-wishlist
## step 0
- [x] 1주차 과제 중 conflict resolve 하기
- [x] 1주차 과제를 merge
## step 1
### 기능 요구 사항 
- 상품을 추가하거나 수정하는 경우, 클라이언트로부터 잘못된 값이 전달될 수 있다. 잘못된 값이 전달되면 클라이언트가 어떤 부분이 왜 잘못되었는지 인지할 수 있도록 응답을 제공한다.
- 상품 이름은 공백을 포함하여 최대 15자까지 입력할 수 있다.
- 특수 문자 가능: ( ), [ ], +, -, &, /, _ (그 외 특수 문자 사용 불가)
- "카카오"가 포함된 문구는 담당 MD와 협의한 경우에만 사용할 수 있다.
### 구현할 기능 목록
- [x] Product 객체의 15자 제한 + 특수 문자 제한 유효성 검사 구현하기
- [x] "카카오"가 포함된 문구의 유효성 검사 구현하기
- [x] 유효성 검사에서 발생한 Exception을 처리하는 ExceptionHandler 구현하기
- [x] 만들어둔 서버 사이드 렌더링 페이지에 잘못된 값이 왜 잘못되었는지 인지할 수 있는 thymeleaf 템플릿 만들기
## step 2
### 기능 요구 사항
- 사용자가 로그인하고 사용자별 기능을 사용할 수 있도록 구현한다.
- 아래 예시와 같이 HTTP 메시지를 주고받도록 구현한다.
#### REQUEST
~~~
POST /login/token HTTP/1.1
content-type: application/json
host: localhost:8080

{
    "password": "password",
    "email": "admin@email.com"
}
~~~
#### RESPONSE
~~~
HTTP/1.1 200 
Content-Type: application/json

{
    "accessToken": ""
}
~~~
### 구현할 기능 목록
- [x] 사용자의 비밀번호와 이메일을 저장하는 Member, MemberDTO 클래스 생성
- [x] MemberDTO와 DB의 데이터 전달을 담당하는 Repository 클래스 생성
- [ ] JWT 토큰 관련 기능 구현
- [ ] 사용자가 로그인하면 요청를 매핑하여 응답하는 AuthController 구현
- [ ] thymeleaf 사용하여 로그인 페이지 구현 (선택)