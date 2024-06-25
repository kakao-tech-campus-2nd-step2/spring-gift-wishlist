package gift.product.model;

public class Product {
    protected Long id;        // 선물의 고유 식별자
    protected String name;    // 선물의 이름
    protected int price = 0;      // 선물의 가격
    protected String imageUrl; // 선물 이미지 URL
    protected boolean isActive = true; // 선물의 활성화 상태

    public Product(Long id, String name, int price, String imageUrl, Boolean isActive) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.imageUrl = imageUrl;
        this.isActive = isActive;
    }
}