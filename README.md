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
    - 정규식, 문자 길이를 위반할 경우 400

## step2 구현 기능

- 유저 객체 생성
  - 유저 리스트 조회
  - 유저 조회
  - 유저 생성
    - 이메일
      - 이메일 형식 정규식 적용
    - 비밀번호
      - 4~30자 
  - 유저 비밀번호 수정(patch)
  - 유저 삭제(soft delete)
- 로그인 기능 구현
  - post로 이메일, 비밀번호 전송
  - access Token반환