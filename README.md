# spring-gift-wishlist
---
## 1단계(유효성 검사 및 예외 처리) 요구사항
### build.gradle
- spring-boot-starter-validation 의존성 추가
### Product
- @ValidProductName 어노테이션 추가
### ValidProductName
| 커스텀 유효성 검사 어노테이션
### NotValidProductNameException
- Product product
- super(message)
### ProductNameValidator 
| implements ConstraintValidator<ValidProductName, Product>
- @Override void initialize
- @Override boolean isValid
  - 특수문자 가능: (), [], +, -, &, /, _
  - 그 외 특수문자 불가능
  - '카카오' 포함 문구는 담당 MD와 협의한 경우에만 사용 가능
### ProductController
- `@Validated` 어노테이션 추가
- `save()`, `update()` 파라미터에 `@Valid` 어노테이션 추가
### GlobalExceptionHandler
- `@ControllerAdvice` 어노테이션 추가
- handleNotValidProductNameException(NotValidProductNameException e, Model model)
  - "errorMessage": e.getMessage()
  - "product": e.getProduct()

## 2단계 요구사항
### schema.sql
- create table user
### User
- id PK
- email
- password
- super key (email, password)
### UserRepository
- getIdByEmailPassword()
### UserController
### AuthViewController
- showSingupForm()
- createUser()
- showLoginForm()
- login()
### singUp.html
### login.html

## 3단계 요구사항
### JWT 기반 인증 구현
### schema.sql
- wishlist table 생성
- user_id
- product_id
- product_name
- amount
### withlist.html
### AuthController
### WishlistItem
### WishlistRepository
- findListByUserId()
- save()
### WishlistController
- makeWishlist()
- createWishlist()
### add_wishlist.html
