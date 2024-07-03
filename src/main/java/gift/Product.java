package gift;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

public record Product(
    @NotNull Long id,

    @Size(min = 1, max = 15)
    @Pattern(regexp = "^[a-zA-Z가-힣0-9 ()\\[\\]+\\-&/_]*$")
    String name,

    @Positive Integer price,

    String imageUrl
) {}
