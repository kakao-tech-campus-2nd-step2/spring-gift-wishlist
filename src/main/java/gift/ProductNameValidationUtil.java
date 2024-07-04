package gift;

import java.util.regex.Pattern;

public class ProductNameValidationUtil {

    private static final Pattern SPECIAL_CHAR_PATTERN = Pattern.compile("[^a-zA-Z0-9\\s()\\[\\]+\\-&/_가-힣]");

    public static boolean isValidLength(String productName){
        return !productName.isEmpty() & productName.length() <= 15;
    }

    public static boolean containsSpecialCharacters(String productName){
        return !productName.isEmpty() & SPECIAL_CHAR_PATTERN.matcher(productName).find();
    }

    public static boolean containsKAKAO(String productName){
        return !productName.isEmpty() & productName.contains("카카오");
    }
}