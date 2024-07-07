package gift.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record Product(Long id,
                      @NotBlank(message = "상품 이름은 최소 1자 이상이어야 합니다.")
                      @Size(max = 15, message = "상품 이름은 공백 포함 최대 15자까지 입력할 수 있습니다.")
                      @Pattern(regexp = "^[a-zA-Z0-9가-힣ㄱ-ㅎㅏ-ㅣ\\s\\(\\)\\[\\]\\+\\-\\&\\/\\_]*$", message = "상품 이름에 (), [], +, -, &, /, _ 외 특수 문자는 사용할 수 없습니다.")
                      @Pattern(regexp = "^(?!.*카카오).*$", message = "'카카오'가 포함된 문구는 담당 MD와 협의 후 사용 바랍니다.")
                      String name,
                      @NotBlank(message = "가격을 입력해야 합니다.")
                      @Pattern(regexp = "^\\d+$", message = "가격은 0이상의 숫자만 입력 가능합니다.")
                      String price,
                      @NotBlank(message = "이미지 URL을 입력해야 합니다.")
                      @Pattern(regexp = "^(http|https)://.*$", message = "유효한 이미지 URL을 입력해야 합니다.")
                      String imageUrl) {

    public Product {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("상품 이름은 최소 1자 이상이어야 합니다.");
        }
        if (name.length() > 15) {
            throw new IllegalArgumentException("상품 이름은 공백 포함 최대 15자까지 입력할 수 있습니다.");
        }
        if (!name.matches("^[a-zA-Z0-9가-힣ㄱ-ㅎㅏ-ㅣ\\s\\(\\)\\[\\]\\+\\-\\&\\/\\_]*$")) {
            throw new IllegalArgumentException("상품 이름에 (), [], +, -, &, /, _ 외 특수 문자는 사용할 수 없습니다.");
        }
        if (name.contains("카카오")) {
            throw new IllegalArgumentException("'카카오'가 포함된 문구는 담당 MD와 협의 후 사용 바랍니다.");
        }
        if (price == null || price.isBlank()) {
            throw new IllegalArgumentException("가격을 입력해야 합니다.");
        }
        if (price == null || !price.matches("^\\d+$")) {
            throw new IllegalArgumentException("가격은 0이상의 숫자만 입력 가능합니다.");
        }
        if (imageUrl == null || imageUrl.isBlank()) {
            throw new IllegalArgumentException("이미지 URL을 입력해야 합니다.");
        }
        if (imageUrl == null || !imageUrl.matches("^(http|https)://.*$")) {
            throw new IllegalArgumentException("유효한 이미지 URL을 입력해야 합니다.");
        }
    }

}
