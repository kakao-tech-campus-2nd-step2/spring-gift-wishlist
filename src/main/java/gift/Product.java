package gift;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class Product {
    private Long id;
    @NotBlank(message = "이름은 필수 입력 값입니다.")
    @Size(max = 15, message = "이름은 최대 15자까지 입력 가능합니다.")
    @Pattern(regexp = "^[a-zA-Z0-9가-힣_()+&/ ]*$",
            message = "이름에는 특수 문자는 (, ), [, ], +, -, &, /, _ 만 사용 가능합니다.")
    private String name;
    private int price;
    private String imageUrl;

    public Product() {
    }

    public Product(Long id, String name, int price, String imageUrl) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.imageUrl = imageUrl;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
