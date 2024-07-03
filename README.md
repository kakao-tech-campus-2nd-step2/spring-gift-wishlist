# spring-gift-wishlist

## 기능 요구 사항

### week1/step1

상품을 조회, 추가, 수정, 삭제할 수 있는 간단한 HTTP API를 구현한다.

- HTTP 요청과 응답은 JSON 형식으로 주고받는다.
- 현재는 별도의 데이터베이스가 없으므로 적절한 자바 컬렉션 프레임워크를 사용하여 메모리에 저장한다.

### week1/step2

상품을 조회, 추가, 수정, 삭제할 수 있는 관리자 화면을 구현한다.

- Thymeleaf를 사용하여 서버 사이드 렌더링을 구현한다.
- 기본적으로는 HTML 폼 전송 등을 이용한 페이지 이동을 기반으로 하지만, 자바스크립트를 이용한 비동기 작업에 관심이 있다면 이미 만든 상품 API를 이용하여 AJAX 등의 방식을 적용할 수 있다.
- 상품 이미지의 경우, 파일을 업로드하지 않고 URL을 직접 입력한다.

### week1/step3

자바 컬렉션 프레임워크를 사용하여 메모리에 저장하던 상품 정보를 데이터베이스에 저장한다.

- 프로그래밍 요구사항
  - 메모리에 저장하고 있던 모든 코드를 제거하고 H2 데이터베이스를 사용하도록 변경한다.
  - 사용하는 테이블은 애플리케이션이 실행될 때 구축되어야 한다.

### week2/step1

상품을 추가하거나 수정하는 경우, 클라이언트로부터 잘못된 값이 전달될 수 있다. 잘못된 값이 전달되면 클라이언트가 어떤 부분이 왜 잘못되었는지 인지할 수 있도록 응답을 제공한다.

- 상품 이름은 공백을 포함하여 최대 15자까지 입력할 수 있다.
- 사용 가능 특수 문자
  - `(`, `)`, `[`, `]`, `+`, `-`, `&`, `/`, `_`
  -  이외 특수 문자 사용 불가
- `카카오`가 포함된 문구는 담당 MD와 협의한 경우에만 사용할 수 있다.

---



## 기능 목록

> ✔️ **일러두기**
> - [ ] : 미구현한 기능
> - [x] : 구현한 기능

### 상품 도메인

#### 모델 설계

- [x] 상품을 표현하는 도메인 객체 생성하기
  - 상품의 구성요소
    - id: int
    - name: string
    - price: int
    - imageUrl: string
- [x] 상품을 저장하는 데이터베이스 연동하기
  - [x] 데이터베이스 관리 레포지토리 생성하기
  - H2 데이터베이스 사용하기

#### 기능 설계(컨트롤러 설계)

- [x] 상품을 관리하는 컨트롤러 생성하기
  - [x] 상품을 추가하는 API 구현하기
  - [x] 상품 리스트를 조회하는 API 구현하기
  - [x] 상품을 수정하는 API 구현하기
  - [x] 상품을 삭제하는 API 구현하기
- [x] 레포지토리를 이용해 특정 서비스를 제공해주는 서비스 생성하기

#### 예외, 검증 설계

- [x] 커스텀 예외 추가하기
  - [x] 예외 핸들러 기능 추가하기
- [ ] 사용자 입력을 검증하기
  - [x] 데이터베이스 조건 수정하기
  - [ ] 검증기 만들기
  - [x] 모델에서 검증하기 
  - [x] 컨트롤러에서 검증하기

#### Server-side Renderings

- [x] 상품의 조회, 추가, 수정, 삭제가 가능한 홈페이지 생성
- [x] 상품을 조회하는 홈페이지 생성
- [x] 상품을 추가하는 홈페이지 기능 추가
- [x] 상품을 수정하는 홈페이지 기능 추가
- [x] 상품 등록시 중복 발생하면 알려주는 홈페이지 추가
- [x] 서버사이드 렌더링 담당하는 컨트롤러 추가

---



## API 명세서

- 모든 응답에는 json 형식의 body가 존재한다.

### 상품 리스트 조회 API

#### Request

