# spring-wishlist-wishlist

# Step1 구현 기능 목록 명세

---
## 기능 목록
### 1. 상품 추가, 수정시 유효성 검증
   1. 상품 이름은 공백을 포함하여 최대 15자
   2. 특수 문자
        - 가능 : (),[],+,-,&,/,_
        - 그 외 특수 문자 사용 불가
   3. "카카오"가 포함된 문구는 담당 MD와 협의한 경우에만 사용 가능

### 2. 전역 예외 처리
1. 요청 예외 
   - 유효하지 않은 HTTP Method 요청 처리
   - 존재하지 않는 요청 처리
2. 상품 관련 
   - 존재하지 않는 상품 조회 처리

## 구현 목록
### 1. 유효성 검증
- ItemForm : Hibernate Validator 적용
- ItemController : 검증 절차 추가

### 2. 예외 처리
1. 커스텀 예외 
   - ItemNotFoundException
     - 존재하지 않는 상품 조회 요청 예외
   - BadRequestException
     - 존재하지 않는 요청 예외
2. 기본 예외
   - HttpRequestMethodNotSupportedException
3. GlobalExceptionHandler