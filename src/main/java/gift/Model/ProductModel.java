package gift.Model;

public class ProductModel {
    private long id;
    private String name;
    private int price;
    private String imageURL;

    public ProductModel() {
    }

    public ProductModel(long id, String name, int price, String imageURL) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.imageURL = imageURL;
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

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }
}
