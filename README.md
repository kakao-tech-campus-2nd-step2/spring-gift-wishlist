# spring-gift-wishlist

## 🚀 1단계 - 유효성 검사 및 예외 처리

### 개요
- 상품 정보를 추가하거나 수정할 때 유효성 검사 및 예외 처리를 통해 잘못된 입력을 방지하고, 명확한 오류 메시지를 제공

### 기능 목록
- [X] 상품 이름 유효성 검사
    - 공백 포함 최대 15자까지 허용
    - 허용된 특수 문자: ( ), [ ], +, -, &, /, _
    - "카카오" 포함 문구는 담당 MD와 협의된 경우에만 허용
- [ ] 예외 처리 구현

### 기술 스택
- Java 21
- Spring Boot 3.3.1
- Gradle 8.4