# spring-gift-whishlist

# 진행 방식
- 미션은 과제 진행 요구 사항, 기능 요구 사항, 프로그래밍 요구 사항 세 가지로 구성되어 있다.
세 개의 요구 사항을 만족하기 위해 노력한다. 
- 특히 기능을 구현하기 전에 기능 목록을 만들고, 기능 단위로 커밋 하는 방식으로 진행한다.
- 기능 요구 사항에 기재되지 않은 내용은 스스로 판단하여 구현한다.
-------
# 과제 진행 요구 사항
- 기능을 구현하기 전 README.md에 구현할 기능 목록을 정리해 추가한다.
Git의 커밋 단위는 앞 단계에서 README.md에 정리한 기능 목록 단위로 추가한다.
- AngularJS Git Commit Message Conventions을 참고해 커밋 메시지를 작성한다.
------
# 기능 요구 사항
- 사용자가 로그인하고 사용자별 기능을 사용할 수 있도록 구현한다.

  - 아래 예시와 같이 HTTP 메시지를 주고받도록 구현한다.
  
    - Request
      - 
        POST /login/token HTTP/1.1
    
        content-type: application/json
    
        host: localhost:8080

          {
            "password": "password",
            "email": "admin@email.com"
          }
    
    - Response 
    
        HTTP/1.1 200
      
        Content-Type: application/json
    
          {
            "accessToken": ""
          }
- 상품을 추가하거나 수정하는 경우, 클라이언트로부터 잘못된 값이 전달될 수 있다. 
- 잘못된 값이 전달되면 클라이언트가 어떤 부분이 왜 잘못되었는지 인지할 수 있도록 응답을 제공한다.
------
# 기능 구현 사항
- 상품 이름은 공백을 포함하여 최대 15자까지 입력할 수 있다.
- 특수 문자 가능: ( ), [ ], +, -, &, /, _ 그 외 특수 문자 사용 불가
- "카카오"가 포함된 문구는 담당 MD와 협의한 경우에만 사용할 수 있다.
