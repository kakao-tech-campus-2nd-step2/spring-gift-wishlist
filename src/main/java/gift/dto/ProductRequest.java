package gift.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public record ProductRequest(
        @NotNull
        @Pattern(regexp = "^[\\w\\d\\s\\(\\)\\[\\]\\+\\-\\&\\/\\_\\uAC00-\\uD7A3]{0,15}$")
        String name,
        @Min(0)
        int price,
        String imageUrl) { }