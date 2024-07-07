# spring-gift-wishlist

- [x] 상품명 공백 포함 길이 15 이상 시 예외 처리
    - [x] 서비스 레이어에서 상품명 길이 확인  
    - [x] @ControllerAdivce 사용해서 예외 발생 시 처리
        - [x] 예외 클래스 생성 ProductNameLengthLimitException  
- [x] 상품 이름에 (),[],+,-,&,/,_ 를 제외한 특수 문자 사용 시 예외 처리
    - [x] 서비스 레이어에서 상품명 길이 확인
    - [x] @ControllerAdivce 사용해서 예외 발생 시 처리 
        - [x] 예외 클래스 생성 ProductNameSpecialCharException 
- [x] 상품명에 '카카오'가 포함될 경우 예외 처리
    - [x] 담당 MD와 협의한 경우는 사용 가능함을 알린다.