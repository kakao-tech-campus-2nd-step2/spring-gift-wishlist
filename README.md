# spring-gift-wishlist
# spring-gift-wishlist
## step 1
### 기능 요구 사항
잘못된 값이 전달 되면 클라이언트가 어떤 부분이 왜 잘못 되었는 지 인지할 수 있도록 응답을 제공 한다.
- 상품 이름은 공백을 포함하여 최대 15자까지 입력할 수 있다.
- 특수 문자 가능: ( ), [ ], +, -, &, /, _ (그 외 특수 문자 사용 불가)
- `카카오`가 포함된 문구는 담당 MD와 협의한 경우에만 사용할 수 있다.

### 구현할 기능 목록
DTO에서 @Valid를 통한 검증
- [X] 상품 이름 길이 검증
- [X] 상품 특수 문자 포함 검증
- [X] MD 동의에 따른 `카카오` 이름 포함 검증

예외 처리
- [X] GlobalExceptionHandler 작성