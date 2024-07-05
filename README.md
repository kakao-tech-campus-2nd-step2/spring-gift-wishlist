# spring-gift-wishlist

## step 1

- 기능 요구 사항
    - 상품 이름은 공백을 포함하여 최대 15자까지 입력할 수 있다.
    - 특수 문자 가능: ( ), [ ], +, -, &, /, _
    - 그 외 특수 문자 사용 불가
    - "카카오"가 포함된 문구는 담당 MD와 협의한 경우에만 사용할 수 있다.
- 힌트
    - validation
        - spring-boot-starter-validation 의존성을 명시적으로 추가한다.
        - implementation 'spring-boot-starter-validation'

## step 2
- 기능 요구 사항
  - 사용자가 회원 가입, 로그인, 추후 회원별 기능을 이용할 수 있도록 구현한다.
  - 회원은 이메일과 비밀번호를 입력하여 가입한다.
  - 토큰을 받으려면 이메일과 비밀번호를 보내야 하며, 가입한 이메일과 비밀번호가 일치하면 토큰이 발급된다.