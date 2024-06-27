# spring-gift-product


## Step1
1. 상품 정의
- 상품은 long type의 unique한 id를 가진다.
- 상품은 이름, 가격, 설명, 이미지 url을 가진다.
- 가격은 원 단위의 정수이다.
- 이미지 url은 url 형식이다.
2. 상품 조회
- 상품의 id로 저장되어 있는 상품의 정보를 조회한다.
- Request
  - Http Method: GET
  - Path: /gifts
  - Query Parameter: id
- Response
    - Status Code: 200
    - Data Type: JSON
    - Response Body: 이름, 가격, 설명, 이미지 url
    - 예시 JSON: `{"id": 1, "name": "상품1", "price": 1000, "description": "상품1 설명", "imageUrl": "http://image1.com"}`

    - **예외 처리**
    1. 요청이 잘못되었을 경우
        - Status Code: 400
        - Response Body: `{"message": "예외 내용 설명"}`
    2. 상품이 존재하지 않을 경우
        - Status Code: 404
        - Response Body: `{"message": "상품이 존재하지 않습니다."}` 
    3. 서버 내부 오류가 발생했을 경우
        - Status Code: 500
        - Response Body: `{"message": "예외 내용 설명"}`
3. 상품 추가
- 상품의 이름, 가격, 설명, 이미지 url을 요청받아 상품을 저장소에 추가한다.
- 요청 내용이 상품의 정의에 부합하는지 검증한다.
- Request가 성공하면 상품의 id를 Response Body로 반환한다.
- ID는 자동으로 생성된다.
- Request
  - Http Method: POST
  - Path: /gifts
  - Request Body: 이름, 가격, 설명, 이미지 url
  - Data Type: JSON
  - 예시 JSON: `{"name": "상품1", "price": 1000, "description": "상품1 설명", "imageUrl": "http://image1.com"}`
- Response
  - Status Code: 201
  - Response Body: 생성된 상품의 id
  - 예시 JSON: `{"id": 1}`
  - **예외 처리**
    1. 요청이 잘못되었을 경우
        - Status Code: 400
        - Response Body: `{"message": "예외 내용 설명"}`
    2. 서버 내부 오류가 발생했을 경우
        - Status Code: 500
        - Response Body: `{"message": "예외 내용 설명"}`
4. 상품 수정
- 상품의 id로 저장되어 있는 상품의 정보를 수정한다.
- 요청 내용이 상품의 정의에 부합하는지 검증한다.
- 수정할 수 있는 정보는 이름, 가격, 설명, 이미지 url이다.
- Request가 성공하면 상품의 id를 Response Body로 반환한다.
- Request
  - Http Method: PUT
  - Path: /gifts
  - Request Body: id, 이름, 가격, 설명, 이미지 url
  - Data Type: JSON
  - 예시 JSON: `{"id": 1, "name": "상품1", "price": 1000, "description": "상품1 설명", "imageUrl": "http://image1.com"}`
- Response
  - Status Code: 200
  - Response Body: 수정된 상품의 id
  - 예시 JSON: `{"id": 1}`
  - **예외 처리**
    1. 요청이 잘못되었을 경우
        - Status Code: 400
        - Response Body: `{"message": "예외 내용 설명"}`
    2. 상품이 존재하지 않을 경우
        - Status Code: 404
        - Response Body: `{"message": "상품이 존재하지 않습니다."}`
    3. 서버 내부 오류가 발생했을 경우
        - Status Code: 500
        - Response Body: `{"message": "예외 내용 설명"}`

5. 상품 삭제
- 상품의 id로 저장되어 있는 상품의 정보를 삭제한다.
- Request
  - Http Method: DELETE
  - Path: /gifts
  - Query Parameter: id
- Response
  - Status Code: 200
  - **예외 처리**
    1. 요청이 잘못되었을 경우
        - Status Code: 400
        - Response Body: `{"message": "예외 내용 설명"}`
    2. 상품이 존재하지 않을 경우
        - Status Code: 404
        - Response Body: `{"message": "상품이 존재하지 않습니다."}`
    3. 서버 내부 오류가 발생했을 경우
        - Status Code: 500
        - Response Body: `{"message": "예외 내용 설명"}`

## Step2

1. JDBC + H2 DB로 전환
   - Repository 구현체를 JDBC로 전환
   - 기능 구현
     1. 상품 테이블 생성
     2. 상품 추가
     3. 단일 상품 조회
     4. 전체 상품 조회
     5. 상품 수정
     6. 상품 삭제
2. 관리자 페이지 구현
    - 상품 추가, 수정, 삭제 기능을 수행할 수 있는 관리자 페이지 구현
    - 화면 기능
      - 전체 상품 목록
      - 상품 추가 버튼
      - 상품 수정 버튼
      - 상품 삭제 버튼
        - 체크박스로 삭제할 상품 선택
3. 상품 추가 페이지 구현
    - 상품 추가를 위한 페이지 구현
    - 화면 기능
      - 이름, 가격, 설명, 이미지 url 입력
      - 추가 버튼
4. 상품 수정 페이지 구현
- 상품 수정을 위한 페이지 구현
    - 화면 기능
      - 이름, 가격, 설명, 이미지 url 입력
      - 수정 버튼