# spring-gift-product

## 기능 요구 사항 (step1)

상품을 조회, 추가, 수정, 삭제할 수 있는 간단한 HTTP API를 구현한다.

- HTTP 요청과 응답은 JSON 형식으로 주고받는다.
- 현재는 별도의 데이터베이스가 없으므로 적절한 자바 컬렉션 프레임워크를 사용하여 메모리에 저장한다.

## 기능 요구 사항 2 (step2)

상품을 조회, 추가, 수정, 삭제할 수 있는 관리자 화면을 구현한다.

- Thymeleaf를 사용하여 서버 사이드 렌더링을 구현한다.
- 기본적으로는 HTML 폼 전송 등을 이용한 페이지 이동을 기반으로 하지만, 자바스크립트를 이용한 비동기 작업에 관심이 있다면 이미 만든 상품 API를 이용하여 AJAX 등의 방식을 적용할 수 있다.
- 상품 이미지의 경우, 파일을 업로드하지 않고 URL을 직접 입력한다.

## 기능 요구사항 3 (step3)

자바 컬렉션 프레임워크를 사용하여 메모리에 저장하던 상품 정보를 데이터베이스에 저장한다.

## 프로그래밍 요구사항 (step3)

- 메모리에 저장하고 있던 모든 코드를 제거하고 H2 데이터베이스를 사용하도록 변경한다.
- 사용하는 테이블은 애플리케이션이 실행될 때 구축되어야 한다.

## 기능 목록

> ✔️ **일러두기**
> - [ ] : 미구현한 기능
> - [x] : 구현한 기능

### 설계

#### 모델 설계

- [x] 상품을 표현하는 객체를 생성하기
  - 상품의 구성요소
    - id: int
    - name: string
    - price: int
    - imageUrl: string
- [x] 상품을 저장하는 데이터베이스 표현하기
  - 해시맵으로 표현하기

#### 기능 설계(컨트롤러 설계)

- [x] 상품을 추가하는 API 구현하기
- [x] 상품 리스트를 조회하는 API 구현하기
- [x] 상품을 수정하는 API 구현하기
- [x] 상품을 삭제하는 API 구현하기

#### 기타 설계 구현사항

- [x] 상품을 관리하는 컨트롤러 생성하기
- [x] 데이터베이스 관리 레포지토리 생성하기
- [x] 레포지토리를 이용해 특정 서비스를 제공해주는 서비스 생성하기
- [x] 커스텀 예외 & 예외 핸들러 기능 추가하기

### 서버 사이드 렌더링

- [x] 상품의 조회, 추가, 수정, 삭제가 가능한 홈페이지 생성
- [x] 상품을 조회하는 홈페이지 생성
- [x] 상품을 추가하는 홈페이지 기능 추가
- [x] 상품을 수정하는 홈페이지 기능 추가
- [x] 상품 등록시 중복 발생하면 알려주는 홈페이지 추가
- [x] 서버사이드 렌더링 담당하는 컨트롤러 추가

### H2로의 데이터베이스 전환

- [x] application.properties 수정하기
- [x] ProductRepository를 메모리 대신 데이터베이스와 소통하도록 리팩터링하기

---



## API 명세서

### 상품 리스트 조회 API

- Request

| Method | URL           | Path param | Path variable | Body |
|--------|---------------|------------|---------------|------|
| GET    | /api/products | -          | -             | -    |


- Response

| Status | Body |
|--------|------|
| 200 OK | Yes  |

- Response/Body 

```json
[
  {
    "id": 8146027,
    "name": "아이스 카페 아메리카노 T",
    "price": 4500,
    "imageUrl": "https://st.kakaocdn.net/product/gift/product/20231010111814_9a667f9eccc943648797925498bdd8a3.jpg"
  },
  { "id": 1, "name":  "name", "price": 2000, "imageUrl": "url" },
  {},
  {}
]
```

---

### 상품 추가 API

- Request

| Method | URL           | Path param | Path variable | Body |
|--------|---------------|------------|---------------|------|
| POST   | /api/products | -          | -             | Yes  |

- Request/Body

```json
{
  "name": "Product name",
  "price": 10000,
  "ImageUrl": "http://~"
}
```

- Response(success)

| Status      | Body |
|-------------|------|
| 201 Created | Yes  |

- Response(success)/Body
  - 생성된 리소스의 id를 응답함

```json
{
  "id": 23
}
```

- Response(fail)
  - 기등록된 상품 중 name, price, ImageUrl이 모두 일치할 경우 발생

| Status       | Body |
|--------------|------|
| 409 Conflict | -    |

---

### 상품 수정 API

- Request

| Method | URL                | Path param | Path variable | Body |
|--------|--------------------|------------|---------------|------|
| POST   | /api/products/{id} | -          | yes{id: int}  | yes  |

- Request/Body
```json
{
  "name": "Product name",
  "price": 10000,
  "ImageUrl": "http://~"
}
```

- Response(sucess)

| Status | Body |
|--------|------|
| 200 OK | -    |

- Response(fail)
  - 수정하려는 상품이 존재하지 않을 경우 발생


| Status        | Body |
|---------------|------|
| 404 NOT FOUND | -    |

---

### 상품 삭제 API

- Request

| Method | URL                | Path param | Path variable | Body |
|--------|--------------------|------------|---------------|------|
| DELETE | /api/products/{id} | -          | yes{id: int}  | -    |

- Response(sucess)

| Status | Body |
|--------|------|
| 200 OK | -    |

- Response(fail)
    - 삭제하려는 상품이 존재하지 않을 경우 발생

| Status        | Body |
|---------------|------|
| 404 NOT FOUND | -    |