package gift.model;

import jakarta.validation.constraints.Pattern;

public class Gift {

    private Long id;
    @Pattern(regexp = "^(?!.*카카오).*$", message = "카카오 문구는 MD와 협의 후 사용가능합니다.")
    private String name;
    private int price;
    private String imageUrl;

    public Gift() {
    }

    public Gift(Long id, String name, int price, String imageUrl) {
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