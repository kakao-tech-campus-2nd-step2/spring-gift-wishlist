
# spring-gift-product

#### 1. 상품을 추가하거나 수정하는 경우, 클라이언트로부터 잘못된 값이 전달될 수 있다. 잘못된 값이 전달되면 클라이언트가 어떤 부분이 왜 잘못되었는지 인지할 수 있도록 응답을 제공한다.
1) 상품 이름은 공백을 포함하여 최대 15자까지 입력할 수 있다. -> 상품 이름 길이가 최대 15자 임을 알리고 에러를 반환한다
2) 특수문자
   1) 가능:( ), [ ], +, -, &, /, _
   2) 그 외 특수 문자 사용 불가
3) "카카오"가 포함된 문구는 담당 MD와 협의한 경우에만 사용 가능 -> 담당 MD만 수정 가능하게끔 할 것
4) 사용자가 회원 가입, 로그인, 추후 회원별 기능을 이용할 수 있도록 구현한다. 
5) 회원은 이메일과 비밀번호를 입력하여 가입한다. 
6) 토큰을 받으려면 이메일과 비밀번호를 보내야 하며, 가입한 이메일과 비밀번호가 일치하면 토큰이 발급된다.
7) (선택) 회원을 조회, 추가, 수정, 삭제할 수 있는 관리자 화면을 구현한다.

