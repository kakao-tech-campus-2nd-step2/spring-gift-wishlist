# spring-gift-wishlist

### 2단계 step1 기능 요구사항
#### 상품을 추가하거나 수정하는 경우, 클라이언트로부터 잘못된 값이 전달될 수 있다. 잘못된 값이 전달되면 클라이언트가 어떤 부분이 왜 잘못되었는지 인지할 수 있도록 응답을 제공한다.

- [x] 상품 이름은 공백을 포함하여 최대 15자까지 입력할 수 있다.
- [x] 특수 문자
  - 가능: ( ), [ ], +, -, &, /, _
  - 그 외 특수 문자 사용 불가
- [x] "카카오"가 포함된 문구는 담당 MD와 협의한 경우에만 사용할 수 있다.
- [x] controller에 @valid 어노테이션 추가
- [x] thymeleaf에 error message 보이는 곳 추가

### 2단계 step2 기능 요구사항
#### 사용자가 로그인하고 사용자별 기능을 사용할 수 있도록 구현한다.

- [x] User Table 생성
- [x] User Class 생성
- [x] UserRequestDto 생성
- [x] TokenResponseDto 생성
- [x] UserDao 생성
- [x] UserController 생성
- [ ] AuthService 생성
