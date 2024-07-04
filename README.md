step1
상품을 추가하거나 수정하는 경우, 사용자가 조건에 부합하지 않는 상품 이름을 입력했을 경우, 경고하여 재입력하게 하는 기능
조건
1.상품 이름은 공백을 포함하여 최대 15자까지 입력할 수 있다.
2.특수 문자
가능: ( ), [ ], +, -, &, /, _
그 외 특수 문자 사용 불가
3."카카오"가 포함된 문구 사용 불가

실제로 추가, 수정하는 기능을 수행할 때,(사용자가 웹페이지에서 정보를 입력하고 제출버튼을 누르면) 유효성을 검사

ProductController 클래스
추가, 수정하는 기능을 수행하는 메서드에 대해 productService의 추가, 수정 메서드를 실행하고 예외 발생 시 
예외 메시지와 product 객체(추가 기능이면 빈 객체, 수정 기능이면 수정 전 객체) model 객체에 추가

ProductService 클래스
추가, 수정하는 기능을 수행하는 메서드에 대해 productRepository에 값을 바꾸기 전 ProductValidator 객체로 유효성 검사.

ProductValidator 클래스
조건에 따른 유효성 검사를 수행하는 클래스

Add_product.html, Edit_product.html
model에 저장되어 있는 에러 메시지 가져와서 출력

ProductDTO 클래스
컨트롤러에서 클라이언트 요청을 받고 응답을 보낼 때 사용

ProductException 클래스
커스텀 예외 클래스 생성
