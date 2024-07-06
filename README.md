# spring-gift-wishlist

## 📪 Features

### 👤 회원 기능
- 회원가입
  - 이메일과 비밀번호를 입력하여 회원가입
  - 이메일은 중복될 수 없음
- 로그인
  - 이메일과 비밀번호를 입력하여 로그인
>#### ⚠️ 비밀번호 조건
> - 영어 대문자, 소문자, 숫자 필수 포함
> - 최소 8글자 이상
> - 공백 포함 불가능

### 🎁 상품 관리
- 상품 조회
- 상품 추가
- 상품 수정
- 상품 삭제

>##### ⚠️ 상품 이름 조건
>- 최대 15자
>- 숫자 및 공백 포함 가능
>- 가능한 특수 문자
>  - (
>  - )
>  - [
>  - ]
>  - \+
>  - \-
>  - &
>  - /
>  - _
>- `카카오`가 포함된 문구는 담당 MD와 협의한 경우에만 사용 가능

### 📜 위시리스트 관리
>⚠️ 유효한 토큰을 포함한 요청에 대해서만 아래 기능 수행
- 위시리스트 조회
- 위시리프트 상품 추가
  - 하나의 요청당 하나의 상품 추가
- 위시리스트 상품 수량 변경
  - 요청된 변경 수량이 0일 경우 삭제
- 위시리스트 상품 삭제
  - 하나의 요청당 하나의 상품 삭제