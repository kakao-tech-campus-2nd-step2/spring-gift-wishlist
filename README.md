# spring-gift-wishlist

## 1단계(유효성 검사 및 예외 처리) 요구사항

### 기능 요구사항

상품을 추가하거나 수정하는 경우, 클라이언트로부터 잘못된 값이 전달될 수 있다. 잘못된 값이 전달되면 클라이언트가 어떤 부분이 왜 잘못되었는지 인지할 수 있도록 응답을 제공한다.

- 상품 이름은 공백을 포함하여 최대 15자까지 입력할 수 있다.
- 특수 문자
  - 가능: ( ), [ ], +, -, &, /, _
  - 그 외 특수 문자 사용 불가
- "카카오"가 포함된 문구는 담당 MD와 협의한 경우에만 사용할 수 있다.



### 1단계 기능 목록

* 상품 추가 및 수정 기능에서의 유효성 검증
  * [ ] 상품 이름은 공백을 포함하여 최대 15자까지 입력 가능
    * [ ] `InvalidProductNameException` 처리
  * [ ] 유효한 특수 문자만 입력 가능 - 정규식 사용(`^[a-zA-Z가-힣0-9 ()\[\]\+\-\&/\_]*$`)
    * [ ] `InvalidProductNameException` 처리
  * [ ] "카카오"가 포함된 문구가 입력된다면 예외 처리 후 클라이언트에게 안내
    * [ ] `RestrictedKeywordException` 처리
* Exception 및 ExceptionHandler 정의
  * [x] `InvalidProductNameException` 정의
  * [x] `RestrictedKeywordException` 정의
  * [ ] ExceptionHandler 정의
    * [x] 상품 이름 길이에 관한 예외(`InvalidProductNameException`)
    * [x] 상품 이름 특수문자에 관한 예외(`InvalidProductNameException`)
    * [ ] 특정 문구에 관한 예외(`RestrictedKeywordException`)



---

# 1주차 과제 요구사항(spring-gift-product)

## 1단계(상품 API) 요구 사항

### 과제 진행 요구 사항

* 미션은 선물하기 상품 관리 저장소를 포크하고 클론하는 것으로 시작한다.
* 온라인 코드 리뷰 요청 1단계 문서를 참고하여 실습 환경을 구축한다.
  1. 미션 시작 버튼을 클릭하여 미션을 시작한다.
  2. 저장소에 GitHub 사용자 이름으로 브랜치가 생성되었는지 확인한다.
  3. 저장소를 내 계정으로 포크한다.
* 기능을 구현하기 전 README.md에 구현할 기능 목록을 정리해 추가한다.
* Git의 커밋 단위는 앞 단계에서 README.md에 정리한 기능 목록 단위로 추가한다.
  * AngularJS Git Commit Message Conventions을 참고해 커밋 메시지를 작성한다.



### 기능 요구 사항

상품을 조회, 추가, 수정, 삭제할 수 있는 간단한 HTTP API를 구현한다.

* HTTP 요청과 응답은 JSON 형식으로 주고받는다.
* 현재는 별도의 데이터베이스가 없으므로 적절한 자바 컬렉션 프레임워크를 사용하여 메모리에 저장한다.

#### 예시

**Request**

```http
GET /api/products HTTP/1.1
```

**Response**

```http
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



### 1단계 기능 목록

> 패키지 분리 없이 단일 패키지로 진행. 단계를 진행하며 패키지 분리 리팩토링할 지 고려

**Product 클래스**

상품의 정보를 저장하는 객체

* 필드: `Long id`, `String name`, `int price`, `String imageUrl`
* 메서드
  * `public Product(Long id, String name, int price, String imageUrl)`



**ProductController 클래스 @RestController**

상품 조회, 추가, 수정, 삭제를 처리하는 엔드포인트 정의

* 엔드포인트
  * `GET /api/products`: 모든 상품 조회
  * `GET /api/products/{id}`: id를 통해 상품 조회
  * `POST /api/products`: JSON을 통해 새로운 상품 추가
  * `PUT /api/products/{id}`: 특정 id의 상품 수정
  * `DELETE /api/products/{id}`: 특정 id의 상품 삭제



**ProductService 클래스**

상품 조회, 추가, 수정, 삭제 기능을 실질적으로 처리하는 클래스

* 필드: `private final Map<Long, Product> products`: 상품 정보를 저장하는 저장소. <id, 상품>의 HashSet

* 메서드
  * `public List<Product> getAllProducts()`: 모든 상품 조회
  * `public Product getProductById(Long id)`: `id`를 통해 상품 조회
  * `public addProduct(Product product)`: 새로운 상품 추가
    * JSON 형식이 이상할 경우 에러 발생
  * `public Product updateProduct(Long id, Product product)`: id를 통해 상품을 검색한 후 상품 수정
    * 존재하지 않는 상품인 경우 예외 발생
  * `public void deleteProduct(Long id)`: 특정 id의 상품 삭제
    * 존재하지 않는 상품인 경우 예외 발생



---

## 2단계(관리자 화면) 요구사항

### 기능 요구 사항

상품을 조회, 추가, 수정, 삭제할 수 있는 관리자 화면을 구현한다.

- [Thymeleaf](https://www.thymeleaf.org/)를 사용하여 [서버 사이드 렌더링](https://joshua1988.github.io/vue-camp/nuxt/ssr.html)을 구현한다.
- 기본적으로는 HTML 폼 전송 등을 이용한 페이지 이동을 기반으로 하지만, 자바스크립트를 이용한 비동기 작업에 관심이 있다면 이미 만든 상품 API를 이용하여 [AJAX](https://developer.mozilla.org/ko/docs/Glossary/AJAX) 등의 방식을 적용할 수 있다.
- 상품 이미지의 경우, 파일을 업로드하지 않고 URL을 직접 입력한다.

 ### 2단계 기능 목록

> 각 페이지는 /src/resources/templates 경로에 저장

**product_list.html**

* 모든 상품을 리스트로 조회하는 메인 페이지
* 각 상품마다 update, delete 버튼 존재
  * update 버튼은 `product_add_form.html` 페이지로 이동
  * delete 버튼은 추가적인 조작없이 delete method 전송을 통해 삭제 후 메인 페이지 새로 고침
* 새 상품을 추가하는 버튼 존재
  * 버튼을 누르면 `product_edit_form.html` 페이지로 이동



**product_add_form.html**

* 새 상품을 추가하는 페이지
* 각 정보를 html 폼으로 입력받아 `POST /api/products`로 요청하여 저장



**product_edit_form.html**

* 상품을 수정하는 페이지
* 각 정보를 html 폼으로 입력받아 `PUT /api/products/{id}`로 요청하여 수정



## 3단계(데이터베이스 적용) 요구사항

### 3단계 기능 목록

#### 데이터베이스 구축

- [x] 기존 메모리 저장 로직 삭제
- [x] H2 데이터베이스 연결
- [x] `ProductService`의 기존 메서드들 DB 로직으로 대체
  - [x] `getAllProducts`
  - [x] `getProductById`
  - [x] `addProduct`
  - [x] `updateProduct`
  - [x] `deleteProduct`



### 기능 요구 사항

자바 컬렉션 프레임워크를 사용하여 메모리에 저장하던 상품 정보를 데이터베이스에 저장한다.

### 프로그래밍 요구 사항

- 메모리에 저장하고 있던 모든 코드를 제거하고 H2 데이터베이스를 사용하도록 변경한다.
- 사용하는 테이블은 애플리케이션이 실행될 때 구축되어야 한다.
