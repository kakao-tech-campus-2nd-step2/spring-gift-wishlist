package gift;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;

public class Product {
    private Long id;

    @NotEmpty(message = "상품 이름은 필수 입력 값입니다.")
    @jakarta.validation.constraints.Size(max = 15, message = "상품 이름은 공백 포함 15자 이하로 입력해야 합니다.")
    @Pattern(regexp = "^[a-zA-Z0-9\\(\\)\\[\\]\\+\\-&/_가-힣]*$", message = "[ ] ( ), [ ], +, -, &, /, _ 의 특수문자만 사용가능합니다.")
    private String name;
    private double price;
    private String imageUrl;

    public Product(Long id, String name, double price, String imageUrl) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.imageUrl = imageUrl;
    }

    public Product() {

    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
