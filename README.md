# spring-gift-wishlist

제품 입력에 대한 유효성 검사와 잘못된 입력에 대한 상세 오류 메세지를 클라이언트에게 제공한다.

1. Product: 유효성 검사를 위한 annotation 사용 (null x, 최대 15자, 특수문자 제한, 카카오 x)
2. GlobalExceptionHandler : 예외처리 발생시 핸들링
3. ProductService: ProductController에서 비즈니스 로직과 서비스 호출 역할을 분리