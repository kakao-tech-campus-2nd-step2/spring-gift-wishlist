package gift.Product;

import org.hibernate.validator.constraints.Length;

import gift.Validation.SpecialCharacterValidation;

public record ProductDTO(
                @Length(min = 1, max = 15) @SpecialCharacterValidation String name, Integer price,
                String imageUrl) {
}
