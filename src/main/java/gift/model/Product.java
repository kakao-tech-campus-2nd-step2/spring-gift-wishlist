package gift.model;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public record Product(
        Long id,

        @NotBlank(message = "Name is mandatory")
        @Size(max = 15, message = "Name must be less than or equal to 15 characters")
        @Pattern(regexp = "^[ㄱ-ㅎ가-힣a-zA-Z0-9\\s\\(\\)\\[\\]+&\\-/_]*$", message = "Invalid characters in name")
        String name,

        @Min(value = 0, message = "Price must be greater than or equal to 0")
        int price,

        @NotBlank(message = "Image URL is mandatory")
        String imageUrl
) {
}