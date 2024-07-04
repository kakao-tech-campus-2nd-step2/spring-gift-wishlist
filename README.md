# spring-gift-wishlist
## step1
1주차 피드백 반영
- rest api
- Controller vs RestController

2주차 step1 Validation
- 상품 추가, 수정 Validation
    - 상품 이름은 공백을 포함하여 최대 15자까지 입력할 수 있다.
        - @Size(min=, max=)
    - 특수 문자
        - 가능: ( ), [ ], +, -, &, /, _
        - 그 외 특수 문자 사용 불가
        - @Pattern
    - "카카오"가 포함된 문구는 담당 MD와 협의한 경우에만 사용할 수 있다.
        - 유효성 검사 로직 추가
    - 중복 상품 추가 불가
        - 유효성 검사 로직 추가
    - @RequestBody, @RequestParam, @PathVariable 사용
---
## step 2
인증, 사용자가 로그인하고 사용자별 기능을 사용할 수 있도록 구현한다.
아래 예시와 같이 HTTP 메시지를 주고받도록 구현한다.

Request

    POST /login/token HTTP/1.1
    content-type: application/json
    host: localhost:8080
    {
    "password": "password",
    "email": "admin@email.com"
    }

Response

    HTTP/1.1 200
    Content-Type: application/json
    
    {
    "accessToken": ""
    }

- 로그인 기능 구현
  - users 테이블 구축
    - users
      - email(pk)
      - pw
      - role
  - 회원가입 구현
    - 비밀번호 해싱
  - 로그인 구현
    - 토큰 인증