# spring-gift-wishlist

## step 1

- 기능 요구 사항
    - 상품 이름은 공백을 포함하여 최대 15자까지 입력할 수 있다.
    - 특수 문자 가능: ( ), [ ], +, -, &, /, _
    - 그 외 특수 문자 사용 불가
    - "카카오"가 포함된 문구는 담당 MD와 협의한 경우에만 사용할 수 있다.
- 힌트
    - validation
        - spring-boot-starter-validation 의존성을 명시적으로 추가한다.
        - implementation 'spring-boot-starter-validation'