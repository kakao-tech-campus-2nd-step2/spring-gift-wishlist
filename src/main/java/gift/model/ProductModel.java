package gift.model;

public class ProductModel {
    public long id; // 상품의 고유 식별자
    public String name; // 상품 이름
    public int price;
    public String imgUrl; // 상품 카테고리// 상품 가격

    // 생성자
    public ProductModel(long id, String name, int price, String imgUrl) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.imgUrl = imgUrl;
    }

    // 기본 생성자 : 매개변수 없이 호출될 수 있게 한다.
    public ProductModel() {
    }

    // Getter와 Setter 메서드
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