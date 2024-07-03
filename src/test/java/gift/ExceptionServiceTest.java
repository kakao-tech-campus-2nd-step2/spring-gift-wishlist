package gift;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(SpringExtension.class)
public class ExceptionServiceTest {
    private ExceptionService exceptionService;
    private String validName;
    private String longName;
    private String invalidSpecialCharName;
    private String kakaoName;

    @BeforeEach
    public void setup() {
        exceptionService = new ExceptionService();
        validName = "ValidName";
        longName = "ThisNameIsWayTooLong";
        invalidSpecialCharName = "@@이름name";
        kakaoName = "카카오Name";
    }

    @DisplayName("정상 동작 테스트")
    @Test
    public void testFindNameException_ValidName() {
        // No exception should be thrown
        exceptionService.findNameException(validName);
    }

    @Test
    @DisplayName("이름 길이 테스트")
    public void testFindNameException_NameTooLong() {
        assertThrows(NameException.class, () -> {
            exceptionService.findNameException(longName);
        });
    }

    @Test
    @DisplayName("특수문자 테스트")
    public void testFindNameException_InvalidSpecialCharacter() {
        assertThrows(NameException.class, () -> {
            exceptionService.findNameException(invalidSpecialCharName);
        });
    }

    @Test
    @DisplayName("카카오 포함 테스트")
    public void testFindNameException_KakaoName() {
        assertThrows(NameException.class, () -> {
            exceptionService.findNameException(kakaoName);
        });
    }
}
