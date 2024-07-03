package gift.Model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;

public record Product(Long id,
                      String name,
                      long price,
                      String imageUrl) {

}
