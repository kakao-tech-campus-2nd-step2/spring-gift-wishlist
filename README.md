# 상품 관리
## Step 2
### 요구사항
상품을 조회, 추가, 수정, 삭제할 수 있는 관리자 화면을 구현한다.
- Thymeleaf를 사용하여 서버 사이드 렌더링을 구현한다.
- 기본적으로는 HTML 폼 전송 등을 이용한 페이지 이동을 기반으로 하지만, 자바스크립트를 이용한 비동기 작업에 관심이 있다면 이미 만든 상품 API를 이용하여 AJAX 등의 방식을 적용할 수 있다.
- 상품 이미지의 경우, 파일을 업로드하지 않고 URL을 직접 입력한다.
### 변경된 프로젝트 구조
/src/main/resources/templates 에 아래와 같은 html 템플릿을 추가한다.
- product-list.html
- product-edit.html
### 구현할 기능 구현 목록
1. product-list.html
- 상품을 조회할 수 있는 페이지이다.
- edit 버튼으로 product-edit.html의 템플릿으로 넘어가 새로운 제품의 필드 값을 입력해서 추가할 수 있다.
- delete 버튼으로 조회 가능한 상품을 삭제할 수 있다.
2. product-edit.html
- 추가 버튼으로 넘어가게 되는 페이지이다.
- 상품을 수정하거나 추가할 수 있다.
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

