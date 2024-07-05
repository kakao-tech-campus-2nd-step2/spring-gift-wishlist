package gift.dto.requestDTO;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record ProductRequestDTO(
    @Size(max = 15, message = "최대 15자까지 입력할 수 있습니다.")
    @Pattern(regexp = "^(?!.*카카오)[\\w가-힣ㄱ-ㅎㅏ-ㅣ()\\[\\]+\\-&/_]*$" , message = "특수문자는 (), [], +, -, &, /, _ 만 가능합니다\n\"카카오\"가 포함된 문구는 담당 MD와 협의한 경우에만 사용할 수 있습니다\"")
    @NotBlank
    String name,
    @Min(1)
    int price,
    @NotBlank
    String imageUrl) {}
