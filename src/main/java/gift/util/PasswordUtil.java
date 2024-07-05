package gift.util;


import java.security.SecureRandom;
import java.util.Base64;

public class PasswordUtil {
    private static final SecureRandom RANDOM = new SecureRandom();
    private static final int SALT_LENGTH = 16;

    public static String hashPassword(String password) {
        return Base64.getEncoder().encodeToString(password.getBytes());

    }

    public static boolean checkPassword(String rawPassword, String hashedPassword) {
        String encodedPassword = Base64.getEncoder().encodeToString(rawPassword.getBytes());
        return encodedPassword.equals(hashedPassword);
    }

    public static String decodeCredentials(String base64Credentials) {
        byte[] decodedBytes = Base64.getDecoder().decode(base64Credentials);
        return new String(decodedBytes);
    }
}
