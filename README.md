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

## step 2
### 기능 요구 사항
- 사용자가 로그인하고 사용자별 기능을 사용할 수 있도록 구현한다.
- 예시와 같이 HTTP 메시지를 주고받도록 구현한다.

### 구현할 기능 목록
주요 기능
- [X] 회원 가입
    - controller
    - service

- [X] 회원 존재 확인
    - service

- [X] 로그인(유효한 회원에게 토큰 수여)
    - contorller

- [X] 토큰 관리 (JwtUtil)
    - [X] 토큰 생성
    - [X] 토큰 유효성 확인
    - [X] 토큰에서 사용자 정보 추출

- [X] 권한 인가
    - JwtInterceptor

- [X] interceptor 설정
    - web config 작성

예외 처리
- [X] UserNotFound Exception 작성
- [X] JwtException Exception를 GlobalExceptionHandler로 처리