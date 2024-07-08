# spring-gift-wishlist

## 1단계 - 유효성 검사 및 예외 처리

- [x] 상품 이름은 공백을 포함하여 최대 15자까지 입력할 수 있다.
    - 특수 문자 가능: ( ), [ ], +, -, &, /, _
    - 그 외 특수 문자 사용 불가
- [x] "카카오"가 포함된 문구는 담당 MD와 협의한 경우에만 사용할 수 있다.

## 2단계 회원 로그인

- [x] 사용자가 회원 가입, 로그인, 추후 회원별 기능을 이용할 수 있도록 구현한다.
  - 회원은 이메일과 비밀번호를 입력하여 가입한다.
  - 토큰을 받으려면 이메일과 비밀번호를 보내야 하며, 가입한 이메일과 비밀번호가 일치하면 토큰이 발급된다.
  - 토큰을 생성하는 방법에는 여러 가지가 있다. 방법 중 하나를 선택한다.
  - (선택) 회원을 조회, 추가, 수정, 삭제할 수 있는 관리자 화면을 구현한다. -> 생략

**🤨추후 회원별 기능?**
`DB`에서 어떻게 `User`관리가 되어야 한다는 말인가..?

## 3단계 위시 리스트
이전 단계에서 로그인 후 받은 토큰을 사용하여 사용자별 위시 리스트 기능을 구현한다.

- [ ] 위시 리스트에 등록된 상품 목록을 조회할 수 있다.
- [ ] 위시 리스트에 상품을 추가할 수 있다.
- [ ] 위시 리스트에 담긴 상품을 삭제할 수 있다.
