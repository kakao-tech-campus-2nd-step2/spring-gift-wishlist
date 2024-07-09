# spring-gift-wishlist

## 2주차 step1

### 기능
- 유효성 검사 및 예외 처리
- 상품 이름 길이 제한 및 특수 문자 제한
- "카카오" 포함 문구 예외 처리

### 할일
1. 의존성 추가
   - spring-boot-starter-validation
2. Product 클래스 수정
   - 유효성 검사 어노테이션 추가
3. ProductController 클래스 수정
   - 유효성 검사 및 예외 처리 추가
4. 글로벌 예외 처리기 작성
   - ControllerAdvice 사용


## 2주차 step2

### 기능
- 회원 가입 및 로그인
- JWT 토큰 생성 및 반환

### 할일
1. 의존성 추가
2. User 엔티티 작성
   - `User` 클래스: `id`, `email`, `password` 필드
3. UserRepository 작성
   - `findByEmail` 메서드
4. UserService 작성
   - `createUser`: 회원 가입 로직
   - `loadOneUser`: 이메일로 사용자 조회
   - `makeToken`: JWT 토큰 생성
5. UserController 작성
   - `@PostMapping("/register")`: 회원 가입 엔드포인트
   - `@PostMapping("/login")`: 로그인 엔드포인트
6. TokenResponse DTO 작성
   - `token` 필드 및 생성자

## 2주차 step3

### 기능
- 위시 리스트에 등록된 상품 목록 조회
- 위시 리스트에 상품 추가
- 위시 리스트에서 상품 삭제

### 할일
1. WishlistRepository 작성: save, findByUserId, delete 메서드 
2. WishlistService 작성: 위시 리스트 추가, 조회, 삭제 로직 
3. WishlistController 작성: 위시 리스트 추가, 조회, 삭제 엔드포인트 
4. JwtTokenProvider 작성: JWT 토큰 생성, 추출, 유효성 검사