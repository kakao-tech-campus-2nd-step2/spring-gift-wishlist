package gift;


public class Product {
    private long id;
    private String name;
    private int price;
    private String imageUrl;

    public Product() {
        this.id = -1;
        this.name = "";
        this.price = 0;
        this.imageUrl = "";
    }

    public Product(long id, String name, int price, String imageUrl) {
        this.id = id;
        this.name = name;
        this.price = Math.max(price,0);
        this.imageUrl = imageUrl;
    }

    public Product(String name, int price, String imageUrl) {
        this.id = -1;
        this.name = name;
        this.price = Math.max(price, 0);
        this.imageUrl = imageUrl;
    }

    public long getId() {
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

    public void setId(long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(int price) {
        this.price = Math.max(price,0);
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}

