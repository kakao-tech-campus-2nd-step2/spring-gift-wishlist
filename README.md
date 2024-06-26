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
- 상품 조회 
```http request
GET api/products/{productId}
```
- 상품 추가
```http request
POST api/products
```
- 상품 수정
```http request
PUT api/products/{productId}
```
- 상품 삭제
```http request
DELETE api/products/{productId}
```