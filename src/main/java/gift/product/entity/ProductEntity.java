package gift.product.entity;

// 상품의 정보를 담는 엔터티
public class ProductEntity {
    private long id;
    private String name;
    private int price;
    private String image;
    private boolean md;

    public ProductEntity(long id, String name, int price, String image, boolean md) {
        setId(id);
        setName(name);
        setPrice(price);
        setImage(image);
        setMd(md);
    }

    // getter는 JSON으로 바꿀 수 있도록 public으로 선언
    public long getId() {
        return id;
    }

    // setter는 다른 클래스에서 사용을 지양하기 위해 private으로 선언
    private void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    private void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    private void setPrice(int price) {
        this.price = price;
    }

    public String getImage() {
        return image;
    }

    private void setImage(String image) {
        this.image = image;
    }

    public boolean getMd() {
        return md;
    }

    private void setMd(boolean md) {
        this.md = md;
    }
}
