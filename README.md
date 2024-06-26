# 구현할 기능 목록
## 1. 상품 옵션 추가
- [x] create_product.html : 상품 옵션 추가 기능 구현
- [x] 전체적인 웹 페이지 ui 변경

## 2. JdbcTemplate 활용
- [x] application.yml : h2 database 접속 정보 설정
- [x] schema.sql : 데이터베이스의 테이블 설계
- [x] ProducRepository : JdbcTemplate을 활용하여 CRUD 구현 
- [x] DB 사용 로직의 중복 해결 by RowMapper

## 3. ExceptionHandler 구현
- [x] 사용자 정의 exception 구현
  - [x] ProductNotFoundException: 제품을 찾을 수 없을 때 발생하는 예외   
  - [x] ProductAlreadyExistsException: 제품이 이미 존재할 때 발생하는 예외
  - [x] InvalidProductDataException: 입력하는 제품 데이터가 유효하지 않을 때 발생하는 예외
- [x] Global Exception 핸들러 구현 

