# spring-gift-wishlist

## 2단계 - 회원 로그인 기능 요구사항
사용자가 회원 가입, 로그인, 추후 회원별 기능을 이용할 수 있도록 구현한다.

- 회원은 이메일과 비밀번호를 입력하여 가입한다.
- 토큰을 받으려면 이메일과 비밀번호를 보내야 하며, 가입한 이메일과 비밀번호가 일치하면 토큰이 발급된다.
- 토큰을 생성하는 방법에는 여러 가지가 있다. 방법 중 하나를 선택한다.
- (시간되면) joinForm.html 을 만들어 컨트롤러와 타임리프를 사용하여 연결한다.


## 1단계 - 유효성 검사 및 예외 처리 기능 요구사항
상품을 추가하거나 수정하는 경우, 클라이언트로부터 잘못된 값이 전달될 수 있다. 
잘못된 값이 전달되면 클라이언트가 어떤 부분이 왜 잘못되었는지 인지할 수 있도록 응답을 제공한다.

- 상품 이름은 공백을 포함하여 최대 15자까지 입력할 수 있다.
- 특수 문자 
      가능: ( ), [ ], +, -, &, /, _
      그 외 특수 문자 사용 불가
- "카카오"가 포함된 문구는 담당 MD와 협의한 경우에만 사용할 수 있다. -> 일단 메시지만 출력하도록 구현한다.
- 
## validation
spring-boot-starter-validation 의존성을 명시적으로 추가한다.
`implementation 'spring-boot-starter-validation'`


## 2단계에서 수정된 코드 구조
```plaintext
└── src
    └── main
        ├── java
        │   └── gift
        │       ├── Application.java
        │       ├── repository
        │       │   └── MemberRepository.java
        │       ├── service
        │       │   ├── MemberService.java
        │       │   └── ProductService.java
        │       ├── entity
        │       │   ├── Member.java
        │       │	└── Product.java
        │       ├── exception
        │       │   ├── KakaoProductException.java
        │       │   └── GlobalExceptionHandler.java
        │       ├── controller
        │       │   ├── MemberController.java
        │       │   ├── ProductController.java
        │       │   └── AdminController.java
        │       ├── domain
        │       │   └── ProductModel.java=
        │       ├── dao
        │       │   └── ProductDao.java
        │       └── dto
        │           ├── MemberDto.java
        │           └── ProductDto.java
        └── resources
            ├── member.html
            ├── data.sql
            ├── schema.sql
            ├── static
            └── templates
                ├── add.html
                ├── edit.html
                ├── list.html
                └── view.html
