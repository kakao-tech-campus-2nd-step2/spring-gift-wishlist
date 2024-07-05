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

### week2/step2

사용자가 회원 가입, 로그인, 추후 회원별 기능을 이용할 수 있도록 구현한다.

- 회원은 이메일과 비밀번호를 입력하여 가입한다.
- 토큰을 받으려면 이메일과 비밀번호를 보내야 하며, 가입한 이메일과 비밀번호가 일치하면 토큰이 발급된다.
- 토큰을 생성하는 방법에는 여러 가지가 있다. 방법 중 하나를 선택한다.
- (선택) 회원을 조회, 추가, 수정, 삭제할 수 있는 관리자 화면을 구현한다.

아래 예시와 같이 HTTP 메시지를 주고받도록 구현한다.

#### 회원 가입/Request

```http request
POST /members/register HTTP/1.1
content-type: application/json
host: localhost:8080

{
  "email": "admin@email.com",
  "password": "password"
}
```

#### 회원가입/Response

```http request
HTTP/1.1 200
Content-Type: application/json

{
  "token": ""
}
```

#### 로그인/Request

```http request
POST /members/login HTTP/1.1
content-type: application/json
host: localhost:8080

{
  "email": "admin@email.com",
  "password": "password"
}
```


#### 로그인/Response

```http request
HTTP/1.1 200
Content-Type: application/json

{
  "token": ""
}
```


---



## 기능 목록

> ✔️ **일러두기**
> - [ ] : 미구현한 기능
> - [x] : 구현한 기능

### 상품 도메인

#### 모델 설계

- [x] 상품을 표현하는 도메인 객체
  - 상품의 구성요소
    - id: int
    - name: string
    - price: int
    - imageUrl: string
- [x] 상품을 저장하는 데이터베이스 연동
  - [x] 데이터베이스(H2 사용) 접근 Repository 
  - [x] 스키마 SQL 및 예제 데이터 삽입 SQL 구비

#### 기능 설계(컨트롤러 설계)

- [x] 상품을 추가하는 API
- [x] 상품 리스트를 조회하는 API
- [x] 상품을 수정하는 API
- [x] 상품을 삭제하는 API

#### 예외, 검증 설계

- [x] 커스텀 예외 및 예외 핸들링
- [x] 사용자 입력 검증

#### Server-side Renderings

- [x] 상품의 조회, 추가, 수정, 삭제가 가능한 홈페이지 생성
- [x] 상품을 조회하는 홈페이지
- [x] 상품을 추가하는 홈페이지
- [x] 상품을 수정하는 홈페이지
- [x] 상품 등록시 중복 발생하면 알려주는 홈페이지
- [x] 서버사이드 렌더링 담당하는 컨트롤러

### 유저 도메인

#### 모델 설계

- [x] 유저를 표현하는 도메인 객체
  - 유저의 구성요소
    - email: string (primary key)
    - password: string
      - 해시함수를 한번 거친다.
    - permission: string
      - `user`, `admin` 중 하나가 가능
- [x] 유저를 저장하는 데이터베이스 연동
  - [x] 데이터베이스(H2 사용) 접근 Repository
  - [x] 스키마 SQL 및 예제 데이터 삽입 SQL 구비

#### 기능 설계(컨트롤러 및 서비스 설계)

- [x] 회원가입을 하는 API
- [x] 로그인하는 API
- [ ] 유저 정보를 수정하는 API
- [ ] 유저를 삭제하는 API

#### 예외, 검증 설계

- [x] 커스텀 예외 및 예외 핸들링

#### Server-side Renderings

- [ ] 회원가입하는 홈페이지
- [ ] 로그인하는 홈페이지

---



## API 명세서/상품 도메인

- 모든 응답에는 json 형식의 body가 존재한다.

### 상품 리스트 조회 API

#### 상품 리스트 조회 API/Request

| Method | URL           | Path param | Path variable | Body |
|--------|---------------|------------|---------------|------|
| GET    | /api/products | -          | -             | -    |


#### 상품 리스트 조회 API/Response

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

#### 상품 추가 API/Request

| Method | URL           | Path param | Path variable | Body     |
|--------|---------------|------------|---------------|----------|
| POST   | /api/products | -          | -             | Yes(*제약) |
 
#### 상품 추가 API/Request/Body

[^yes]: hello

```json

{
  "name": "Product name",
  "price": 10000,
  "ImageUrl": "http://~"
}
```

#### 상품 추가 API/Request 제약조건

