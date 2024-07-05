package gift.model;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record Product(long id, ProductName name, int price, String imageUrl, int amount) { }
