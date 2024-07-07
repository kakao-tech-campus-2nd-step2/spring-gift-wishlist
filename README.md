# spring-gift-wishlist

---

## 0단계 - 기본 코드 준비

- 구현할 기능
    - 상품 관리 코드 옮기기

---

## 1단계 - 유효성 검사 및 예외 처리

- spring-boot-starter-validation 의존성을 명시적으로 추가
- Product에 Validation Annotation 사용하기
    - 상품 이름 길이 검사하기 위해
        - 상품 이름은 최소 1자 이상이어야 하므로 @NotBlank 사용
            - message는 "상품 이름은 최소 1자 이상이어야 합니다."
        - 상품 이름은 공백 포함 최대 15자까지 입력 가능하므로 @Size(max=15)사용
            - message는 "상품 이름은 공백 포함 최대 15자까지 입력할 수 있습니다."
        - 클라이언트가 잘못된 값을 보냈으므로 Http Status Code는 400 Bad Request
    - 상품 이름 특수 문자 검사하기 위헤
        - @Pattern 사용
            - regexp는 사용가능한 문자와 특수 문자
            - message는 상품 이름에 (), [], +, -, &, /, _ 외 특수 문자는 사용할 수 없습니다."
        - 클라이언트가 잘못된 값을 보냈으므로 Http Status Code는 400 Bad Request
    - 가격 검사하기 위해
        - 가격은 0원부터 시작이고 음수가 될 수 없으므로 @PositiveOrZero 사용
        - message = "가격은 음수가 될 수 없습니다."
        - 클라이언트가 잘못된 값을 보냈으므로 Http Status Code는 400 Bad Request
    - 이미지 주소 검사하기 위해
        - 없으면 안되므로 @NotBlank 사용
            - message는 "이미지 URL을 입력해야 합니다."
        - 주소의 특징은 https://~~~ 또는 http://~~~이므로 @Pattern 사용
            - regexp는 ^(http|https)://.*$
            - message는 유효한 이미지 URL을 입력해야 합니다.
- AdminController 수정
    - Product에 @Valid 사용
        - 자동으로 요청 객체의 필드에 대한 유효성 검사하기
    - 카카오 문구 검사하기 위해
        - Validation Annotation 으로 단어 검증 하는 방법을 몰라 illegalArgumentException 사용하기
            - 입력 받은 문자열에서 "카카오"가 있으면 예외 발생
            - 예외 메시지는 "상품 이름에 '카카오'를 포함하려면 담당 MD와 협의가 필요합니다."
    - BindResult 사용
    - 상품 가격에 숫자 이외의 값 입력 시 예외 처리
    - 카카오 문구 검사 예외 처리
- 전체적인 예외를 다루는 handler 클래스 생성
    - @ExceptionHandler 사용
        - MethodArgumentNotValidException
            - Validation annotation의 예외가 발생하면 MethodArgumentNotValidException이 발생
        - IllegalArgumentException
    - @ResponseStatus 사용
        - Http Status Code의 400 Bad Request 상태 코드를 반환하기 위해서 사용
- HTML 수정
    - edit_product_form
        - 예외 처리되면 나오는 메시지 출력
    - add_product_form
        - 예외 처리되면 나오는 메시지 출력

## 2단계 - 회원 로그인

- JJWT 라이브러리 추가
    -  compileOnly 'io.jsonwebtoken:jjwt-api:0.12.6'
    - runtimeOnly 'io.jsonwebtoken:jjwt-impl:0.12.6'
    - runtimeOnly 'io.jsonwebtoken:jjwt-jackson:0.12.6'
- User 객체 생성
    - 필드는 id, name, email, password, role
- User DB 생성
    - schema.sql 수정
    - data.sql 수정
- UserDTO 생성
    - 회원 가입 시 필요한 필드만 넣습니다.
    - name, email, password
- LoginDTO 생성
    - 로그인 시 필요한 필드만 넣습니다.
    - email, password
- UserRepository 생성
- UserController 생성
    - 회원 가입
    - 로그인
- JwtUtil 생성
    - JWT 토큰 생성 및 검증