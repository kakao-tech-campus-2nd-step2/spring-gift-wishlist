# spring-gift-wishlist

## Step2 기능 요구 사항

사용자가 회원 가입, 로그인, 추후 회원별 기능을 이용할 수 있도록 구현한다.

* 회원은 이메일과 비밀번호를 입력하여 가입한다.
* 토큰을 받으려면 이메일과 비밀번호를 보내야 하며, 가입한 이메일과 비밀번호가 일치하면 토큰이 발급된다.
* 토큰을 생성하는 방법에는 여러 가지가 있다. 방법 중 하나를 선택한다.

## Step2 구현 기능

- 회원의 email, password를 저장하는 User클래스 생성.
- jwt를 생성하는 jwtUtil클래스 생성.
- Service layer에서 로그인되었을때 토큰 반환.



## Step3 기능 요구 사항

* Step2에서 로그인 후 받은 토큰을 사용해서 사용자별 위시 리스트 기능을 구현.
* 위시 리스트에 등록된 상품 목록을 조회할 수 있다.
* 위시 리스트에 상품을 추가할 수 있다.
* 위시 리스트에 담긴 상품을 삭제할 수 있다.

## Step3 구현 기능

- Wishlist, ProductWithQuantity 클래스 생성.
- Wishlist와 ProductWithQuantity는 1:N 매핑 관계.
- HandlerMethodArgumentResolver를 사용해 Header의 jwt의 email정보를 Controller의 파라미터로 받아 회원 검증 및 회원 정보 사용
