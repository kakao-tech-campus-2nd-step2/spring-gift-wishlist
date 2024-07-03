# [구현할 기능 목록] 2단계 - 인증
### 사용자가 로그인하고 사용자별 기능을 사용할 수 있도록 구현
- [x] 라이브러리 설정 (spring security, jwt)
- [x] Model 설계 
  - [x] 사용자의 로그인 요청 정보를 표현하는 클래스 (LoginRequest)
  - [x] JWT 토근 정보와 인증 방식을 담는 클래스 (JwtToken)
- [x] JWT 토큰 생성 및 검증하는 서비스 구현 (JwtService)
- [ ] Spring Security 설정 및 JWT 인증 통합 (SecurityConfigurer)
- [ ] 로그인 요청 처리 및 JWT 토큰 반환하는 컨트롤러 구현
- [ ] 사용자 정보를 로드하는 서비스 구현
- [ ] JWT 요청 시 검증해주는 필터를 구현

