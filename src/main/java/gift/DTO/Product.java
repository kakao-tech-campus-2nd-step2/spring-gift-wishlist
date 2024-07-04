package gift.DTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record Product(
    @NotNull
    long id,

    @NotBlank(message = "상품명에는 빈 값을 입력할 수 없습니다.")
    @Size(min = 1, max = 15, message = "상품명은 공백 포함 최대 15자까지 입력 가능합니다.")
    @Pattern(
        regexp = "^[a-zA-Z0-9가-힣\\s\\(\\)\\[\\]\\+\\-\\&\\/\\_]*$",
        message = "상품명에는 특수 문자 (,),[,],+,-,&,/,_ 만 허용됩니다.")
    @Pattern(
        regexp = "^(?!.*카카오).*$",
        message = "\"카카오\" 문구를 사용하시려면 담당 MD와 협의해주세요.")
    String name,

    @NotNull
    long price,

    String imageUrl) {

}
