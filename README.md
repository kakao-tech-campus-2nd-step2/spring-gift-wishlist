# spring-gift-wishlist

## 3단계 - 위시리스트

### 기능 요구 사항

- 이전 단계에서 로그인 후 받은 토큰을 사용하여 사용자별 위시 리스트 기능을 구현한다.

1. 위시 리스트에 등록된 상품 목록을 조회할 수 있다.
2. 위시 리스트에 상품을 추가할 수 있다.
3. 위시 리스트에 담긴 상품을 삭제할 수 있다.

### 실행 결과

- 사용자 정보는 요청 헤더의 Authorization 필드를 사용한다.
- `Authorization: <유형> <자격증명>`
- 인증 방법은 bearer 인증을 사용한다.

```
Authorization: Bearer token
```

## 구현 기능 목록

1. wishlist 테이블을 만든다.
    - product id, email 두개의 값을 갖는다.
    - 각각은 forienkey(product), forienkey(member)이며, 둘을 합쳐 하나의 primary key로 이용한다.
2. wishlist에 접근하는 API에 한해서 모두 전처리를 통해 member객체를 주입한다.
3. wishlist에 대해 CRD 기능을 구현한다.
    - Create와 Delete는 product의 id값과 토큰을 통해 상품을 추가한다.
    - Read는 토큰을 통해 모든 product의 id값을 가져온다.
