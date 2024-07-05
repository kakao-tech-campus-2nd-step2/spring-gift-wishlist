# spring-gift-whishlist

# 진행 방식

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

