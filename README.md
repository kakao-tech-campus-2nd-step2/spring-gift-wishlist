# spring-gift-wishlist

### 구현할 기능 목록

## 1단계

### 유효성 검사 및 예외처리
- DTO 검증
- Controller 검증
- `@ExceptionHander`
- `@RestControllerAdvice

상품 이름
   1. 공백을 포함하여 최대 15자까지 입력
   2. 특수 문자 (),[],+,-,&,/,_ 제외하고 사용 불가
   3. "카카오"가 포함된 문구는 담당 MD와 협의한 경우에만 사용 가능
