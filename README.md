# spring-gift-product
***

## 기능 요구사항
상품을 조회, 추가, 수정, 삭제할 수 있는 간단한 HTTP API를 구현한다.
- HTTP 요청과 응답은 JSON 형식으로 주고받는다.
- 현재는 별도의 데이터 베이스가 없으므로, 적절한 자바 컬렉션 프레임워크를 사용하여 메모리에 저장한다.
- 아래 예시와 같이 HTTP 메세지를 주고 받도록 구현한다.
```http request
GET /api/proudcts HTTP/1.1
```
```
HTTP/1.1 200
Content-Type: application/json

[
  {
    "id": 8146027,
    "name": "아이스 카페 아메리카노 T",
    "price": 4500,
    "imageUrl": "https://st.kakaocdn.net/product/gift/product/20231010111814_9a667f9eccc943648797925498bdd8a3.jpg"
  }
]
```

## 힌트
상품 데이터 관리
- 현재는 별도의 데이터베이스가 없으므로 적절한 컬렉션을 이용하여 메모리에 저장한다.
```java
public class ProductController{
    private final Map<Long, Product> product = new HashMap<>();
}
```

## 구현 목록
### STEP1
- 상품 조회 
```
GET api/products/{productId}
```
- 상품 추가
```
POST api/products
```
- 상품 수정
```
PUT api/products/{productId}
```
- 상품 삭제
```
DELETE api/products/{productId}
```
---
### STEP2
### 관리자 페이지
- 상품 목록 조회
  - manage-product : 상품 목록 조회 페이지
```
GET /api/manage/products
```
- 상품 삭제
```
POST /api/manage/products/delete/{productId}
```
- 상품 수정
  - product-update-from : 상품 정보 수정 페이지
```
GET /api/manage/products/update/{productId}
POST /api/manage/products/update/{productId}
```
- 상품 추가
  - product-addition-from : 상품 추가 페이지
```
GET /api/manage/products/add
POST /api/manage/products/add
```
---
### STEP3
- h2 database 사용을 위한 환경설정
- ProductDao 클래스 추가
  - 모든 상품 조회: selectAllProduct
  - 특정 상품 조회: selectOneProduct
  - 상품 정보 수정: updateProduct
  - 새로운 상품 등록: insertProduct
  - 상품 삭제: deleteProduct