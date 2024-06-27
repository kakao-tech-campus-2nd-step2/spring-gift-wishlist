# [구현할 기능 목록] 1단계 - 상품 API 
## 1. 상품 정보를 담는 Product 클래스 구현
#### - 필드값 : Long id, String name, Long price, String imageurl;
## 2. CRUD 요청을 받고 응답을 반환할 controller 구현
#### - Create : 자바 컬렉션 프레임워크를 사용하여 메모리에 저장하는 메서드 구현
#### - Read : 상품 전부를 조회하는 메서드, URL 경로 변수와 일치하는 id를 가지는 상품을 조회하는 메서드 구현
#### - Update : URL 경로 변수와 일치하는 id를 가진 상품의 필드값들 수정하는 메서드 구현
#### - Delete : URL 경로 변수와 일치하는 id를 가진 상품 삭제하는 메서드 구현


# [구현할 기능 목록] 2단계 - 관리자 화면 
## 1. 의존성 추가
### (1) 의존성 추가 
- implementation 'org.springframework.boot:spring-boot-starter-thymeleaf
- implementation 'org.springframework.boot:spring-boot-starter-data-jpa'
- runtimeOnly 'com.h2database:h2'
### (2) 간단한 웹페이지 구현
- `products.html` : 상품 관리 메인 페이지. 추가된 상품들을 보여줌.
- `creat_product.html` : 사용자 입력을 통해 상품 정보 추가.
- `edit_product.html` : 추가된 상품 정보를 수정 가능
### (3) MVC 구현
- `ProductController` : SSR 이용하여 컨트롤러 구현   
- `ProductService` : CRUD 서비스 구현 
- `ProductRepository` : JPA 활용

