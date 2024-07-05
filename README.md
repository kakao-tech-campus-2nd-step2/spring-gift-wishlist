# spring-gift-wishlist

## Step1 - ToDo List
- 1단계 유효성 검사 및 예외 처리
- 기능 요구 사항
  - 상품 이름은 공백을 포함하여 최대 15자까지 입력할 수 있다.
  - 특수 문자 가능: ( ), [ ], +, -, &, /, _
  - 그 외 특수 문자 사용 불가
  - "카카오"가 포함된 문구는 담당 MD와 협의한 경우에만 사용할 수 있다.

- 구현 기능 목록
  - Product Request Dto validation
  - Controller Dto validation
  - Controller Advice 등록

## Step2 - ToDo List
- 2단계 회원 로그인
  - 기능 요구 사항
    - 사용자가 회원 가입, 로그인, 추후 회원별 기능을 이용할 수 있도록 구현한다.
    - 회원은 이메일과 비밀번호를 입력하여 가입한다.
    - 토큰을 받으려면 이메일과 비밀번호를 보내야 하며, 가입한 이메일과 비밀번호가 일치하면 토큰이 발급된다.
    - 토큰을 생성하는 방법에는 여러 가지가 있다. 방법 중 하나를 선택한다.


- 구현 기능 목록
  - Filter
  - domain
    - Member
    - AuthToken : 토큰 저장용 entity
  - Controller
    - AuthController
  - Service
    - AuthService
    - TokenService
  - Repository
    - MemberRepository
    - TokenRepository


## Step3 - ToDo List
- 3단계 위시 리스트
  - 기능 요구 사항
    - 위시 리스트에 등록된 상품 목록을 조회할 수 있다.
    - 위시 리스트에 상품을 추가할 수 있다.
    - 위시 리스트에 담긴 상품을 삭제할 수 있다.


- 구현 기능 목록
  - domain
    - wish
  - Controller
    - WishController
  - Service
    - WishService
  - Repository
    - WishRepository