# spring-gift-wishlist
___

## Step1 - 유효성 검사 및 예외처리
___

### 기능 요구사항
상품을 추가하거나 수정하는 경우, 클라이언트로부터 잘못된 값이 전달될 수 있다. 
잘못된 값이 전달되면 클라이언트가 어떤 부분이 왜 잘못되었는지 인지할 수 있도록 응답을 제공한다.

- 상품 이름은 공백을 포함하여 최대 15자까지 입력할 수 있다.
- 특수 문자
  - 가능: ( ), [ ], +, -, &, /, _
  - 그 외 특수 문자 사용 불가
- "카카오"가 포함된 문구는 담당 MD와 협의한 경우에만 사용할 수 있다.

### 구현할 기능 목록

- `ProductReqDto`: 상품 이름(name) 필드에 대한 유효성 검사 기능 추가 
  - 상품 이름은 1자 이상 15자 이하로 입력해야 한다.
  - 상품 이름에 '카카오' 문구가 포함되어 있으면 안된다.
  - 상품 이름은 한글, 영문, 숫자, 공백, 특수문자 ( ), [ ], +, -, &, /, _ 만 입력 가능하다.


- `ProductController`: 상품 생성 API에 대한 유효성 검사 기능 추가
  - 상품 이름이 유효하지 않은 경우, 클라이언트에게 적절한 응답을 보낸다.


- `ProductExceptionHandler`: 상품 API에 대한 예외처리 기능 추가


- `ProductErrorCode`: 상품 API에 대한 예외 코드 추가
  - `PRODUCT_NOT_FOUND`: 상품을 찾을 수 없는 경우
  - `PRODUCT_ALREADY_EXISTS`: 이미 존재하는 상품을 추가하려는 경우
  - `PRODUCT_NOT_ENOUGH_STOCK`: 상품의 재고가 부족한 경우
  - `INVALID_INPUT_VALUE_PRODUCT`: 상품 API에 잘못된 값이 전달된 경우
  - `PRODUCT_CREATE_FAILED`: 상품 생성에 실패한 경우
  - `PRODUCT_UPDATE_FAILED`: 상품 수정에 실패한 경우
  - `PRODUCT_DELETE_FAILED`: 상품 삭제에 실패한 경우

### 응답 예시


- 입력한 상품의 이름이 15자 초과, 카카오 문구 포함, 허용되지 않은 특수문자가 들어간 경우
  ```json
  {
    "type": "https://gift.com/docs/error#P004",
    "title": "Invalid input value for product",
    "status": 400,
    "detail": "상품 생성에 필요한 값이 누락되었거나 잘못되었습니다.",
    "instance": "/api/products",
    "code": "P004",
    "invalidParams": [
      {
        "field": "name",
        "message": "상품 이름은 1자 이상 15자 이하로 입력해주세요."
      },
      {
        "field": "name",
        "message": "상품 이름에 '카카오' 문구가 포함되어 있습니다. 담당 MD와 협의 후 사용해주세요."
      },
      {
        "field": "name",
        "message": "상품 이름은 한글, 영문, 숫자, 공백, 특수문자 (), [], +, -, &, /, _ 만 입력 가능합니다."
      }
    ]
  }
  ```

  

