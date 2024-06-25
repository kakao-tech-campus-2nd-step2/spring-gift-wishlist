# spring-gift-product

## 구현 기능 목록

1. `/api/products` 로 HTTP 요청을 받을 수 있는 컨트롤러 구현
    1. 조회 : key값을 입력받으면, 그에 해당하는 
    2. 추가
    3. 수정
    4. 삭제
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