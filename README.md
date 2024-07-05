# spring-gift-wishlist

## 2단계 - 인증

### JWT 필터 및 설정 추가

- **SecurityConfig 클래스에 JWT 필터 추가**
  - JWT 토큰을 검증하고 인증을 처리하기 위한 필터를 추가합니다.
  - CORS 설정을 통합하여 기본 허용 정책을 설정합니다.

  커밋 메시지: feat(security): JWT filter & configuration 구현

### JWT 토큰 발급 기능 구현

- **JwtTokenUtil 클래스에 AccessToken 및 RefreshToken 발급 메서드 구현**
  - 사용자의 이메일을 기반으로 AccessToken과 RefreshToken을 생성합니다.
  - 토큰의 만료 기간을 설정하고 서명 알고리즘을 지정하여 안전하게 토큰을 생성합니다.

  커밋 메시지: feat(jwt): JWT token generation 구현

### JWT 토큰 검증 기능 구현

- **JwtTokenUtil 클래스에 토큰 유효성 검사 및 클레임 추출 메서드 추가**
  - 주어진 토큰의 유효성을 검사하고, 유효한 경우 클레임을 추출합니다.
  - 인증 처리를 위해 추출된 클레임을 사용합니다.

  커밋 메시지: feat(jwt): JWT token validation 구현

### 사용자 인증 처리 구현

- **UserService 클래스에 사용자 로그인 기능 구현**
  - 사용자가 제공한 이메일과 비밀번호를 검증하여 인증을 처리합니다.
  - 인증 성공 시 JWT 토큰을 발급하여 반환합니다.

  커밋 메시지: feat(user): user authentication 구현








## STEP1

### 기능 목록

1. 상품 이름 유효성 검사
   - 상품 이름은 최대 15자까지 입력 가능
   - 허용 특수 문자: ( ) [ ] + - & / _
   - "카카오"가 포함된 문구는 담당 MD와 협의가 필요

2. 예외 처리
   - 유효성 검사 실패 시 적절한 예외 메시지 반환
   - 전역 예외 처리 핸들러 구현

---

### 구현 계획

#### 1. 상품 이름 유효성 검사 ProductRequestDTO, ProductController/AdminProductController 유효성 검증 기능 추가
   - 길이 제한: 최대 15자
   - 허용 특수 문자: ( ) [ ] + - & / _
   - "카카오" 포함 제한: 담당 MD와 협의가 필요

#### 2. 예외 처리 GlobalExceptionHandler, ProductValidationException, error.html
   - 유효성 검사 실패 시 적절한 예외 메시지를 반환합니다.
   - 예외 발생 시 전역 예외 처리 핸들러를 구현하여 통일된 방식으로 예외를 관리합니다.

---
