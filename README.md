# spring-gift-product
KTC step2 클론코딩 선물하기 구현

## Product 객체
- **id** : 상품 id
- **name** : 상품 이름
- **price** : 상품 가격
- **imageUrl** : 상품 사진 url

## 기능 소개
### ProductDao
- **selectAllProduct** : 모든 상품 목록 List반환
- **selectProduct** : id에 해당하는 상품 하나를 반환
- **insertProduct** : 상품 추가
- **deleteProduct** : 상품 삭제
- **updateProduct** : 상품 수정

### IndexController
- **index** : index.html 연결
- **postform** : postform.html 연결
- **editform** : editform.html 연결

### ProductController
- **getProductsController** : 모든 상품 목록 List index.html로 반환
- **getProductsListController** : 모든 상품 목록 List반환
- **getProductByIdController** : id에 해당하는 상품 하나를 반환
- **postProductController** : 상품 추가 후 index로 리다이렉트
- **deleteProductController** : 상품 삭제 후 index로 리다이렉트
- **updateProductController** : 상품 수정 후 index로 리다이렉트

### CatchError
- **isCorrectName** : 이름의 유효성을 검사한다
- **isContainsKakao** : 이름에 Kakao가 포함되 있는지 검사한다
-
### 유효성
- 클라이언트가 어떤 부분이 왜 잘못되었는지 인지할 수 있도록 응답제공
- 상품 이름 : 공백포함 최대 15자
- 특수 문자 : ( ), [ ], +, -, &, /, _ 이외 사용불가
- "카카오"가 포함된 문구는 담당 MD와 협의한 경우에만 사용 가능