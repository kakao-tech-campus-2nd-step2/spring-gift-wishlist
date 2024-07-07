# spring-gift-wishlist

## step0

fork 후, 1주차 과제 옮기기

피드백 반영하기

## step1

관리자 페이지에서 상품을 추가하거나 수정하는 경우, 클라이언트로부터 잘못된 값이 전달될 수 있다. 잘못된 값이 전달되면 클라이언트가 어떤 부분이 왜 잘못되었는지 인지할 수 있도록 응답을 제공해야한다.

- 상품 이름은 공백을 포함하여 최대 15자까지 입력할 수 있어야 한다.
- 가능한 특수 문자는 (),[],+,-,&,/,_ 그 외 특수 문자는 사용 불가하다.
- "카카오"가 포함된 문구는 담당 MD와 협의한 경우에만 사용할 수 있다!

### 구현해야하는 기능

- spring-boot-starter-validation 의존성 추가
- validation의 annotation을 통해 유효성 검사
- Exception 처리를 위한 클래스 생성

## step2

사용자가 회원가입, 로그인, 추후 회원별 기능을 이용할 수 있도록 구현한다.

- 회원은 email과 password를 입력하여, 가입한다. (memberDTO 필요할 듯)
- 토큰을 받으려면 이메일과 비밀번호를 보내야하며, 가입한 이메일과 비밀번호가 일치하면 토큰이 발급된다.
- 토큰을 생성하는 방식으로는 Json Web Token을 사용해보자

### 회원가입

Request

`````
POST /members/register HTTP/1.1
content-type: application/json
host: localhost:8080

{
    "email": "admin@email.com",
    "password": "password"
}
`````

Response

```
HTTP/1.1 200
Content-Type: application/json

{
    "token": ""
}
```

### 로그인

Request

```
POST /members/login HTTP/1.1
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

## Step3

로그인 후 받은 토큰을 사용하여 사용자별 위시 리스트 기능을 구현한다.

- 위시 리스트에 등록된 상품 목록을 조회할 수 있다.
- 위시 리스트에 상품을 추가할 수 있다.
- 위시 리스트에 담긴 상품을 삭제할 수 있다.

사용자 정보는 request 헤더의 Authorization 필드를 사용한다.
