# spring-gift-wishlist


### step1
제품 입력에 대한 유효성 검사와 잘못된 입력에 대한 상세 오류 메세지를 클라이언트에게 제공한다.

1. Product: 유효성 검사를 위한 annotation 사용 (null x, 최대 15자, 특수문자 제한, 카카오 x)
2. GlobalExceptionHandler : 예외처리 발생시 핸들링
3. ProductService: ProductController에서 비즈니스 로직과 서비스 호출 역할을 분리

### step2
다음 step3단계에서 MVC패턴에 따라 코드 총정리까지 할 예정입니다. 

1. 사용자 로그인 및 회원가입 기능 구현
2. 예외 처리 
- 인증 실패 시 예외 처리 구현
3. HTTP 메세지 처리
- HTTP 요청, 응답 구현 

### step3

JwtToken이용해서 로그인 성공한 사용자만 user-products와 user-wishes 사용 가능하도록 구현했다. 