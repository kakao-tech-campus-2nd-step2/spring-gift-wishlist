package gift.product.model;

import org.springframework.lang.NonNull;

public class Product {
    @NonNull
    private Long id;        // 선물의 고유 식별자
    @NonNull
    private String name;    // 선물의 이름
    @NonNull
    private int price;      // 선물의 가격
    private String imageUrl; // 선물 이미지 URL
    @NonNull
    private Boolean isActive = true; // 선물의 활성화 상태

    public Product(String name, int price, String imageUrl, Boolean isActive) {
        this.id = lastId;
        this.name = name;
        this.price = price;
        this.imageUrl = imageUrl;
        this.isActive = isActive;
    }

}