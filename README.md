# spring-gift-product

## 상품 DB

### DB 정보
- 자료구조: Map<Long, Product>
- 저장형식: HashMap

### 속성
- id: Long - 상품의 고유 식별자
- name: String - 상품명
- price: int - 가격
- imageUrl: String - 상품 이미지 URL
- status: Boolean - 상품 활성화 상태 (true: 활성, false: 비활성)

## 기능 명세

### URL 라우팅
- 클라이언트에게 url 받기
- 해당 url에 맞는 기능 매핑해주기

### 조회
1. 대상 postId 존재하는지 조회
2. 존재하면 DB에서 데이터 가져오기
3. 데이터를 json으로 변환해서 보내기

### 추가
1. 중복 검사
   - 이름 중복 검사
     - status가 true인 상품 중에서 중복되는 이름을 가진 상품이 존재하는지 확인 
     - 실제 카카오톡 선물하기는 브랜드로 상품 구분이 가능하기 때문에, 이름 중복은 허용하는 걸로 보임
     - 하지만 현재 상품 DB에는 브랜드 칼럼이 없기 때문에 이름 중복을 허용하지 않음
2. 중복된 상품이 없을 때 Product 객체에 request 데이터 담기
3. Product 객체를 DB에 저장
4. 성공/실패 메세지 보내기

### 수정
1. 대상 postId 존재 확인
2. 상품이 존재하면 Product 객체에 request 데이터 담음
3. Product 객체에서 null이 아닌 요소를 골라서 DB값 수정
4. 성공/실패 메세지 보내기

### 삭제
1. 대상 postId 존재 확인
2. 상품이 존재하면 삭제
3. 성공/실패 메세지 보내기

## API 명세

### [조회] 1. 상품 단일 조회
DB에 저장되어 있는 단일 상품 정보를 반환
#### request
- url: **`[GET] /api/product?product={productId}`**
- queryString
  - productId : 검색 제품의 productId
- body : 없음

#### response
- 응답 성공
  - HTTP 코드
  - response parameter
  ```
  - product
      - id
      - name
      - price
      - imageUrl
  ```

응답 실패
- HTTP 코드
- 실패 원인 메세지

### [조회] 2. 상품 전체 조회
DB에 저장되어 있는 상품 전체를 리스트로 반환
#### request
- url: **`[GET] /api/products`**
- body / queryString : 없음

#### response
응답 성공
- HTTP 코드
- response parameter
    ```
    - productList
      - product
        - id
        - name
        - price
        - imageUrl
    ```


응답 실패
- HTTP 코드
- 실패 원인 메세지


### [추가] 1. 단일 상품 추가
새로운 단일 상품 정보를 DB에 추가
#### request
- url: **`[POST] /api/product`**
- queryString:없음
- body
    ```
  - product
        - name
        - price
        - imageUrl
  ```
    

#### response
응답 성공
- HTTP 코드
- 저장한 상품의 productId

응답 실패
- HTTP 코드
- 실패 메세지

### [추가] 2. 여러 상품 추가
두 개 이상의 상품 정보를 DB에 추가
#### request
- url: **`[POST] /api/products`**
- queryString:없음
- body
    ```
    - productList
          - product
              - name
              - price
              - imageUrl
    ```

#### response
응답 성공
- HTTP 코드
- 저장한 상품의 productId list

응답 실패
- HTTP 코드
- 실패 메세지


### [수정] 1. 단일 상품 수정
이미 DB에 존재하는 단일 상품 정보를 수정
#### request
- url: **`[PATCH] /api/product`**
- queryString:없음
- body
  - 수정할 항목은 값 추가
  - 수정하지 않는 항목은 비어둠
      ```
      - product
            - productId : 수정할 상품 ID
            - name : 수정값 (null 허용)
            - price : 수정값 (null 허용)
            - imageUrl: 수정값 (null 허용)
      ```

#### response
응답 성공
- HTTP 코드
- 수정한 상품의 productId

응답 실패
- HTTP 코드
- 실패 메세지

### [수정] 2. 여러 상품 수정
이미 DB에 존재하는 2개 이상의 상품 정보를 한 번에 수정
#### request
- url: **`[PATCH] /api/products`**
- queryString:없음
- body
    - 수정할 항목은 값 추가
    - 수정하지 않는 항목은 비어둠
        ```
        - productList
            - product
              - productId : 수정할 상품 ID
              - name : 수정값 (null 허용)
              - price : 수정값 (null 허용)
              - imageUrl: 수정값 (null 허용)
        ```

#### response
응답 성공
- HTTP 코드
- 수정한 상품의 productId List

응답 실패
- HTTP 코드
- 실패 메세지


### [삭제] 1. 단일 상품 삭제
이미 DB에 존재하는 단일 상품 정보를 삭제
#### request
- url: **`[PATCH] /api/product/deleted?product={productId}`**
- queryString: 삭제할 상품 productId
- body : 없음

#### response
응답 성공
- HTTP 코드
- 성공 메세지

응답 실패
- HTTP 코드
- 실패 메세지

