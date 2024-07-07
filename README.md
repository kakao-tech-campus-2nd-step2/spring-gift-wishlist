# spring-gift-wishlist

## Step3

- [x] 위시 리스트 MVC 생성
  - [x] 위시 리스트 모델 생성
  - [x] 위시 리스트 
- [ ] 위시 리스트 상품 목록 조회
- [ ] 위시 리스트 상품 추가
- [ ] 위시 리스트 상품 삭제
- [ ] 위시 리스트 상품 수량 변경


## HandlerMethodARgumentResolver
HandlerMethodArgumentResolver는 Spring Boot에서 요청(request) 파라미터를 컨트롤러 메서드의 파라미터로 변환해주는 역할을 하는 인터페이스입니다. 이를 통해 Spring MVC는 클라이언트로부터 받은 다양한 형식의 데이터를 적절한 객체로 변환하여 컨트롤러 메서드에 전달할 수 있습니다.

HandlerMethodArgumentResolver를 사용하면, 다음과 같은 작업을 수행할 수 있습니다:

1. **커스텀 파라미터 처리**: 기본 제공되는 파라미터 타입 외에 커스텀 타입의 파라미터를 컨트롤러 메서드에 주입하고 싶을 때 사용합니다.
2. **복잡한 로직 캡슐화**: 특정 파라미터 처리 로직이 복잡할 때, 이를 캡슐화하여 재사용성을 높일 수 있습니다.
3. **유효성 검사**: 파라미터를 객체로 변환하기 전에 유효성 검사를 수행할 수 있습니다.

### 사용 예제

HandlerMethodArgumentResolver를 구현하고 등록하는 간단한 예제를 통해 더 잘 이해할 수 있습니다.

#### 1. CustomObject를 파라미터로 받는 경우

먼저, `CustomObject`라는 클래스가 있다고 가정합니다:

```java
public class CustomObject {
    private String value;

    // getter, setter
}
```

#### 2. HandlerMethodArgumentResolver 구현

`CustomObjectArgumentResolver`라는 이름으로 `HandlerMethodArgumentResolver`를 구현합니다:

```java
import org.springframework.core.MethodParameter;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

public class CustomObjectArgumentResolver implements HandlerMethodArgumentResolver {

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return parameter.getParameterType().equals(CustomObject.class);
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer,
                                  NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
        String value = webRequest.getParameter("value");
        CustomObject customObject = new CustomObject();
        customObject.setValue(value);
        return customObject;
    }
}
```

#### 3. ArgumentResolver 등록

이제 이 `CustomObjectArgumentResolver`를 Spring MVC 설정에 등록합니다:

```java
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
        resolvers.add(new CustomObjectArgumentResolver());
    }
}
```

#### 4. 컨트롤러에서 사용

이제 컨트롤러 메서드에서 `CustomObject`를 파라미터로 받을 수 있습니다:

```java
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/example")
public class ExampleController {

    @GetMapping
    public String exampleMethod(CustomObject customObject) {
        return "Value: " + customObject.getValue();
    }
}
```

이제 클라이언트가 `/example?value=test`와 같은 요청을 보내면, `exampleMethod`는 `CustomObject`를 파라미터로 받아 처리할 수 있습니다.

이렇게 `HandlerMethodArgumentResolver`는 Spring MVC에서 매우 유연하게 파라미터 처리를 할 수 있게 해주는 강력한 도구입니다.