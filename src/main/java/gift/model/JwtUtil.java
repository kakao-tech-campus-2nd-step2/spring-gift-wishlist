package gift.model;

public class JwtUtil {
    private static String secretKey = "Yn2kjibddFAWtnPJ2AFlL8WXmohJMCvigQggaEypa5E=";

    public static String generateToken(User user) {
        return user.getJwtToken(secretKey);
    }
}