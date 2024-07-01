package gift;

public class Product {
    private long id;
    private String name;
    private int price;
    private String imgUrl;

    public Product(){}

    public Product(long id, String name, int price, String imgUrl) {
        this.id=id;
        this.name=name;
        this.price=price;
        this.imgUrl=imgUrl;
    }

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

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }
}
