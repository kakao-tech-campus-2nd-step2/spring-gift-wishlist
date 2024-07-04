# spring-gift-wishlist

## 1단계
### 클라이언트 입력 값에 대한 검증 및 예외 처리 구현
#### Model(Product)
- 스프링에서 제공하는 Validation 활용
- 공통 조건
  - null 입력 불가
  - 일치하지 않는 타입 입력 불가
- 상품명
    1. 공백을 포함하여 최대 15자까지 입력할 수 있다.
    2. 특수 문자
       - 가능: ( ), [ ], +, -, &, /, _
       - 그 외 특수 문자 사용 불가
    3. "카카오"가 포함된 문구가 입력되면, "담당 MD와 협의한 경우에만 사용할 수 있다"를 출력한다.
- 상품 가격(스스로 판단)
  1. 최소 100원~최대 1,000,000원까지 허용한다.
- 상품 url(스스로 판단)
  1. “http://” 혹은 “https://”로 시작하는 url 양식을 가져야 한다.

#### Controller
- REST API: 스프링 validation 활용하여 검증, ExceptionHandler를 통해 예외 처리
- 관리자 화면 렌더링: 스프링 validation 활용하여 검증, 오류 메시지 출력

#### Exception
- ProductException: 검증 오류 메시지를 포함한 커스텀 예외
- ProductExceptionHandler: 검증 오류 메시지를 포함하여 응답 메시지 반환

#### Validation
- 타입 에러: 스프링에서 기본으로 제공하는 typeMismatch를 메시지 커스텀
- bindingResult: 타입 에러 외 유효하지 않은 input들을 담아 Form html 전송

#### Html
- BindingResult.hasErrors() 시 에러를 출력하도록 수정
  - th:errors/errorclass를 활용하여 에러 출력

    
## 2단계
### 유저 인증을 통한 기능 사용 권한 검증
- 스프링 인터셉터로 유저 인증 기능 구현
    - 인증 방식: **세션**
        - 유저 인증 후 세션을 생성하여 세션 저장소에서 관리, 이후 클라이언트의 요청이 들어오면 세션 저장소에서 값을 꺼내 대조 작업을 거치고 난 다음에 응답 데이터 전송
        - 세션을 선택한 이유?: 단일 서버 구조여서 하나의 서버에서 세션 관리를 하면 됨, 토큰 방식의 경우 토큰의 만료 기간을 설정하는 것이나 redis와 같은 휘발성 DB 도입이 복잡하여 비교적 단순한 세션을 선택함
    - 로그인된 유저인 경우에 한해서만 상품 등록/수정/삭제가 가능하도록 허용
        - 비로그인된 유저의 경우는 상품 조회만 가능

#### Authorization
- Interceptor: http request를 가로채 유저 인증 작업을 수행
- Config: 인터셉터를 등록

#### Controller
- ProductApiController에 한해 적용
- 로그인 요청 API: /login
    - 유저 인증이 정상적으로 수행되면, session을 넣어 정상 응답
    - 유저 인증이 실패할 시 예외 처리
- 상품 생성/수정/삭제 API: 세션 조회 기능 추가
    - 로그인된 유저만 기능 사용을 허가, 비로그인된 유저일 시 예외 처리
  
#### Model
- Member: 유저 정보를 저장할 모델
- email: 유저 이메일(아이디)
- password: 유저 패스워드

#### DB
- Member 테이블 생성