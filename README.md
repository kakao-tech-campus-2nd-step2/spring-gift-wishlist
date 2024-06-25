# 구현할 기능 목록
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

