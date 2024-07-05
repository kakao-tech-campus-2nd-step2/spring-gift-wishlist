# spring-gift-wishlist

# step1 이후로 추가한 파일

1. jwt 패키지  <br>
-JwtAuthenticationFilter - 토큰 검증<br>
-JwtTokenProvider - 토큰 생성<br>
-JwtUserDetailsService - DB에서 회원정보 조회하고 user 객체 생성<br>
-LoginController - 로그인을 요청 , 성공시 토큰 반환<br>
2.  user 패키지 <br>
-DataSourceconfiguration- DB 초기화 <br>
-SiteUser- 회원정보를 담는 엔티티 <br>
-UserController- 사용자 관련 HTTP 요청 처리<br>
-UserCreateForm-회원가입시 데이터를 입력받는 폼클래스 <br>
-UserRepository- 회원정보를 조회하는 인터페이스<br>
-UserRole- 사용자 역할 정의<br>
-UserSecurityService- 사용자 정보를 로드<br>
-UserService- 사용자 생성 , 저장<br>
3. SecurityConfig - security 설정 클래스 , 토큰 , DB  HTTP 요청에 대한 권한 수행

