# spring-gift-wishlist
## step1
* DTO Validation Annotation 추가
  * 필수 값과 값의 형식과 같은 양식 검사
  * 값에 대한 유효성 검사이므로 느슨한 결합을 위해 Controller에서 진행하지 않습니다.
* Entity Validation Annotation 추가
  * Id 중복 여부 등 논리적 검사
  * 마찬가지로 도메인 규칙에 대한 검사이므로 느슨한 결합을 위해 Service에서 진행하지 않습니다.
## step2
* 로그인 핸들러 생성
  * 토큰을 반환하기 (RestController)
* 회원 가입 핸들러 생성
  * 회원 테이블을 다룰 DAO 필요
* JWT Utility 클래스 생성
