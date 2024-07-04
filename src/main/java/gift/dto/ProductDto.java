package gift.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import org.hibernate.validator.constraints.URL;

public class ProductDto {
    private Long id;

    @NotBlank(message = "상품명을 입력하세요.")
    @Size(max = 15, message = "상품명은 공백 포함 최대 15자")
    @Pattern(regexp = "^[\\w\\s()\\[\\]+\\-&/]*$", message = "상품명에 잘못된 문자가 있습니다.")
    private String name;

    @Positive(message = "올바른 가격을 입력하세요.")
    private int price;

    @NotBlank(message = "이미지 URL을 입력하세요.")
    @URL(message = "잘못된 URL 입니다.")
    private String imgUrl;

    public ProductDto() {}

    public ProductDto(Long id, String name, int price, String imgUrl) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.imgUrl = imgUrl;
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

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }
}