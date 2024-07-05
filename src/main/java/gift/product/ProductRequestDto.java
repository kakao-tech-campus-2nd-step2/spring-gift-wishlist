package gift.product;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record ProductRequestDto(
    @NotBlank
    @Size(max = 15, message = "상품 이름은 공백을 포함하여 최대 15자까지 입력할 수 있습니다")
    @Pattern(regexp = "^[a-zA-Z0-9가-힣()*\\[\\]+\\-&/_]*$", message = "허용되지 않은 특수문자입니다")
    @Pattern(regexp = "^(?!.*카카오).*$", message = "'카카오'가 포함된 문구는 담당 MD와 협의한 경우에만 사용 가능합니다")
    String name,
    @Min(1)
    int price,
    @NotBlank
    String url) { }