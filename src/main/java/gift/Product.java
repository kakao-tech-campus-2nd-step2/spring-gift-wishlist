package gift;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

@Entity
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Product name is mandatory")
    @Size(max = 15, message = "Product name can be up to 15 characters")
    @Pattern(
            regexp = "^[\\w\\s\\(\\)\\[\\]\\+\\-\\&\\/]*$",
            message = "Product name contains invalid characters"
    )
    private String name;
    private int price;
    private String imageUrl;

    public Product() {}

    public Product(long id, String name, int price, String imageUrl) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.imageUrl = imageUrl;
    }

    // Getter and Setter methods
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
        if (name.contains("카카오")) {
            throw new KakaoNameException("contack md");
        }
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

// 별도 예외 클래스 추가
class KakaoNameException extends RuntimeException {
    public KakaoNameException(String message) {
        super(message);
    }
}
