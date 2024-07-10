# spring-gift-wishlist

### 구현할 기능 목록

## 1단계

### 유효성 검사 및 예외처리
- DTO 검증
- Controller 검증
- `@ExceptionHander`
- `@RestControllerAdvice

상품 이름
   1. 공백을 포함하여 최대 15자까지 입력
   2. 특수 문자 (),[],+,-,&,/,_ 제외하고 사용 불가
   3. "카카오"가 포함된 문구는 담당 MD와 협의한 경우에만 사용 가능

## 2단계

### 회원 가입과 로그인

- Member 도메인
  - email
  - password

  
email과 password를 서버로 요청을 보내고 DBD에 일치한 회원이 있는지 확인 후 access token 발급
