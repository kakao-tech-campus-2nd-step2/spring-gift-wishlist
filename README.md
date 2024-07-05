# spring-gift-wishlist

# step1 이후로 추가한 파일

1. jwt 패키지  <br>
-JwtAuthenticationFilter<br>
-JwtTokenProvider<br>
-JwtUserDetailsService<br>
-LoginController<br>
2.  user 패키지 <br>
-DataSourceconfiguration <br>
-SiteUser <br>
-UserController <br>
-UserCreateForm <br>
-UserRepository <br>
-UserRole<br>
-UserSecurity<br>
-UserService<br>
3. SecurityConfig


# 코드 작성 후기
https://github.com/pahkey/sbb3/tree/v3.07 <br>
여기 이 코드를 활용하였습니다.
spring boot는 3.0 이상  , securiity는 6.0 이상이라 지원되지 않는
기능이나 메서드가 너무 많아 대체재를 찾느라 힘들었습니다.

-  위의 코드에서 추가한 기능 <br>
1. 토큰 기능추가
2. 기존의 코드와 합체
3. login.sql 파일을 생성하여 회원정보 DB 관리


