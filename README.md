# spring-gift-wishlist
구현할 기능 목록
1. Product DTO에 조건 추가
2. 컨트롤러 수정
   - 상품 추가 시 검증
   - 상품 수정 시 검증
3. 뷰 수정
   - 상품 추가 뷰 : 필드값 에러 시 오류 메시지 출력
   - 삼품 수정 뷰 : 필드값 에러 시 오류 메시지 출력

step2
1. User 객체 생성
2. UserDTO 객체 생성
3. UserController 
   - 로그인 뷰
   - 회원가입 뷰
   - 로그인 API
   - 회원가입 API
4. UserService 
   - 아이디와 비밀번호가 일치하는지 확인
5. JWTService
   - JWT 토큰 발급
   - JWT 토큰 유효성 검사

step3
1. 위시리스트 Repository
   - wishList 객체
   - wishList DTO
2. 위시리스트 Controller
   - 상품 추가
   - 상품 삭제
   - 조회
   - 상품 수정(수량 변경)
3. @LoginUser
   - HandlerMethodArgumentResolver
   - annotation
4. JWT Interceptor
   - 로그인 관련 전처리
