# spring-gift-wishlist

## step0 구현 기능

- 1주차 코드 클론
- http status 반환 기능 추가

## step1 구현 기능

- spring-boot-starter-validation 추가
- 이름을 입력받는 경우 검증
    - 길이는 0~15자
    - 한글,영어, ( ), [ ], +, -, &, /, _ 만 가능
    - "카카오"가 포함된 경우 403(Forbidden)
- price, imageUrl도 null을 허용하지 않음
- http status 반환 기능 수정
    - 성공적으로 기능 수행시 20X 반환
    - "카카오"가 포함된 경우 403(Forbidden)
    - 정규식, 문자 길이를 위반할 경우 400(Bad R

