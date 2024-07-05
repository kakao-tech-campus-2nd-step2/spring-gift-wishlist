# spring-gift-wishlist

# 2주차 위시 리스트 - 요청과 응답 심화

# step 0

1주차 코드 가져오기

1주차 PR에 대해서 잘못된 저장소에서 fork하는 바람에 리뷰를 받지 못하였습니다.

# step 1

목표

- 상품 추가, 수정 시 잘못된 값에 대한 처리 + 응답 설정
- 상품 이름 제한 : 공백 포함 최대 15글자
- 일부 특수 문자만 허용
- "카카오" 포함 문구 입력 시 따로 confirm 이후 진행 가능하도록

## milestone

-[X] 스프링 validation 의존성 추가
-[X] feat : DTO valid 추가
-[ ] refact : service - 상품 update 로직 변경 (하나로 통합)
-[X] feat : @ControllerAdvice 클래스 추가
-[X] feat : "카카오" 검사를 위한 예외클래스 추가

## milestone 2

회원 가입, 로그인, 추후 회원별 기능 이용을 위해

회원은 이메일과 비밀번호를 입력하여 가입한다.
토큰을 받으려면 이메일과 비밀번호를 보내야 하며, 가입한 이메일과 비밀번호가 일치하면 토큰이 발급된다.
토큰은 JWT 를 사용하도록 한다.
관리자는 회원을 조회 추가 수정 삭제 할 수 있다.

- [ ] feat : 회원 모델 만들기
- [ ] feat : 회원 Repository 만들기
- [ ] feat : 회원 가입 서비스 만들기
- [ ] feat : 회원 가입 컨트롤러 만들기
- [ ] feat : 로그인 서비스 만들기
- [ ] feat : 인증 서비스 만들기
- [ ] feat : 인증 컨트롤러 만들기

### 회원 모델 만들기

Member.class : 회원모델
MemberRole.class : 회원 등급 enum class
