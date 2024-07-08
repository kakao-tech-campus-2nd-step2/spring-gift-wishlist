# 🚀 2단계 - 회원 로그인

## 과제 진행 요구 사항
- 기능을 구현하기 전 README.md에 구현할 기능 목록을 정리해 추가한다.
- Git의 커밋 단위는 앞 단계에서 README.md에 정리한 기능 목록 단위로 추가한다. 
- AngularJS Git Commit Message Conventions을 참고해 커밋 메시지를 작성한다.
## 기능 요구 사항
- 사용자가 회원 가입, 로그인, 추후 회원별 기능을 이용할 수 있도록 구현한다.

- 회원은 이메일과 비밀번호를 입력하여 가입한다.
- 토큰을 받으려면 이메일과 비밀번호를 보내야 하며, 가입한 이메일과 비밀번호가 일치하면 토큰이 발급된다.
- 토큰을 생성하는 방법에는 여러 가지가 있다. 방법 중 하나를 선택한다.
(선택) 
- 회원을 조회, 추가, 수정, 삭제할 수 있는 관리자 화면을 구현한다.

### 아래 예시와 같이 HTTP 메시지를 주고받도록 구현한다

### Request

    POST /members/register HTTP/1.1
    content-type: application/json
    host: localhost:8080

    {
        "email": "admin@email.com",
        "password": "password"
    }
### Response
    HTTP/1.1 200
    Content-Type: application/json

    {
        "token": ""
    }
### 로그인
    POST /members/login HTTP/1.1
    content-type: application/json
    host: localhost:8080

    {
        "email": "admin@email.com",
        "password": "password"
    }
### Response
    HTTP/1.1 200
    Content-Type: application/json

    {
        "token": ""
    }

# 🚀 3단계 - 위시 리스트
## 기능 요구 사항
- 이전 단계에서 로그인 후 받은 토큰을 사용하여 사용자별 위시 리스트 기능을 구현한다.

- 위시 리스트에 등록된 상품 목록을 조회할 수 있다.
- 위시 리스트에 상품을 추가할 수 있다.
- 위시 리스트에 담긴 상품을 삭제할 수 있다.
##  실행 결과
- 사용자 정보는 요청 헤더의 Authorization 필드를 사용한다.

- Authorization: <유형> <자격증명>

