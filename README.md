# spring-gift-wishlist

<hr>

## Week 1
### STEP1. 상품 API
1. 조회
2. 추가
3. 수정
4. 삭제


### STEP2. 관리자 페이지
1. 상품 조회 페이지
   추가 버튼
   상품 목록에 수정, 삭제 버튼
2. 상품 추가 페이지
3. 상품 수정 페이지

### STEP3. 데이터베이스 적용
1. DB 연결
2. DB 연결사항 코드 반영

<hr>

## Week 2

### STEP1. 유효성 검사
1. 상품 이름은 공백을 포함하여 최대 15자까지 입력할 수 있다.
2. 상품 이름은 ( ), [ ], +, -, &, /, _ 외의 특수문자 사용 불가능
3. "카카오"가 포함된 문구는 담당 MD와 협의한 경우에만 사용할 수 있다.

### STEP2. 토큰 생성
1. Member, MemberDto 생성
2. Member Repository 생성
3. Member service 생성
4. Member controller 생성
5. 토큰 생성

### STEP3. Wishlist 생성
1. Wish, WishDto, RequestWishDto 생성
2. Wish Repository 생성
3. Wish Service 생성
4. Wish Controller 생성
5. @loginMember 생성
6. LoginMemberArgumentResolver 생성