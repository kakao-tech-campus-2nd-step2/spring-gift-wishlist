# spring-gift-wishlist

## 과제 요구사항
1. 상품을 조회, 추가, 수정, 삭제할 수 있는 간단한  HTTP API를 구현한다.
   ```GET /api/products HTTP/1.1``` -> 이와 같이 Request를 받았을 때
```HTTP/1.1 200 
Content-Type: application/json

[
  {
    "id": 8146027,
    "name": "아이스 카페 아메리카노 T",
    "price": 4500,
    "imageUrl": "https://st.kakaocdn.net/product/gift/product/20231010111814_9a667f9eccc943648797925498bdd8a3.jpg"
  }
]
```
-> Response는 이렇게 출력한다.
2. Thymeleaf를 사용하여 서버 사이드 렌더링을 구현한다.
3. 상품 이미지의 경우, 파일을 업로드하지 않고 URL을 직접 입력한다.
4. 메모리에 저장하는 상품 정보를 데이터베이스에 저장한다.
5. 상품을 추가하거나 수정하는 경우, 클라이언트로부터 잘못된 값이 전달될 수 있다. 잘못된 값이 전달되면 클라이언트가 어떤 부분이 왜 잘못되었는지 인지할 수 있도록 응답을 제공한다.
   - 상품 이름은 공백을 포함하여 최대 15자까지 입력할 수 있다.
   - 특수 문자
   - 가능: (), [], +, -, &, /, _
   - 그 외 특수 문자 사용불가
   - "카카오"가 포함된 문구는 담당 MD와 협의한 경우에만 사용할 수 있다.
6. 사용자가 회원 가입, 로그인, 추후 회원별 기능을 이용할 수 있도록 구현한다.
   - 회원은 이메일과 비밀번호를 입력하여 가입한다.
   - 토큰을 받으려면 이메일과 비밀번호를 보내야 하며, 가입한 이메일과 비밀번호가 일치하면 토큰이 발급된다.
   - 토큰을 생성하는 방법에는 여러 가지가 있다. 방법 중 하나를 선택한다.
   - (선택) 회원을 조회, 추가, 수정, 삭제할 수 있는 관리자 화면을 구현한다.
7. 이전 단계(회원 가입, 로그인 등)에서 로그인 후 받은 토큰을 사용하여 사용자별 위시 리스트 기능을 구현한다.
   - 위시 리스트에 등록된 상품 목록을 조회할 수 있다.
   - 위시 리스트에 상품을 추가할 수 있다.
   - 위시 리스트에 담긴 상품을 삭제할 수 있다.
   - 실행 결과: 사용자 정보는 요청 헤더의 ```Authorization``` 필드를 사용한다.
     - ```Authorization: <유형> <자격증명```
       
     ``` Authorization: Bearer token```
   
Request: 
```
POST /members/register HTTP/1.1
content-type: application/json
host: localhost:8080

{
    "email": "admin@email.com",
    "password": "password"
}
```
Response:
```dtd
HTTP/1.1 200
Content-Type: application/json

{
    "token": ""
}

```
login:
```dtd
POST /members/login HTTP/1.1
content-type: application/json
host: localhost:8080

{
    "email": "admin@email.com",
    "password": "password"
}
```
Response:
```dtd
HTTP/1.1 200
Content-Type: application/json

{
    "token": ""
}
```
**HandelrMethodArgumentResolver**
 - 컨트롤러 메서드에 진입하기 전 처리를 통해 객체를 주입할 수 있다.
```dtd
public class LoginMemberArgumentResolver implements HandlerMethodArgumentResolver {
    private final MemberService memberService;

    public LoginMemberArgumentResolver(MemberService memberService) {
        this.memberService = memberService;
    }

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return parameter.hasParameterAnnotation(LoginMember.class);
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
    ...
            return new Member(1L, "test@email.com", "1234");
    }

```
```dtd
@PostMapping("/wishes")
public void create(
    @RequestBody WishRequest request,
    @LoginMember Member member
) {
}
```


## 프로그래밍 요구사항
- 자바 코드 컨벤션을 지키면서 프로그래밍한다.
- 기본적으로 google java style guide를 원칙으로 한다
- 단 들여쓰기는 2spaces가 아닌 4spaces로 한다
  indent depth를 3이 넘지 않도록 구현한다. 2까지만 허용한다.
- 예를 들어 while문 안에 if문이 있으면 들여쓰기는 2이다.
  3항 연산자를 쓰지 않는다.
  함수(또는 메서드)의 길이가 15라인을 넘어가지 않도록 구현한다.
- 함수(또는 메서드)가 한 가지 일만 잘 하도록 구현한다.
  else 예약어를 쓰지 않는다.
- switch/case도 허용하지 않는다.
  (과제 요구사항 4번부터 생긴 요구사항)
  H2 데이터베이스를 사용한다.
  사용하는 테이블은 애플리케이션이 실행될 때 구축되어야 한다.
  JdbcTemplace, SimpleJdbcinser, JdbcClient와 같은 도구를 사용할 수 있다.
  SQL 스크립트
- 스키마 스크립트는 schema.sql, 데이터 스크립트는 data.sq
- 스키마 및 데이터 스크립트의 위치는 각각 spring.sql.init.schema-locations 및 spring.sql.init.data-locations를 사용하여 사용자 지정할 수 있다.
- 기본적으로 내장된 메모리 내 데이터베이스를 사용할 때만 수행된다.
- 항상 sql 데이터베이스를 초기화하려면 spring.sql.init.mode를 always로 설정한다.
  (과제 요구사항 5번)
  spring-boot-starter-validation 의존성을 명시적으로 추가한다.
  ``` implementation 'spring-boot-starter-validation' ```
- (6번) 힌트 **Basic 인증**
  - Base64로 인코딩된 사용자 ID, 비밀번호 쌍을 인증 정보(credentials) 값으로 사용한다.
  
  ```Authorization: Basic base64({EMAIL}:{PASSWORD}) ```
- JSON Web Token
  - JJWT 라이브러리를 사용하여 JWT를 쉽게 만들 수 있다.
  ```
  dependencies {
    compileOnly 'io.jsonwebtoken:jjwt-api:0.12.6'
    runtimeOnly 'io.jsonwebtoken:jjwt-impl:0.12.6'
    runtimeOnly 'io.jsonwebtoken:jjwt-jackson:0.12.6'
  }
  ```
  ```
  String secretKey = "Yn2kjibddFAWtnPJ2AFlL8WXmohJMCvigQggaEypa5E=";
  String accessToken = Jwts.builder()
  .setSubject(member.getId().toString())
  .claim("name", member.getName())
  .claim("role", member.getRole())
  .signWith(Keys.hmacShaKeyFor(secretKey.getBytes()))
  .compact();  ```