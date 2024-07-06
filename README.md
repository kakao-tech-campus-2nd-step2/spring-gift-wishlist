# spring-gift-wishlist

## Step 1
1. 상품을 추가하거나 수정할 때 잘못된 값이 들어오면 어떤 부분이 왜 잘못되었는지 인지할 수 있도록 응답을 제공한다.
- 상품 이름은 공백을 포함하여 최대 15자까지 입력할 수 있다.
- 특수문자는 "(', ")", "[", "]", "+", "-", "&", "/", "_" 만 가능하며 그 외 특수 문자는 사용이 불가능하다.
- "카카오"가 포함된 문구는 담당 MD와 협의한 경우에만 사용할 수 있다.

### Step 2: 사용자 인증 및 관리 기능
1. 회원 가입
    - 이메일과 비밀번호를 입력하여 회원 가입
2. 로그인
    - 이메일과 비밀번호를 입력하여 로그인
    - 로그인 성공 시 토큰 발급

3. 토큰 생성
    - 선택한 방법을 사용하여 토큰 생성

4. 관리자
    - 회원 조회, 추가, 수정, 삭제 기능 제공

# Step 3: 위시 리스트
이전 단계에서 로그인 후 받은 토큰을 사용해 사용자별 위시 리스트 기능을 구현한다.
1. 위리 리스트에 등록된 상품 목록을 조회할 수 있다.
2. 위시 리스트에 상품을 추가할 수 있다.
3. 위시 리스트에 담긴 상품을 삭제할 수 있다.