package gift.Util;

import jakarta.servlet.http.HttpServletRequest;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

public class AuthUtil {

    public static String[] extractCredentials(HttpServletRequest request) {
        String authHeader = request.getHeader("Authorization");
        System.out.println("Authorization Header: " + authHeader); // 디버깅 출력

        if (authHeader != null && authHeader.startsWith("Basic ")) {
            // Extract base64 encoded {EMAIL}:{PASSWORD}
            String base64Credentials = authHeader.substring(6);
            String credentials = new String(Base64.getDecoder().decode(base64Credentials), StandardCharsets.UTF_8);
            // Split {EMAIL}:{PASSWORD}
            final String[] values = credentials.split(":", 2);

            return values;
        }

        System.out.println("No Authorization header or it doesn't start with Basic."); // 디버깅 출력
        return null;
    }
}
