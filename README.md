# spring-gift-product

## 기능 요구 사항
- 상품을 조회, 추가, 수정, 삭제할 수 있는 간단한 HTTP API를 구현한다.
- HTTP 요청과 응답은 JSON 형식으로 주고받는다.
- 현재는 별도의 데이터베이스가 없으므로 적절한 자바 컬렉션 프레임워크를 사용하여 메모리에 저장한다.

## 기능 목록
- Controller
  - get
  - add
  - update
  - delete
- Entity
  - product
- DTO
  - RequestDTO
  - ResponseDTO