# spring-gift-wishlist

## 1단계 - 유효성 검사 및 예외 처리
> <<상품 추가, 수정 시>>   
> 클라이언트로부터 잘못된 값이 전달되면 클라이언트가 어떤 부분이 왜 잘못되었는지 인지할 수 있도록 응답   
> 상품 이름은 공백 포함 15자 이내   
> (, ), [, ], +, -, &, /, _ 외의 특수 문자 불가   
> "카카오"가 포함된 문구는 담당 MD와 협의한 경우에만 사용 가능

### 기능 요구사항 목록
- [x] 입력 필드가 null인 경우 처리
  - 공백만으로 구성된 경우도 예외로 가정
- [ ] 상품 가격이 범위를 벗어나는 경우 처리
  - 최대 가격은 int 자료형의 최댓값으로 가정
- [ ] 상품 가격에 정수로 치환 불가능한 값이 입력된 경우 처리
- [ ] 형식에 맞지 않는 URL이 입력된 경우 처리
- [ ] 상품 이름이 15자를 초과하는 경우 처리
  - 문자열 앞뒤에 공백이 포함된 경우는 유효한 것으로 가정
- [ ] 상품 이름에 불가능한 특수 문자가 입력된 경우 처리
  - (대)괄호의 쌍이 맞지 않는 경우는 유효한 것으로 가정
- [ ] 상품 이름에 "카카오" 문구가 포함된 경우 처리