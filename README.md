# spring-gift-wishlist

<br/>

# 1단계 - 유효성 검사 및 예외 처리

## 요구 사항 정의

잘못된 요청에 대한 응답 처리

### 상품 추가

- 잘못된 상품 이름 → MethodArgumentNotValidException 발생
    - 공백을 포함하여 최대 15자
    - ( ), [ ], +, -, &, /, _ 특수 문자만 가능
    - "카카오"가 포함된 문구는 담당 MD와 협의한 경우에만 사용
- 잘못된 상품 가격 → MethodArgumentNotValidException 발생
    - 양수만 가능
- 잘못된 상품 이미지 → MethodArgumentNotValidException 발생
    - null 제외, 빈 문자열 가능

### 상품 수정

- 잘못된 상품 id → NoSuchProductException 발생
- 잘못된 상품 이름 → MethodArgumentNotValidException 발생
- 잘못된 상품 가격 → MethodArgumentNotValidException 발생
- 잘못된 상품 이미지 → MethodArgumentNotValidException 발생

### 상품 삭제

- 잘못된 상품 id → NoSuchProductException 발생

<br/>
<br/>

# 2단계 - 인증

## 요구 사항 정의

사용자가 로그인하고 사용자별 기능을 사용할 수 있도록 구현

### 회원 테이블
- 이메일 - PK
- 비밀번호

### 회원가입
- 이메일, 비밀번호 POST
    ```
    POST /members/register HTTP/1.1
    content-type: application/json
    host: localhost:8080

    {
        "email": "admin@email.com",
        "password": "password"
    }
    ```
- Access Token 반환
    
    ```
    HTTP/1.1 200 
    Content-Type: application/json

    {
        "token": ""
    }
    ```

### 로그인
- 이메일, 비밀번호 POST
    ```
    POST /members/login HTTP/1.1
    content-type: application/json
    host: localhost:8080

    {
        "email": "admin@email.com",
        "password": "password"
    }
    ```
- Access Token 반환
    
    ```
    HTTP/1.1 200 
    Content-Type: application/json

    {
        "token": ""
    }
    ```

### 비밀번호 변경
- 현재 비밀번호, 새 비밀번호, 새 비밀번호 확인 POST
    ```
    Authorization: Bearer token
    ```
    ```
    POST /members/password HTTP/1.1
    content-type: application/json
    host: localhost:8080

    {
        "password": "password",
        "newPassword1": "newPassword1",
        "newPassword2": "newPassword2"
    }
    ```
- Access Token 반환
    
    ```
    HTTP/1.1 200 
    Content-Type: application/json

    {
        "token": ""
    }
    ```