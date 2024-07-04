package gift;

import jakarta.validation.ValidationException;

public class Product {
    private long id;
    private ProductName name;
    private int price;
    private String imageUrl;

    // 기본 생성자
    public Product() {}

    public Product(long id, String name, int price, String imageUrl) throws ValidationException {
        this.id = id;
        this.name = new ProductName(name);
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
        return name.getName();
    }

    public void setName(String name) throws ValidationException {
        this.name = new ProductName(name);
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