package gift.entity;

import gift.exception.BusinessException;
import gift.exception.ErrorCode;
import org.springframework.http.HttpStatus;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class ProductName {

    @NotNull(message = "상품 이름은 null 일 수 없습니다.")
    @Size(max = 15, message = "상품 이름은 15자 이하여야합니다. (공백포함)")
    @Pattern(regexp = "^[a-zA-Z0-9가-힣\\s\\(\\)\\[\\]+\\-&/_]*$", message = "( ), [ ], +, -, &, /, _ 외 특수문자는 사용 불가능합니다.")
    private final String value;

    public ProductName(String value) {
        if (value == null || value.trim().isEmpty()) {
            throw new BusinessException(ErrorCode.INVALID_NAME_SIZE, HttpStatus.BAD_REQUEST);
        }
        if (value.contains("카카오")) {
            throw new BusinessException(ErrorCode.KAKAO_NAME_NOT_ALLOWED, HttpStatus.BAD_REQUEST);
        }
        if (value.length() > 15) {
            throw new BusinessException(ErrorCode.INVALID_NAME_SIZE, HttpStatus.BAD_REQUEST);
        }
        if (!value.matches("^[a-zA-Z0-9가-힣\\s\\(\\)\\[\\]+\\-&/_]*$")) {
            throw new BusinessException(ErrorCode.INVALID_NAME_PATTERN, HttpStatus.BAD_REQUEST);
        }
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}