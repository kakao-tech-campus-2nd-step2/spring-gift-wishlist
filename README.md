# spring-gift-wishlist

## step 1
> 상품을 추가하거나 수정하는 경우, 클라이언트로부터 잘못된 값이 전달될 수 있다. 잘못된 값이 전달되면 클라이언트가 어떤 부분이 왜 잘못되었는지 인지할 수 있도록 응답을 제공한다. 
> - 상품 이름은 공백을 포함하여 최대 15자까지 입력할 수 있다.
> - 특수 문자
>   - 가능: ( ), [ ], +, -, &, /, _
>   - 그 외 특수 문자 사용 불가
>   - "카카오"가 포함된 문구는 담당 MD와 협의한 경우에만 사용할 수 있다.

### 유효성 검사
1. 상품 이름
    - 공백을 포함하여 최대 15자
    - 특수 문자 일부 사용 가능
        - ( ), [ ], +, -, &, /, _ 
    - "카카오"가 포함된 문구 사용 불가
2. 상품 가격
    - 0 이상의 정수

### 예외 처리
1. 글자수 초과
   - message: "상품 이름은 15자 이하로 입력해주세요."
2. 사용하지 못하는 특수문자 포함
   - message: "사용할 수 없는 특수문자가 포함되어 있습니다."
3. "카카오" 포함
   - message: "상품 이름에 '카카오'를 포함할 수 없습니다. 담당 MD와 협의 후 사용해주세요."
4. 가격이 0 이하
   - message: "상품 가격은 0 이상의 정수로 입력해주세요."

## step 2
> 사용자가 로그인하고 사용자별 기능을 사용할 수 있도록 구현한다. 
> 아래 예시와 같이 HTTP 메시지를 주고받도록 구현한다.
> #### Request
> ```
> POST /login/token HTTP/1.1
> content-type: application/json
> host: localhost:8080
> {
> "password": "password",
> "email": "admin@email.com"
> }
> ```
> #### Response
> ```
> HTTP/1.1 200
> Content-Type: application/json
> {
> "accessToken": ""
> }

### DB 테이블 추가
User 테이블
- id: Long - 회원 고유 식별자
- name: String - 회원명
- email: String - 회원 이메일
- password: String - 비밀번호
- status: Boolean - 회원 활성화 상태 (true: 활성, false: 비활성)

### 로그인
1. 클라이언트가 email, password를 담은 request 전송
2. email로 User 조회
3. 조회된 User의 password와 request의 password 비교
4. 비밀번호가 일치하면 User에 대한 accessToken 발급
5. 비밀번호가 일치하지 않으면 에러 메세지 전송
