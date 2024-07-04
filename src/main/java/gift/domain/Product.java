package gift.domain;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class Product {
    private Long id;

    @NotBlank(message = "Name cannot be blank")
    @Size(max = 15, message = "Name cannot be longer than 15 characters")
    @Pattern(regexp = "^[\\w\\s\\(\\)\\[\\]+\\-&/_]*$", message = "Name contains invalid characters") // 정규표현식을 통한 특수 문자 제어
    private String name;
    private int price;
    private String imageUrl;

    public Product() {
    }

    // constructor (use id)
    public Product(Long id, String name, int price, String imageUrl) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.imageUrl = imageUrl;
    }

    // constructor (don't use id)
    public Product(String name, int price, String imageUrl) {
        this.name = name;
        this.price = price;
        this.imageUrl = imageUrl;
    }


    // getter and setter
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