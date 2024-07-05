# spring-gift-wishlist

## Step2

- [x] 회원 이메일과 비밀번호를 입력하여 가입한다.
  - [x] Member Controller
  - [x] Member Service
  - [x] Member Repository
  - [x] Member 테이블 생성
- [x] 토큰을 받기 위해 이메일과 비밀번호를 보내야 하며, 가입한 메일과 비밀번호가 일치하면 토큰이 발급
- [x] 토큰 생성 방식 채택
  - [x] JJWT 이용하여 토큰 생성
  - [x] "이메일":"비밀번호"를 credential로 이용하여 토큰 생성
