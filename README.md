# [구현할 기능 목록] 3단계 - 위시 리스트
#### 이전 단계에서 로그인 후 받은 토큰을 사용하여 사용자별 위시 리스트 기능을 구현한다.
#### 단, 사용자 정보는 요청 헤더의 Authorization 필드를 사용한다.
#### ex) Authorization: Bearer token
### 1. HandlerMethodArgumentResolver 구현
#### (1) `LoginMember` 어노테이션 생성 
- [ ] 컨트롤러 메서드의 파라미터에 붙여서 로그인한 사용자의 정보를 주입하기 위해 사용됨
#### (2) `JwtUtil` 구현
- [ ] 토큰 유효성 검증
- [ ] 토큰에서 이메일 정보 추출
- [ ] JWT 토큰을 파싱하여 그 안에 포함된 클레임을 반환
#### (3) `LoginMemberArgumentResolver` 구현
- [ ] `supportsParameter` : `LoginMember` 어노테이션을 가진 파라미터를 지원하는지 판단
- [ ] `resolveArgument` : 토큰값 인증 후 사용자 정보 반환
### 2. 위시 리스트 기능 구현
#### (1) `WishList` 모델 설계
#### (2) `WishListController` 구현
#### (3) `WishListService` 구현
#### (4) `WishListRepository` 구현



