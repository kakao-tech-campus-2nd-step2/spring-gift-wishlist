# [구현할 기능 목록] 2단계 - 인증
### 사용자가 로그인하고 사용자별 기능을 사용할 수 있도록 구현
### 1. 라이브러리 및 환경 설정 
- [x] 라이브러리 설정 (spring security, jwt)

### 2. Model 설계 
- [x] Model 설계 
  - [x] 사용자의 로그인 요청 정보를 표현하는 클래스 (LoginRequest)
  - [x] JWT 토근 정보와 인증 방식을 담는 클래스 (JwtToken)
### 3. Service 설계 
- [x] JWT access 토큰 생성 및 검증하는 서비스 구현 (JwtService)
### 4. Controller 설계
- [x] 로그인 요청 처리 및 JWT access 토큰 반환하는 컨트롤러 구현 (LoginController)