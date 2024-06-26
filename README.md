# spring-gift-product

## 구현 기능 목록

1. `/api/products` 로 HTTP 요청을 받을 수 있는 컨트롤러 구현
    1. 조회 : key값을 입력받으면, 그에 해당하는 product 객체를 json 타입으로 반환
    2. 추가 : product 객체의 이름, 가격, 이미지 url을 받아서 저장 및 해당 객체를 다시 반환
    3. 수정 : key값과 product 객체의 이름, 가격, 이미지 url을 받아서 기존의 product 객체를 갱신. 만일 해당 key값이 존재하지 않는다면 에러 발생
    4. 삭제 : key값을 입력받으면 해당 객체를 삭제 및 삭제된 객체를 반환
2. Product 클래스 구현
    1. 매개변수는 String name, Integer price, String imageUrl
3. ProductRepository 클래스 구현
    1. Singleton Pattern을 가진다.
    2. Map으로 키에 Long, 값에 Product를 가진다.
    3. Product에 대해 key 값을 기반으로 조회, 추가, 수정, 삭제를 할 수 있다.
4. 서버 부팅시 초기에 자동으로 기본 Product값 생성
5. 테스트 코드 작성
    1. `/api/products` 의 각 기능별 테스트 코드 작성
    2. ProductRepository의 각 기능별 테스트 코드 작성