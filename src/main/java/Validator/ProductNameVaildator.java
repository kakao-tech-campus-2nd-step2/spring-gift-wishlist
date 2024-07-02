package Validator;

public class ProductNameVaildator {
    public static ValidatedResult validateProduct(String productName) {
        ValidatedResult result = new ValidatedResult();
        if (productName == null || productName.isEmpty()) {
            result.setValid(false);
            result.setMessage("상품 이름은 비워 둘 수 없습니다.");
            return result;
        }
        if (productName.length() > 15) {
            result.setValid(false);
            result.setMessage("상품 이름은 최대 15자까지 입력할 수 있습니다.");
            return result;
        }
        if (!productName.matches("[\\w\\s()\\[\\]+\\-&/]+")) {
            result.setValid(false);
            result.setMessage("상품 이름에 허용되지 않는 특수 문자가 포함되어 있습니다.");
            return result;
        }
        if (productName.contains("카카오")) {
            result.setValid(false);
            result.setMessage("상품 이름에 '카카오'는 특정 조건 하에만 사용할 수 있습니다.");
            return result;
        }
        result.setValid(true);
        result.setMessage("상품 이름이 유효합니다.");
        return result;
    }
}
