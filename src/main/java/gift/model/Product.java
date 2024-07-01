package gift.model;

public class Product {
    // 필드 생성
    private Long id;
    private String name;
    private int price;
    private String imageUrl;

    public Product(){}

//    // 생성자
//    public Product(Long id, String name, int price, String imageUrl){
//        this.id = id;
//        this.name = name;
//        this.price = price;
//        this.imageUrl = imageUrl;
//    }

    // Getter 메서드들
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

    // Setter 메서드들
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
}
