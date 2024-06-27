# spring-gift-product

## 구현 기능 목록
1. H2 Database
   1. H2 Database를 사용할 수 있도록 Gradle, application.properties를 설정한다.
   2. ProductRepository가 생성되면 테이블을 생성한다.
      - 테이블은 기존의 Product클래스와 같다.
   3. 추가, 삭제, 수정, 조회 메서드를 JDBC를 사용해 관리할 수 있도록 한다.