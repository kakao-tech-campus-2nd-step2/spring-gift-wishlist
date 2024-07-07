# spring-gift-wishlist

### 🚀 1단계 - 유효성 검사 및 예외 처리
상품을 추가하거나 수정하는 경우, 클라이언트로부터 잘못된 값이 전달되면 어떤 부분이 왜 잘못되었는지 인지할 수 있도록 응답 제공
- 상품 이름은 공백 포함 최대 15자
- 특수 문자는 '(), [], +, -, &, /, _' 만 사용 가능
- "카카오"가 포함된 문구는 담당 MD와 협의한 경우에만 사용 가능

<br><hr>

### 🚀 2단계 - 회원 로그인
- 회원은 이메일과 비밀번호를 입력하여 가입
- 로그인 시 가입한 이메일과 비밀번호가 일치하면 토큰이 발급됨
- JSON Web Token 사용

<br><hr>

### 🚀 3단계 - 위시 리스트
2단계에서 로그인 후 받은 토큰을 사용하여 사용자별 위시 리스트 기능 구현
- 위시 리스트에 등록된 상품 목록 조회
- 위시 리스트에 상품 추가
- 위시 리스트에 담긴 상품 삭제
