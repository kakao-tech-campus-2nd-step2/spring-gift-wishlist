package gift.domain;

import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record Product(

    long id,

    @Size(max=15, message="Name is too long!")
    @Pattern(regexp = "^[a-zA-Z0-9 ()\\[\\]+\\-\\&\\/\\_가-힣]*$", message = "Name has invalid chracter")
    @Pattern(regexp = "^(?!.*카카오).*$", message = "'카카오'가 포함된 문구는 담당 MD와 협의한 경우에만 사용 가능합니다")
    String name,
    int price,
    String imageUrl

){}