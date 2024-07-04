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

