package gift;

import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record Product(

    long id,

    @Size(max=15, message="Name is too long!")
    @Pattern(regexp = "^[a-zA-Z0-9 ()\\[\\]+\\-\\&\\/\\_가-힣]*$", message = "Name has invalid chracter")
    String name,
    int price,
    String imageUrl

){}