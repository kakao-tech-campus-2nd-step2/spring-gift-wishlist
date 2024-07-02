# spring-gift-wishlist

## 2주차 step1

### 기능
- 유효성 검사 및 예외 처리
- 상품 이름 길이 제한 및 특수 문자 제한
- "카카오" 포함 문구 예외 처리

### 할일
1. 의존성 추가
    - spring-boot-starter-validation
2. Product 클래스 수정
    - 유효성 검사 어노테이션 추가
3. ProductController 클래스 수정
    - 유효성 검사 및 예외 처리 추가
4. 글로벌 예외 처리기 작성
    - ControllerAdvice 사용