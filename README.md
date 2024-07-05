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
    - 정규식, 문자 길이를 위반할 경우 400(Not Found)

## step2 구현 기능

유저 객체 생성
  - 유저 리스트 조회
    - 모든 유저의 이메일,비밀번호를 조회할 수 있다.
  - 유저 조회
    - 해당 id를 가진 유저를 조회한다.
    - 이메일, id를 반환한다.
  - 유저 생성
    - 이메일
      - 이메일 형식 정규식 적용
    - 비밀번호
      - 4~30자 
    - 이메일은 생성하면 수정할 수 없다.
    - 성공하면 id를 반환한다.
  - 유저 비밀번호 수정(patch)
    - 이메일은 변경불가여서 patch로 구현
    - 성공하면 id를 반환한다.
  - 유저 삭제(soft delete)
    - isDelete가 1이면 존재, 0이면 삭제 상태
    - 성공하면 id를 반환한다.
    

로그인 기능 구현
- 로그인
  - post로 이메일, 비밀번호 전송
  - 로그인시 JWT Token반환
    - 토큰에는 email만 포함한다.
  - 로그인을 제외한 오청에는 JWT토큰을 요구한다.
    - Header에 "Authorization":"Bearer JWT토큰" 추가
    - 잘못된 토큰을 입력하거나 토큰이 존재하지 않는다면 401(Unauthorized)

## step3 구현 기능

위시리스트 기능 구현
- 위시 리스트 조회
  - user id로 조회
  - id, user id, product id 반환
  - user id 검증
- 위시 상세 조회
  - wish id로 조회
  - id, user id, product id, product name, product price, product imageUrl 반환
  - wish id 검증
- 위시 리스트 추가
  - user id, product id로 추가
  - wish id 반환
  - user id, product id, 위시 리스트내 중복 검증
- 위시 리스트 삭제
  - wish id로 삭제
  - wish id 반환
  - wish id 검증