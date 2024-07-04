# spring-gift-wishlist

## STEP1

### 기능 목록

1. 상품 이름 유효성 검사
   - 상품 이름은 최대 15자까지 입력 가능
   - 허용 특수 문자: ( ) [ ] + - & / _
   - "카카오"가 포함된 문구는 담당 MD와 협의가 필요

2. 예외 처리
   - 유효성 검사 실패 시 적절한 예외 메시지 반환
   - 전역 예외 처리 핸들러 구현

---

### 구현 계획

#### 1. 상품 이름 유효성 검사 ProductRequestDTO, ProductController/AdminProductController 유효성 검증 기능 추가
   - 길이 제한: 최대 15자
   - 허용 특수 문자: ( ) [ ] + - & / _
   - "카카오" 포함 제한: 담당 MD와 협의가 필요

#### 2. 예외 처리 GlobalExceptionHandler, ProductValidationException, error.html
   - 유효성 검사 실패 시 적절한 예외 메시지를 반환합니다.
   - 예외 발생 시 전역 예외 처리 핸들러를 구현하여 통일된 방식으로 예외를 관리합니다.

---
