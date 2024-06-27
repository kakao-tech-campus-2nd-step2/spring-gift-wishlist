# [구현할 기능 목록] 1단계 - 상품 API 
## 1. 상품 정보를 담는 Product 클래스 구현
- [x] 상품 클래스 필드값 설정 : Long id, String name, Long price, String imageurl;
## 2. CRUD 요청을 받고 응답을 반환할 controller 구현
- [x] Create : 자바 컬렉션 프레임워크를 사용하여 메모리에 저장하는 메서드 구현
- [x] Read : 상품 전부를 조회하는 메서드, URL 경로 변수와 일치하는 id를 가지는 상품을 조회하는 메서드 구현
- [x] Update : URL 경로 변수와 일치하는 id를 가진 상품의 필드값들 수정하는 메서드 구현
- [x] Delete : URL 경로 변수와 일치하는 id를 가진 상품 삭제하는 메서드 구현
## 3. 테스트 코드 작성
- [x] ProductControllerTest : ProductController의 CRUD 메서드 테스트 코드 작성

# [구현할 기능 목록] 2단계 - 관리자 화면 
## 1. 의존성 추가
### (1) 의존성 추가 
- [x] `Thymeleaf` :  implementation 'org.springframework.boot:spring-boot-starter-thymeleaf
### (2) 간단한 웹페이지 구현
- [x] `products.html` : 상품 관리 메인 페이지. 추가된 상품들을 보여줌.
- [x] `creat_product.html` : 사용자 입력을 통해 상품 정보 추가.
- [x] `edit_product.html` : 추가된 상품 정보를 수정 가능
### (3) MVC 모델 구현
- [x] `ProductController` : SSR 이용하여 컨트롤러 구현   
- [x] `ProductService` : CRUD 서비스 구현 
- [x] `ProductRepository` : JPA 활용

# [구현할 기능 목록] 3단계 - DB 적용
## 1. 상품 옵션 추가
- [x] create_product.html : 상품 옵션 추가 기능 구현
- [x] 전체적인 웹 페이지 ui 변경

## 2. JdbcTemplate을 활용하여 상품 정보를 DB에 저장
- [x] application.yml : h2 database 접속 정보 설정
- [x] schema.sql : 데이터베이스의 테이블 설계
- [x] ProducRepository : JdbcTemplate을 활용하여 CRUD 구현 
- [x] DB 사용 로직의 중복 해결 by RowMapper
- [x] data.sql : 초기 데이터 삽입
- [x] `spring.sql.init.mode : always` : 항상 SQL 데이터베이스를 초기화

## 3. ExceptionHandler 구현
- [x] 사용자 정의 exception 구현
  - [x] ProductNotFoundException: 삭제, 수정하려는 상품을 찾을 수 없을 때 발생하는 예외   
  - [x] ProductAlreadyExistsException: 제품이 이미 존재할 때 발생하는 예외
  - [x] InvalidProductDataException: 입력하는 상품 데이터 값이 유효하지 않을 때 발생하는 예외
- [x] Global Exception 핸들러 구현

## 4. 테스트 코드 작성
- [x] ProductTest : 1단계에서 작성한 테스트를 3단계 테스트 코드로 리팩토링

