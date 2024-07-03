# spring-gift-wishlist

# step0 이후로 새로 추가한 파일

- exception 디렉토리
  - ErrorCode
  - GlobalExceptionHandler
  - InvalidProductNameException
-templates
  - error.html


- 간단한 코드 작동 설명
ErrorCode 에서 에러변수롤 설정하고 출력할 메시지를 설정합니다.
그 이후 Controller에서 validate 메서드를 만들어 예외를 체크하고
Global에서 전역에서 예외 처리를 하며 , error.html을 표시했습니다.

- 어려웠던 점
원래 계획인 예외 페이지를 만들지 말고 현재 페이지에서 alert로 에러 메시지를 알려주고 
바로 사용자에게 다시 입력받도록 하고싶었는데 , java-script를 이용해야 할것같아 번거롭기도 하고 해서
포기했습니다. 막상 만들고 보니 error 페이지를 만드는게 더 쉽고 사용자로 하여금 더 눈에 보기 쉬운것 같습니다.