| Method | URL           | Path param | Path variable | Body |
|--------|---------------|------------|---------------|------|
| GET    | /api/products | -          | -             | -    |


#### Response

- Status
  - 200 OK
- Body
    
  ```json
  {
    "timestamp": "2024-01-01T00:00:00.0000000",
    "status": 200,
    "products": [
      {
        "id": 8146027,
        "name": "아이스 카페 아메리카노 T",
        "price": 4500,
        "imageUrl": "https://st.kakaocdn.net/product/gift/product/20231010111814_9a667f9eccc943648797925498bdd8a3.jpg"
      },
      {
        "id": 1,
        "name": "name",
        "price": 2000,
        "imageUrl": "url"
      },
      {},
      {}
    ]
  }
  ```

---

### 상품 추가 API

#### Request

| Method | URL           | Path param | Path variable | Body     |
|--------|---------------|------------|---------------|----------|
| POST   | /api/products | -          | -             | Yes(*제약) |
 
#### Request/Body

[^yes]: hello

```json

{
  "name": "Product name",
  "price": 10000,
  "ImageUrl": "http://~"
}
```

#### Request 제약조건

- 상품의 이름은 공백 포함 15자만 가능함
- 사용 가능 특수문자는 다음과 같음
  - `(`, `)`, `[`, `]`, `+`, `-`, `&`, `/`, `_`
  -  이외 특수 문자 사용 불가
- `카카오`가 포함된 문구는 담당 MD와 협의한 경우에만 사용할 수 있음

#### Response(success)

- Status
  - 201 Created
- Header
  - Location: 생성된 리소스의 REST API URI
    - 예: `Location: /api/products/5`
- Body
  - 생성된 리소스의 id 및 생성된 정보를 응답함

  ```json
  {
    "timestamp": "2024-01-01T00:00:00.0000000",
    "status": 201,
    "created-product": {
      "id": 23,
      "name": "Product name",
      "price": 10000,
      "ImageUrl": "http://~"
    }
  }
  ```

#### Response(fail)

기등록된 상품 중 name, price, ImageUrl이 모두 일치할 경우 발생함

- Status
  - 409 Conflict
- Body

  ```json
  {
    "timestamp": "2024-01-01T00:00:00.0000000",
    "status": 409,
    "message": "The product already exists."
  }
  ```

---

### 상품 수정 API

#### Request

| Method | URL                | Path param | Path variable | Body |
|--------|--------------------|------------|---------------|------|
| PUT    | /api/products/{id} | -          | yes{id: int}  | yes(*제약)  |

#### Request/Body

```json
{
  "name": "Product name",
  "price": 10000,
  "ImageUrl": "http://~"
}
```

#### Request 제약조건

- 상품의 이름은 공백 포함 15자만 가능함
- 사용 가능 특수문자는 다음과 같음
  - `(`, `)`, `[`, `]`, `+`, `-`, `&`, `/`, `_`
  -  이외 특수 문자 사용 불가
- `카카오`가 포함된 문구는 담당 MD와 협의한 경우에만 사용할 수 있음

#### Response(sucess)

- Status
  - 200 OK
- Body

  ```json
  {
    "timestamp": "2024-01-01T00:00:00.0000000",
    "status": 200
  }
  ```

#### Response(fail)

- 수정하려는 상품이 존재하지 않을 경우 발생
- Status
  - 404 NOT FOUND
- Body

  ```json
  {
    "timestamp": "2024-01-01T00:00:00.0000000",
    "status": 404,
    "message": "The product was not found."
  }
  ```

---

### 상품 삭제 API

#### Request

| Method | URL                | Path param | Path variable | Body |
|--------|--------------------|------------|---------------|------|
| DELETE | /api/products/{id} | -          | yes{id: int}  | -    |

#### Response(sucess)

- Status
  - 200 OK
- Body

  ```json
  {
    "timestamp": "2024-01-01T00:00:00.0000000",
    "status": 200
  }
  ```

#### Response(fail)

- 삭제하려는 상품이 존재하지 않을 경우 발생
- Status
  - 404 NOT FOUND
- Body

  ```json
  {
    "timestamp": "2024-01-01T00:00:00.0000000",
    "status": 404,
    "message": "The product was not found."
  }
  ```