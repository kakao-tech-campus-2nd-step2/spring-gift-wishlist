# spring-gift-wishlist
- spring-gift-product 의 step 1,2,3 에 작성되었던 코드를 옮겨옵니다. 
- spring-gift-product의 step1,2,3의 pr 링크는 다음과 같습니다.
- step1 :https://github.com/kakao-tech-campus-2nd-step2/spring-gift-product/pull/144
- step2 : https://github.com/kakao-tech-campus-2nd-step2/spring-gift-product/pull/183
- step3 : https://github.com/kakao-tech-campus-2nd-step2/spring-gift-product/compare/main...Wonmoan:spring-gift-product:step3
### 기능 요구 사항
    1.상품을 조회, 추가, 수정, 삭제할 수 있는 간단한 HTTP API를 구현한다.
    2.상품 CRUD를 수행하는 관리자 페이지 구현한다.
    3.제품에 대한 CRUD 작업을 H2 데이터베이스 콘솔로 관리한다.

- 상품 등록 기능
  상품은 이름,가격,이미지 사진에 대한 정보를 가진다.
  상품을 구분하기 위해 각 상품은 id값을 가지고 이 값은 반드시 고유한 값이어야한다.

- 상품 조회 기능
  상품은 id로 조회할 수 있고, 조회 시 상품의 이름, 가격, 이미지에 대한 정보를 반환한다.
  존재하지 않는 id를 조회하는 경우 에러메시지를 반환한다.

- 상품 수정 기능
  요청하는 정보로 상품 정보를 수정한다.
  존재하지 않는 id에 대해서 수정 요청 보내는 경우 에러메시지를 반환한다.

- 상품 삭제 기능
  id를 요청하여 해당 id를 가진 상품을 데이터베이스에서 삭제한다.
  존재하지 않는 id에 대해서 상품 삭제 요청을 보내는 경우 에러메시지를 반환한다.

## 관리자 페이지 기능 요구사항

- 상품 정보를 저장할 수 있어야 함
    - 상품명, 가격, 수량, 이미지url을 입력받아 저장할 수 있어야 함

- 상품 정보를 조회할 수 있어야 함
    - ID를 입력받아 해당 ID의 상품 정보를 조회할 수 있어야 함

- 모든 상품 정보를 조회할 수 있어야 함
    - 상품 정보를 수정할 수 있어야 함

- 상품 정보를 입력받아 가격, 수량, 이미지url을 수정할 수 있어야 함

- 상품 정보를 삭제할 수 있어야 함
    - ID를 입력받아 상품 정보를 삭제할 수 있어야 함

## 제품 정보를 관리하는 간단한 애플리케이션
- H2 데이터베이스를 사용하여 제품 정보를 저장하며, RESTful API를 통해 제품 데이터를 관리한다.

- 기능
  1.제품에 대한 CRUD 작업 (생성, 조회, 수정, 삭제)
  2.H2 데이터베이스 초기화 및 샘플 데이터 삽입
  3.제품 관리를 위한 RESTful API 제공
  4.H2 데이터베이스 콘솔을 통한 데이터베이스 관리

### REST API 사용법
    - Postman 또는 브라우저를 사용하여 다음 REST API 엔드포인트를 테스트할 수 있습니다:

    - 모든 제품 조회: GET /api/products
    - 특정 제품 조회: GET /api/products/{id}
     - 제품 생성: POST /api/products Body (JSON):
     { "name": "New Product", "price": 15.99, ,"imageUrl":"http://example.com/product1.jpg","description": "Description for new product" }
    - 제품 수정: PUT /api/products/{id} Body (JSON): { "name": "Updated Product", "price": 18.99, "description": "Updated description" }
    - 제품 삭제: DELETE /api/products/{id}

### 유효성 검사
1. 상품 이름에 공백 포함 최대 15자 까지 입력할수 있다.
2. 사용가능 특수 문자 기호 "(), [], +, -, & ,/, _ "
3. 나머지 특수기호는 사용상품이름에 사용될 수 없다.
4. "카카오" 가 포함된 문구는 담당 MD와 협의한 경우에만 사용할 수 있다. 

- 유효성 검사를 위반한 경우 경고 메세지를 클라이언트에게 제공한다.

## 회원가입 & 로그인

### 기능
- **회원 가입**: 사용자가 이메일과 비밀번호로 회원 가입을 할 수 있습니다.
- **로그인**: 등록된 사용자가 이메일과 비밀번호로 로그인하여 JWT 토큰을 받을 수 있습니다.
- **JWT 인증**: JWT 토큰을 사용한 보안 인증.

### 회원 가입
- **URL**: `/members/register`
- **메서드**: `POST`
- **헤더**:
  - `Content-Type: application/json`
- **바디**:
  ```json
  {
      "email": "user@example.com",
      "password": "password"
  }

### 로그인
- **URL**: `/members/login`
- **메서드**: `POST`
- **헤더**:
  - `Content-Type: application/json`
- **바디**:
  ```json
  {
      "email": "user@example.com",
      "password": "password"
  }

### 위시 리스트 
이전 단계에서 로그인 후 받은 토큰을 사용하여 사용자별 위시 리스트 기능을 구현한다.

위시 리스트에 등록된 상품 목록을 조회할 수 있다.
위시 리스트에 상품을 추가할 수 있다.
위시 리스트에 담긴 상품을 삭제할 수 있다.

### 회원 관리 기능
- 회원 가입: POST /members/register - 회원 가입 API 구현
- 로그인: POST /members/login - 로그인 API 구현
- 위시리스트 조회: GET /wishes - 로그인된 사용자의 위시리스트 조회
- 위시리스트 추가: POST /wishes - 새로운 상품을 위시리스트에 추가
- 위시리스트 삭제: DELETE /wishes - 위시리스트에서 상품 삭제

### 위시리스트 조회
URL: GET /wishes
Headers: Authorization: Bearer {JWT_TOKEN}
Response:
200 OK: 위시리스트 정보 반환
403 Forbidden: 인증 실패
### 위시리스트 추가
URL: POST /wishes
Headers: Authorization: Bearer {JWT_TOKEN}
Response:
200 OK: 위시리스트 추가 성공
403 Forbidden: 인증 실패
### 위시리스트 삭제
URL: DELETE /wishes
Headers: Authorization: Bearer {JWT_TOKEN}
Response:
200 OK: 위시리스트 추가 성공
403 Forbidden: 인증 실패
### 공통 request body
{
"productId": 123
}
