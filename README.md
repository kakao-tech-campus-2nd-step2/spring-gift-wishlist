step1
상품을 추가하거나 수정하는 경우, 사용자가 조건에 부합하지 않는 상품 이름을 입력했을 경우, 경고하여 재입력하게 하는 기능
조건
1.상품 이름은 공백을 포함하여 최대 15자까지 입력할 수 있다.
2.특수 문자
가능: ( ), [ ], +, -, &, /, _
그 외 특수 문자 사용 불가
3."카카오"가 포함된 문구 사용 불가

실제로 추가, 수정하는 기능을 수행할 때,(사용자가 웹페이지에서 정보를 입력하고 제출버튼을 누르면) 유효성을 검사

ProductController.java
추가, 수정하는 기능을 수행하는 메서드에 대해 productService의 추가, 수정 메서드를 실행하고 예외 발생 시 
예외 메시지와 product 객체(추가 기능이면 빈 객체, 수정 기능이면 수정 전 객체) model 객체에 추가

ProductService.java
추가, 수정하는 기능을 수행하는 메서드에 대해 productRepository에 값을 바꾸기 전 ProductValidator 객체로 유효성 검사.

ProductValidator.java
조건에 따른 유효성 검사를 수행한다

Add_product.html, Edit_product.html
model에 저장되어 있는 에러 메시지 가져와서 출력

step2,3
사용자가 회원 가입, 로그인, 추후 회원별 기능을 이용할 수 있도록 구현한다.
로그인 후 받은 토큰을 사용하여 사용자별 위시 리스트 기능을 구현한다.

위시 리스트에 등록된 상품 목록을 조회할 수 있다.
위시 리스트에 상품을 추가할 수 있다.
위시 리스트에 담긴 상품을 삭제할 수 있다.

WishListService.java: 위시 리스트의 제품 추가 및 제거와 같은 비즈니스 로직을 처리한다.
WishListController.java: 위시 리스트와 관련된 HTTP 요청을 처리하고 뷰를 반환한다.
WishList.java: 사용자와 제품 간의 위시 리스트 엔티티를 정의한다.
WishListRepository.java: 위시 리스트 엔티티에 대한 데이터베이스 작업을 처리한다.
wishlist.html: 사용자의 위시 리스트를 보여주는 웹 페이지이다.
add_product_to_wishlist.html: 위시 리스트에 제품을 추가하는 폼을 제공하는 웹 페이지이다.

login.html: 사용자 로그인 폼을 제공하는 웹 페이지이다.
register.html: 사용자 등록 폼을 제공하는 웹 페이지이다.

User.java: 사용자 엔티티를 정의한다.
UserService.java: 사용자 등록 및 인증과 같은 사용자 관련 비즈니스 로직을 처리한다.
UserController.java: 사용자 관련 HTTP 요청을 처리하고 뷰를 반환한다.
UserRepository.java: 사용자 엔티티에 대한 데이터베이스 작업을 처리한다.
