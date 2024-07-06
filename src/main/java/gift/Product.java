package gift;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Min;

public class Product {
    private Long id;
    @NotBlank(message = "이름은 필수 입력 값입니다.")
    @Size(max=15, message = "이름은 공백을 포함하여 최대 15자까지 입력 가능합니다.")
    @Pattern(regexp = "^[a-zA-Z0-9가-힣_()+&/ ]*$",
            message = "이름에 특수 문자는 (, ), [, ], +, -, &, /, _ 만 사용 가능합니다.")
    private String name;
    @Min(value = 0, message = "가격은 0원 이상의 값만 가능합니다.")
    private int price;
    private String imageUrl;

    public Product() {}

    public Product(Long id, String name, int price, String imageUrl) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.imageUrl = imageUrl;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }

    public String getImageUrl() {
        return imageUrl;
    }
}
