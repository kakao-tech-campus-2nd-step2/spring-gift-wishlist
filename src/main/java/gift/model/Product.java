package gift.model;

public class Product {
    private Long id;        // 선물의 고유 식별자
    private String name;    // 선물의 이름
    private int price;      // 선물의 가격
    private String imageUrl; // 선물 이미지 URL
    private Boolean isActive; // 선물의 활성화 상태

    public Product(Long id, String name, int price, String imageUrl, Boolean isActive) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.imageUrl = imageUrl;
        this.isActive = isActive;
    }
}