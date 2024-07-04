# spring-gift-wishlist


### step1
제품 입력에 대한 유효성 검사와 잘못된 입력에 대한 상세 오류 메세지를 클라이언트에게 제공한다.

1. Product: 유효성 검사를 위한 annotation 사용 (null x, 최대 15자, 특수문자 제한, 카카오 x)
2. GlobalExceptionHandler : 예외처리 발생시 핸들링
3. ProductService: ProductController에서 비즈니스 로직과 서비스 호출 역할을 분리

### step2
다음 step3단계에서 MVC패턴에 따라 코드 정리하고 회원가입을 통해 정보를 DB에 저장하고 로그인 후 상품페이지를 사용할 수 있도록 구현할 예정입니다.

현재 step2에서는 미션 예제에 나와있는 
{
"password": "password",
"email": "admin@email.com"
}
해당 예제를 POST 했을 때 Response 정상으로 응답되도록 구현하였습니다.

1. 사용자 로그인 기능 구현 
- 로그인 요청 처리 엔드포인트 생성 (`/login/token`)
- 사용자 인증 서비스 구현
- 인증 토큰 생성 및 반환
2. 예외 처리 
- 인증 실패 시 예외 처리 구현
3. HTTP 메세지 처리
- HTTP 요청, 응답 구현 