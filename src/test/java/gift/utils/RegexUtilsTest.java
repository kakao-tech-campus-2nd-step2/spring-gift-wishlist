package gift.utils;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Set;
import java.util.stream.Stream;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class RegexUtilsTest {

    @ParameterizedTest(name = "{0}의 특수문자는 {1} 만을 포함하고 있어야 한다.")
    @MethodSource("specialCharsSuccessCase")
    void containsOnlyAllowedSpecialCharsSuccessCase(String input, Set<Character> allowedSpecialChars) {
        assertTrue(RegexUtils.containsOnlyAllowedSpecialChars(input, allowedSpecialChars));
    }

    private static Stream<Arguments> specialCharsSuccessCase() {
        return Stream.of(
            Arguments.of("abc", Set.of()),
            Arguments.of("abc", Set.of('!', '@', '#', '$', '%')),
            Arguments.of("ab!c!@#", Set.of('!', '@', '#', '$', '%')),
            Arguments.of("abc!@#%", Set.of('!', '@', '#', '$', '%')),
            Arguments.of("a@bc!#%$", Set.of('!', '@', '#', '$', '%')),
            Arguments.of("ab!c!@#%$", Set.of('!', '@', '#', '$', '%')),
            Arguments.of("ab^c!@#%$^&", Set.of('!', '@', '#', '$', '%', '^', '&')),
            Arguments.of("abc!@#%$^&*", Set.of('!', '@', '#', '$', '%', '^', '&', '*')));
    }

    @ParameterizedTest(name = "{0}의 특수문자는 {1} 외의 특수문자를 포함하고 있다.")
    @MethodSource("specialCharsFailCase")
    void containsOnlyAllowedSpecialCharsFailCase(String input, Set<Character> allowedSpecialChars) {
        assertFalse(RegexUtils.containsOnlyAllowedSpecialChars(input, allowedSpecialChars));
    }

    private static Stream<Arguments> specialCharsFailCase() {
        return Stream.of(
            Arguments.of("abc!", Set.of()),
            Arguments.of("a!b%!%c$", Set.of('!', '%')),
            Arguments.of("ab^c!@#%$^&)", Set.of('!', '@', '#', '$', '%', '^')),
            Arguments.of("abc!@#%$^&*[", Set.of('!', '@', '#', '$', '%', '^', '&')));
    }

}