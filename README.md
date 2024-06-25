# 상품 관리
## Step 1
### 요구사항
상품을 조회, 추가, 수정, 삭제할 수 있는 간단한 HTTP API를 구현한다.
- HTTP 요청과 응답은 JSON 형식으로 주고받는다.
- 현재는 별도의 데이터베이스가 없으므로 적절한 자바 컬렉션 프레임워크를 사용하여 메모리에 저장한다.
- 상품 데이터 관리 : 현재는 별도의 데이터베이스가 없으므로 적절한 컬렉션을 이용하여 메모리에 저장한다.
### 프로젝트 구조
- Application.java
- Product.java
- ProductController.java
### 구현할 기능 구현 목록
1. Application.java
- MVC 패턴 중 main의 역할을 한다.
- @SpringBootApplication 을 사용하여 스프링 부트 어플리케이션 임을 정의한다.
2. Product.java
- MVC 패턴 중 model의 역할을 한다.
- getter/setter 의 사용을 피하기 위해 record를 사용한다.
- 예시를 통해 id, name, price, imageUrl 의 정보를 담고 있어야 한다.
3. ProductController.java
- MVC 패턴 중 controller의 역할을 한다.
- 제품 목록 전체 조회 (GET)
- 제품 목록 선택 조회 (GET)
- 새로운 제품을 추가 (POST)
- 새로운 제품을 수정 (PUT)
- 새로운 제품을 삭제 (DELETE)

