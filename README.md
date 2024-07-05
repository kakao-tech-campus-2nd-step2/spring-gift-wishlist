# spring-gift-wishlist

### step0

- 1주차 내용 clone

### step1

- Validation 종속성 추가
- DTO에 검증 값 어노테이션 추가
- Controller 수정 및 GlobalExceptionHandler에서 오류 message 처리
- 서버 사이드 렌더링(관리자 화면)에서 입력 오류 처리기능 추가

### step2

- id, email, password 필드를 가진 엔티티 클래스 생성
- UserRepository DAO생성하여 DB 연동
- 인증 Request, Response DTO 생성 및 구현
- 랜덤 토큰을 생성하는 TokenService 클래스 생성 및 구현
- 보일러 플레이트를 피하기 위해 Interceptor 생성 및 구현
- Interceptor 의존성을 주입한 WebConfig 생성 및 구현
- schema에 users 테이블 추가
- AuthController 구현

### step3

- wish entity 생성 및 스키마 작성
- wish의 request를 다룰 DTO 생성 및 구현
- 데이터를 access할 wish DAO 생성 및 구현
- 비즈니스 로직을 처리할 wish Service 및 User Service 생성 및 구현
- 컨트롤러 메서드에 진입 전 로그인 유저 객체를 주입하기 위한 기능 구현
- wish 리스트를 다룰 컨트롤러 생성 구현
