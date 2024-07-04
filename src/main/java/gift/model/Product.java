package gift.model;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import org.hibernate.validator.constraints.Length;

public class Product {
    private long id;

    @NotNull(message = "이름을 입력해주세요.")
    @Length(min = 1, max=15, message = "1자 ~ 15자까지 가능합니다.")
    @Pattern(regexp = "^(?!.*카카오).*$", message = "카카오가 포함된 문구는 담당 MD와 협의한 경우에만 사용 할 수 있습니다.")
    @Pattern(regexp ="^[a-zA-Z0-9가-힣\\(\\)\\[\\]\\+\\-\\&\\/\\_]*$",message="사용불가한 특수 문자가 포함되어 있습니다.")
    private String name;
    private int price;
    private String imageUrl;

    // 기본 생성자
    public Product() {}

    public Product(long id, String name, int price, String imageUrl) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.imageUrl = imageUrl;
    }

    // Getters and Setters
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