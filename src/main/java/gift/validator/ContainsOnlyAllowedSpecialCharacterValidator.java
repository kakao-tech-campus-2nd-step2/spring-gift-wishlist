package gift.validator;

import gift.customAnnotation.ContainsOnlyAllowedSpecialCharacter;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.function.Predicate;

public class ContainsOnlyAllowedSpecialCharacterValidator implements ConstraintValidator<ContainsOnlyAllowedSpecialCharacter, String> {

    private static final Set<Character> ALLOWED_SPECIAL_CHARACTERS = new HashSet<>(Arrays.asList('(', ')', '[', ']', '+', '-', '&', '/', '_',' '));

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        Predicate<String> containsOnlyAllowedSpecialCharacters = s ->
                s.chars().allMatch(c ->
                        Character.isLetterOrDigit(c) || ALLOWED_SPECIAL_CHARACTERS.contains((char) c));
        return containsOnlyAllowedSpecialCharacters.test(value);
    }

}
