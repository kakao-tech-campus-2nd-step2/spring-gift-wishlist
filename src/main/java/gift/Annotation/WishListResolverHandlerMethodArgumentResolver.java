package gift.Annotation;

import com.fasterxml.jackson.databind.ObjectMapper;
import gift.Controller.JwtUtil;
import gift.Model.WishListItem;
import io.jsonwebtoken.Claims;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import java.io.BufferedReader;
import java.util.Map;

@Component
public class WishListResolverHandlerMethodArgumentResolver implements HandlerMethodArgumentResolver {
    private final JwtUtil jwtUtil;

    @Autowired
    public WishListResolverHandlerMethodArgumentResolver(JwtUtil jwtUtil) {
        this.jwtUtil = jwtUtil;
    }

    @Override
    public boolean supportsParameter(MethodParameter methodParameter) {
        return methodParameter.getParameterType().equals(WishListItem.class);
    }

    @Override
    public Object resolveArgument(MethodParameter methodParameter, ModelAndViewContainer modelAndViewContainer, NativeWebRequest nativeWebRequest, WebDataBinderFactory webDataBinderFactory) throws Exception {
        HttpServletRequest request = (HttpServletRequest) nativeWebRequest.getNativeRequest();

        // 쿠키에서 JWT 토큰 추출
        String token = null;
        if (request.getCookies() != null) {
            for (Cookie cookie : request.getCookies()) {
                if ("token".equals(cookie.getName())) {
                    token = cookie.getValue();
                    break;
                }
            }
        }

        if (token == null) {
            throw new IllegalArgumentException("JWT token not found in cookies");
        }

        Claims claims = jwtUtil.decodeToken(token);

        // JSON 요청 본문 파싱
        Map<String, Object> requestBody = parseJsonRequestBody(nativeWebRequest);
        int userId = Integer.parseInt(claims.get("userId").toString());
        int productId = Integer.parseInt(requestBody.get("productId").toString());
        int quantity = Integer.parseInt(requestBody.get("quantity").toString());
        int price = Integer.parseInt(requestBody.get("price").toString()) * quantity;

        WishListItem wishListItem = new WishListItem();
        wishListItem.setUserId(userId);
        wishListItem.setProductId(productId);
        wishListItem.setCount(quantity);
        wishListItem.setPrice(price);

        return wishListItem;
    }

    // 요청 본문에서 JSON 데이터를 읽어서 Map으로 변환하는 메서드
    private Map<String, Object> parseJsonRequestBody(NativeWebRequest nativeWebRequest) throws Exception {
        HttpServletRequest request = (HttpServletRequest) nativeWebRequest.getNativeRequest();
        StringBuilder jsonBuilder = new StringBuilder();
        String line;
        try (BufferedReader reader = request.getReader()) {
            while ((line = reader.readLine()) != null) {
                jsonBuilder.append(line);
            }
        }
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(jsonBuilder.toString(), Map.class);
    }
}