- 상품의 이름은 공백 포함 15자만 가능함
- 사용 가능 특수문자는 다음과 같음
  - `(`, `)`, `[`, `]`, `+`, `-`, `&`, `/`, `_`
  -  이외 특수 문자 사용 불가
- `카카오`가 포함된 문구는 담당 MD와 협의한 경우에만 사용할 수 있음

#### 상품 추가 API/Response(success)

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

#### 상품 추가 API/Response(fail)

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

#### 상품 추가 API/Response(fail)

등록하려는 상품의 `name` 유효성을 만족하지 못한 경우 발생함

- Status
  - 400 Bad Request
- Body

  ```json
  {
    "timestamp": "2024-01-01T00:00:00.0000000",
    "status": 400,
    "message": "이름 형식이 올바르지 않습니다."
  }
  ```

---

### 상품 수정 API

#### 상품 수정 API/Request

| Method | URL                | Path param | Path variable | Body |
|--------|--------------------|------------|---------------|------|
| PUT    | /api/products/{id} | -          | yes{id: int}  | yes(*제약)  |

#### 상품 수정 API/Request/Body

```json
{
  "name": "Product name",
  "price": 10000,
  "ImageUrl": "http://~"
}
```

#### 상품 수정 API/Request 제약조건

- 상품의 이름은 공백 포함 15자만 가능함
- 사용 가능 특수문자는 다음과 같음
  - `(`, `)`, `[`, `]`, `+`, `-`, `&`, `/`, `_`
  -  이외 특수 문자 사용 불가
- `카카오`가 포함된 문구는 담당 MD와 협의한 경우에만 사용할 수 있음

#### 상품 수정 API/Response(sucess)

- Status
  - 200 OK
- Body

  ```json
  {
    "timestamp": "2024-01-01T00:00:00.0000000",
    "status": 200
  }
  ```

#### 상품 수정 API/Response(fail)

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

#### 상품 수정 API/Response(fail)

등록하려는 상품의 `name` 유효성을 만족하지 못한 경우 발생함

- Status
  - 400 Bad Request
- Body

  ```json
  {
    "timestamp": "2024-01-01T00:00:00.0000000",
    "status": 400,
    "message": "이름 형식이 올바르지 않습니다."
  }
  ```

---

### 상품 삭제 API

#### 상품 삭제 API/Request

| Method | URL                | Path param | Path variable | Body |
|--------|--------------------|------------|---------------|------|
| DELETE | /api/products/{id} | -          | yes{id: int}  | -    |

#### 상품 삭제 API/Response(sucess)

- Status
  - 200 OK
- Body

  ```json
  {
    "timestamp": "2024-01-01T00:00:00.0000000",
    "status": 200
  }
  ```

#### 상품 삭제 API/Response(fail)

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
  
---



## API 명세서/유저 도메인

### 회원가입 API

#### 회원가입 API/Request

| Method | URL                   | Path param | Path variable | Body |
|--------|-----------------------|------------|---------------|------|
| POST   | /api/members/register | -          | -             | yes  |

#### 회원가입 API/Request/Body

```json
{
  "email": "admin@email.com",
  "password": "password"
}
```

#### 회원가입 API/Response(sucess)

- Status
  - 200 OK
- Body

  ```json
  {
    "timestamp": "2024-01-01T00:00:00.0000000",
    "status": 200,
    "token": "your-token-is-here"
  }
  ```

#### 회원가입 API/Response(fail)

- Status
  - 409 Conflict
- Body

  ```json
  {
    "timestamp": "2024-01-01T00:00:00.0000000",
    "status": 409,
    "message": "Your email already registered. Retry with other one."
  }
  ```

---



### 로그인 API

#### 로그인 API/Request

| Method | URL                | Path param | Path variable | Body |
|--------|--------------------|------------|---------------|------|
| POST   | /api/members/login | -          | -             | yes  |

#### 로그인 API/Request/Body

```json
{
  "email": "admin@email.com",
  "password": "password"
}
```

#### 로그인 API/Response(sucess)

- Status
  - 200 OK
- Body

  ```json
  {
    "timestamp": "2024-01-01T00:00:00.0000000",
    "status": 200,
    "token": "your-token-is-here"
  }
  ```

#### 로그인 API/Response(fail)

- Status
  - 403 Forbidden
- Body

  ```json
  {
    "timestamp": "2024-01-01T00:00:00.0000000",
    "status": 400,
    "message": "Incorrect your email or password. Try again."
  }
  ```