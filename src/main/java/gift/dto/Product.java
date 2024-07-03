package gift.dto;

import gift.customAnnotation.ContainsOnlyAllowedSpecialCharacter;
import gift.customAnnotation.NotContainsKakao;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class Product {

    private Long id;
    @NotBlank
    @Size(max = 15, message = "상품 이름은 공백을 포함하여 최대 15자까지 입력할 수 있습니다.")
    @NotContainsKakao
    @ContainsOnlyAllowedSpecialCharacter
    private String name;
    private int price;
    private String imageUrl;

    public Product() {
    }

    public Product(String name, int price, String imageUrl) {
        this.name = name;
        this.price = price;
        this.imageUrl = imageUrl;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public void setImageUrl(String imageUrl) {
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
