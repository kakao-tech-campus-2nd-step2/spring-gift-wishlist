package gift;

public class Product {

    private Long id;
    private String name;
    private double price;
    private String imageUrl;


    public Product() {
    }

    public Product(String name, String imageUrl, double price) {
        this.name = name;
        this.price = price;
        this.imageUrl = imageUrl;
    }

    public String getName() {
        return name;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public double getPrice() {
        return price;
    }

    public Long getId() {
        return id;
    }
}
