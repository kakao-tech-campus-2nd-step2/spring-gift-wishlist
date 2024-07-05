# spring-gift-wishlist
___

## Step1 - 유효성 검사 및 예외처리
___

### 기능 요구사항
상품을 추가하거나 수정하는 경우, 클라이언트로부터 잘못된 값이 전달될 수 있다. 
잘못된 값이 전달되면 클라이언트가 어떤 부분이 왜 잘못되었는지 인지할 수 있도록 응답을 제공한다.

- 상품 이름은 공백을 포함하여 최대 15자까지 입력할 수 있다.
- 특수 문자
  - 가능: ( ), [ ], +, -, &, /, _
  - 그 외 특수 문자 사용 불가
- "카카오"가 포함된 문구는 담당 MD와 협의한 경우에만 사용할 수 있다.

### 구현할 기능 목록

- `ProductReqDto`: 상품 이름(name) 필드에 대한 유효성 검사 기능 추가 
  - 상품 이름은 1자 이상 15자 이하로 입력해야 한다.
  - 상품 이름에 '카카오' 문구가 포함되어 있으면 안된다.
  - 상품 이름은 한글, 영문, 숫자, 공백, 특수문자 ( ), [ ], +, -, &, /, _ 만 입력 가능하다.


- `ProductController`: 상품 생성 API에 대한 유효성 검사 기능 추가
  - 상품 이름이 유효하지 않은 경우, 클라이언트에게 적절한 응답을 보낸다.


- `ProductExceptionHandler`: 상품 API에 대한 예외처리 기능 추가


- `ProductErrorCode`: 상품 API에 대한 예외 코드 추가
  - `PRODUCT_NOT_FOUND`: 상품을 찾을 수 없는 경우
  - `PRODUCT_ALREADY_EXISTS`: 이미 존재하는 상품을 추가하려는 경우
  - `PRODUCT_NOT_ENOUGH_STOCK`: 상품의 재고가 부족한 경우
  - `INVALID_INPUT_VALUE_PRODUCT`: 상품 API에 잘못된 값이 전달된 경우
  - `PRODUCT_CREATE_FAILED`: 상품 생성에 실패한 경우
  - `PRODUCT_UPDATE_FAILED`: 상품 수정에 실패한 경우
  - `PRODUCT_DELETE_FAILED`: 상품 삭제에 실패한 경우

### 응답 예시


- 입력한 상품의 이름이 15자 초과, 카카오 문구 포함, 허용되지 않은 특수문자가 들어간 경우
  ```json
  {
    "type": "https://gift.com/docs/error#P004",
    "title": "Invalid input value for product",
    "status": 400,
    "detail": "상품 생성에 필요한 값이 누락되었거나 잘못되었습니다.",
    "instance": "/api/products",
    "code": "P004",
    "invalidParams": [
      {
        "field": "name",
        "message": "상품 이름은 1자 이상 15자 이하로 입력해주세요."
      },
      {
        "field": "name",
        "message": "상품 이름에 '카카오' 문구가 포함되어 있습니다. 담당 MD와 협의 후 사용해주세요."
      },
      {
        "field": "name",
        "message": "상품 이름은 한글, 영문, 숫자, 공백, 특수문자 (), [], +, -, &, /, _ 만 입력 가능합니다."
      }
    ]
  }
  ```

## Step2 - 회원 로그인
___

### 기능 요구사항
사용자가 회원 가입, 로그인, 추후 회원별 기능을 이용할 수 있도록 구현한다.

- 회원은 이메일과 비밀번호를 입력하여 가입한다.
- 토큰을 받으려면 이메일과 비밀번호를 보내야 하며, 가입한 이메일과 비밀번호가 일치하면 토큰이 발급된다.
- 토큰을 생성하는 방법에는 여러 가지가 있다. 방법 중 하나를 선택한다.
- (선택) 회원을 조회, 추가, 수정, 삭제할 수 있는 관리자 화면을 구현한다.

아래 예시와 같이 HTTP 메시지를 주고받도록 구현한다.
- 회원 가입
  - Request
    ```http
    POST /members/register HTTP/1.1
    content-type: application/json
    host: localhost:8080
    
    {
      "email": "admin@email.com",
      "password": "password"
    }
    ```
  - Response
    ```http
    HTTP/1.1 200
    Content-Type: application/json

    {
      "token": ""
    }
    ```

- 로그인    
  - Request
    ```http
    POST /members/login HTTP/1.1
    content-type: application/json
    host: localhost:8080
  
    {
    "email": "admin@email.com",
    "password": "password"
    }
    ```

  - Response
    ```http
    HTTP/1.1 200
    Content-Type: application/json

    {
      "token": ""
    }
    ```

### 구현할 기능 목록

- `MemberReqDto`: 회원 가입, 로그인 API에 대한 요청 DTO
  - 회원 가입 요청 DTO: email, password 필드
  - 로그인 요청 DTO: email, password 필드


- `MemberRepository`: 회원 정보를 저장하고 조회하는 기능
  - 회원 정보를 저장하는 기능
  - 이메일로 회원 정보를 조회하는 기능


- `MemberService`: 회원 가입, 로그인 기능
  - 회원 가입 기능
  - 로그인 기능


- `MemberController`: 회원 가입, 로그인 API


- JWT 토큰 생성 및 검증 기능
  - JWT 토큰 생성 기능
  - JWT 토큰 검증 기능


- `AuthService`: 회원가입 및 로그인에 대한 인증 기능
  - 회원가입 인증 기능
  - 로그인 인증 기능


- `AuthController`: 회원가입 및 로그인 API