package gift.model;

public class Product {

    //id 값을 위해 정적 변수 선언
    private static Long id = 1L;

    private String name;
    private int price;
    private String imageUrl;

    public Product(String name, int price, String imageUrl) {
        this.id = Product.id; // 정적 변수 ID
        this.name = name;
        this.price = price;
        this.imageUrl = imageUrl;
    }

    // id 값 증가
    public static void increase() {
        id += 1;
    }

    public Long getId() {
        return this.id;
    }

    public static void setId(Long id) {
        Product.id = id;
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
