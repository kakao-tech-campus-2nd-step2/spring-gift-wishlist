# spring-gift-wishlist

## 2단계 - 인증

### 기능 요구 사항

- 사용자가 로그인하고 사용자별 기능을 사용할 수 있도록 구현한다.
- 아래 예시와 같이 HTTP 메시지를 주고받도록 구현한다.

### Request

```
POST /login/token HTTP/1.1
content-type: application/json
host: localhost:8080

{
"password": "password",
"email": "admin@email.com"
}
```

### Response

```
HTTP/1.1 200
Content-Type: application/json

{
"accessToken": ""
}
```

## 구현 기능 목록

1. 유저의 이메일과 비밀번호를 저장할 수 있는 테이블 생성
   <br><br>
2. 유저 생성로직 구현
    1. 사용자로부터 이메일과 비밀번호를 입력받는다.
    2. 이메일의 형식이 올바른지 검증한다.
        - 이메일 형식이 올바르지 않다면 `400 BAD REQUEST`를 반환한다.
    3. 중복된 이메일이 있는지 확인한다.
        - 중복된 이메일이 있다면 `400 BAD REQUEST`를 반환한다.
    4. 유저 테이블에 이메일과 비밀번호를 추가하고, `200 OK`을 반환한다.
       <br><br>
3. 로그인 로직 구현
    1. 사용자에게 이메일과 비밀번호를 입력받는다.
    2. 이메일의 형식이 올바른지 검증한다.
        - 이메일 형식이 올바르지 않다면 `400 BAD REQUEST`를 반환한다.
    3. 로그인 검증 로직을 거친다.
        1. 이메일이 유저 테이블에 있어야 한다.
            - 없다면, `401 UNAUTHORIZED`를 반환한다.
        2. 해당 이메일과 비밀번호가 일치하는지 확인한다.
            - 일치하지 않는다면, `401 UNAUTHORIZED`를 반환한다.
    4. 유저의 이메일과 비밀번호를 기반으로 토큰을 생성하고, 토큰과 `200 OK`를 반환한다. 