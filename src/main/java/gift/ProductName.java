package gift;

import jakarta.validation.ValidationException;

public class ProductName {
    private static final int MAX_LENGTH = 15;
    private static final String SPECIAL_CHARS = "()[\\]+-&/_";
    private static final String FORBIDDEN_KEYWORD = "카카오";

    private final String name;

    public ProductName(String name) throws ValidationException {
        validate(name);
        this.name = name;
    }

    public String getName() {
        return name;
    }

    private void validate(String name) throws ValidationException {
        if (name == null || name.isEmpty()) {
            throw new ValidationException("제품 이름이 비어있으면 안 됩니다.");
        }

        if (name.length() > MAX_LENGTH) {
            throw new ValidationException("제품 이름은 최대 15자까지 입력 가능합니다.");
        }

        if (name.chars().anyMatch(c -> !Character.isLetterOrDigit(c) && SPECIAL_CHARS.indexOf(c) == -1)) {
            throw new ValidationException("\"()[\\]+-&/_\"를 제외한 특수문자를 사용할 수 없습니다.");
        }

        if (name.contains(FORBIDDEN_KEYWORD)) {
            throw new ValidationException("해당 단어는 포함이 제한됩니다.: 카카오");
        }
    }
}