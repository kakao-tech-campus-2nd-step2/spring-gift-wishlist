# spring-gift-wishlist

<br/>

# 1단계 - 유효성 검사 및 예외 처리

## 요구 사항 정의

잘못된 요청에 대한 응답 처리

### 상품 추가

- 잘못된 상품 이름 → MethodArgumentNotValidException 발생
    - 공백을 포함하여 최대 15자
    - ( ), [ ], +, -, &, /, _ 특수 문자만 가능
    - "카카오"가 포함된 문구는 담당 MD와 협의한 경우에만 사용
- 잘못된 상품 가격 → MethodArgumentNotValidException 발생
    - 양수만 가능
- 잘못된 상품 이미지 → MethodArgumentNotValidException 발생
    - null 제외, 빈 문자열 가능

### 상품 수정

- 잘못된 상품 id → NoSuchProductException 발생
- 잘못된 상품 이름 → MethodArgumentNotValidException 발생
- 잘못된 상품 가격 → MethodArgumentNotValidException 발생
- 잘못된 상품 이미지 → MethodArgumentNotValidException 발생

### 상품 삭제

- 잘못된 상품 id → NoSuchProductException 발생