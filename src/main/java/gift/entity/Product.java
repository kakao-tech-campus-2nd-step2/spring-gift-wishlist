package gift.entity;

// 관리할 객체 만들기
public class Product {
    private long id;
    private String name;
    private int price;
    private String imgUrl;

    public Product(Long o, String name, int price, String imgUrl) {}

    // id 포함하지 않고 필드 초기화
    public Product(String name, int price, String imgUrl) {
        this.name = name;
        this.price = price;
        this.imgUrl = imgUrl;
    }

    // id 포함해서 필드 초기화
    public Product(long id, String name, int price, String imgUrl) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.imgUrl = imgUrl;
    }

    public void update(String name, int price, String imgUrl) {
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

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", imgUrl='" + imgUrl + '\'' +
                '}';
    }
}