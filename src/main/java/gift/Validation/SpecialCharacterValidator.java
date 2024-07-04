package gift.Validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SpecialCharacterValidator implements
    ConstraintValidator<SpecialCharacterValidation, String> {

    // ASCII code 내의 032~047, 058~064, 091~096, 123~126는 전부 특수문자로 간주
    // 032 : x20, 047 : x2F, 058 : x3A, 064 : x40,
    // 091 : x5B, 096 : x60, 123 : x7B, 126 : x7E
    private final String SPECIAL_CHARACTER_PATTERN = "[\\x20-\\x2F\\x3A-\\x40\\x5B-\\x60\\x7B-\\x7E]";
    private final Pattern pattern = Pattern.compile(SPECIAL_CHARACTER_PATTERN);

    // (, ), [, ], +, -, &, /, _ 만 허용
    private final String ALLOWED_SPECIAL_CHARACTER = "()[]+-&/_";

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value == null) {
            return false;
        }

        Matcher matcher = pattern.matcher(value);

        while (matcher.find()) {
            if (!ALLOWED_SPECIAL_CHARACTER.contains(matcher.group())) {
                return false;
            }
        }

        return true;
    }
}