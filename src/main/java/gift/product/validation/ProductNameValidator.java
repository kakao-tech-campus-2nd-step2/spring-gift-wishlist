package gift.product.validation;

import gift.product.exception.ProductNameContainsException;
import gift.product.exception.ProductNameLengthException;
import gift.product.exception.ProductNamePatternException;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.regex.Pattern;

public class ProductNameValidator implements ConstraintValidator<ValidProductName, String> {
    private static final int MAX_LENGTH = 15;
    private static final Pattern PATTERN = Pattern.compile("^[a-zA-Z0-9가-힣 ()\\[\\]+\\-&/_]*$");
    private static final List<String> KEY_WORDS = Arrays.asList("카카오", "kakao");

    @Override
    public boolean isValid(String string, ConstraintValidatorContext constraintValidatorContext) {
        if (Objects.isNull(string) || string.length() > MAX_LENGTH) {
            throw new ProductNameLengthException();
        }

        if (!PATTERN.matcher(string).matches()) {
            throw new ProductNamePatternException();
        }
        if (KEY_WORDS.stream().anyMatch(string::contains)) {
            throw new ProductNameContainsException();
        }

        return true;
    }
}
