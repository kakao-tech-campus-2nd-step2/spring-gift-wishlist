package gift.Service;

import jakarta.servlet.http.HttpServletRequest;
import java.util.Enumeration;
import org.apache.logging.log4j.util.Strings;
import org.springframework.stereotype.Component;

@Component
public class AuthorizationExtractor { // 토큰 추출
    public static final String AUTHORIZATION = "Authorization";
    public static final String ACCESS_TOKEN_TYPE = AuthorizationExtractor.class.getSimpleName() + ".ACCESS_TOKEN_TYPE";// 무슨역할 인지 모르니 공부

    public String extract(HttpServletRequest request, String type) {
        Enumeration<String> headers = request.getHeaders(AUTHORIZATION);// 헤더의 AUTHORIZATION 필드의 값을 갖고옴
        while (headers.hasMoreElements()) {
            String value = headers.nextElement();
            if (value.toLowerCase().startsWith(type.toLowerCase())) {// bearer타입이면
                return value.substring(type.length()).trim();
            }
        }
        return Strings.EMPTY;
    }
}
