# spring-gift-product

## 🚀 1단계 - 상품 API

### 개요
- 상품을 조회, 추가, 수정, 삭제할 수 있는 간단한 HTTP API를 구현
    - HTTP 요청과 응답은 JSON 형식으로 주고받음
    - 현재는 별도의 데이터베이스가 없으므로 적절한 자바 컬렉션 프레임워크를 사용하여 메모리에 저장

### 기능 목록
- [ ] 모든 상품 조회
- [ ] 상품 ID로 조회
- [ ] 상품 추가
- [ ] 상품 수정
- [ ] 상품 삭제

#### 1. 모든 상품 조회
- **GET /api/products**
    - 모든 상품을 조회
    - 요청 예시:
      ```http
      GET /api/products
      ```
    - 응답 예시:
      ```json
      HTTP/1.1 200 OK
      Content-Type: application/json
  
      [
        {
          "id": 1,
          "name": "아이스 카페 아메리카노 T",
          "price": 4500,
          "imageUrl": "https://example.com/image.jpg"
        }
      ]
      ```

#### 2. 상품 ID로 조회
- **GET /api/products/{id}**
    - 상품 ID로 특정 상품을 조회
    - 요청 예시:
      ```http
      GET /api/products/1
      ```
    - 응답 예시:
      ```json
      HTTP/1.1 200 OK
      Content-Type: application/json
  
      {
        "id": 1,
        "name": "아이스 카페 아메리카노 T",
        "price": 4500,
        "imageUrl": "https://example.com/image.jpg"
      }
      ```

#### 3. 상품 추가
- **POST /api/products**
    - 새로운 상품을 추가
    - 요청 예시:
      ```http
      POST /api/products
      Content-Type: application/json
  
      {
        "name": "아이스 카페 아메리카노 T",
        "price": 4500,
        "imageUrl": "https://example.com/image.jpg"
      }
      ```
    - 응답 예시:
      ```json
      HTTP/1.1 201 Created
      Content-Type: application/json
  
      {
        "id": 1,
        "name": "아이스 카페 아메리카노 T",
        "price": 4500,
        "imageUrl": "https://example.com/image.jpg"
      }
      ```

#### 4. 상품 수정
- **PUT /api/products/{id}**
    - 기존 상품을 수정합니다.
    - 요청 예시:
      ```http
      PUT /api/products/1
      Content-Type: application/json
  
      {
        "name": "아이스 카페 라떼",
        "price": 5000,
        "imageUrl": "https://example.com/newimage.jpg"
      }
      ```
    - 응답 예시:
      ```json
      HTTP/1.1 200 OK
      Content-Type: application/json
  
      {
        "id": 1,
        "name": "아이스 카페 라떼",
        "price": 5000,
        "imageUrl": "https://example.com/newimage.jpg"
      }
      ```

#### 5. 상품 삭제
- **DELETE /api/products/{id}**
    - 기존 상품을 삭제합니다.
    - 요청 예시:
      ```http
      DELETE /api/products/1
      ```
    - 응답 예시:
      ```json
      HTTP/1.1 204 No Content
      ```

### 기술 스택
- Java 21
- Spring Boot 3.3.1
- Gradle 8.4
