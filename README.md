# spring-gift-wishlist
1주차 피드백 반영
- rest api
- Controller vs RestController

2주차 step1 Validation
- 상품 추가, 수정 Validation
    - 상품 이름은 공백을 포함하여 최대 15자까지 입력할 수 있다.
        - @Size(min=, max=)
    - 특수 문자
        - 가능: ( ), [ ], +, -, &, /, _
        - 그 외 특수 문자 사용 불가
        - @Pattern
    - "카카오"가 포함된 문구는 담당 MD와 협의한 경우에만 사용할 수 있다.
        - 유효성 검사 로직 추가
    - 중복 상품 추가 불가
        - 유효성 검사 로직 추가
    - @RequestBody, @RequestParam, @PathVariable 사용