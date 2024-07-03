# spring-gift-wishlist

## 기능 정리
### Name 입력 예외처리
- [x]  상품 이름 길이 검사 **공백을 포함**하여 **최대 15자**
  - 예외 발생시 "최대 15자리까지 입력하실 수 있습니다." 전달
- [x]  사용가능 문자 필터링
  - 한글, 영문자, 숫자, ( ), [ ], +, -, &, /, _
  - 정규식 작성
  - 예외 발생시 "특수 문자는 '(), [], +, -, &, /, _ '만 사용가능 합니다." 전달
- [x]  "카카오"가 포함된 문구 예외 처리
  - 예외 발생시 "담당 MD와 협의해주세요" 전달
    
### 예외 처리 핸들러
- [x]  사용자 정의 Exception 생성(NameException)
- [x]  `ExceptionService` 클래스를 생성해서 예외 처리 로직 작성
- [x]  Controller에서 예외처리 구현
- [x]  ExceptionHandler 구현
    - RestController: 에러 메서지(string)과 400 BAD_REQUEST 반환
    - Controller: ResponseStatus로 400 BAD_REQUEST 반환하고 error.html 리다이렉션 
