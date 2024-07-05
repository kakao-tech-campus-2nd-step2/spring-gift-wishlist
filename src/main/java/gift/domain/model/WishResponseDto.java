package gift.domain.model;

public class WishResponseDto {

    private Long id;
    private Long productId;
    private String productName;

    public Long getId() {
        return id;
    }

    public Long getProductId() {
        return productId;
    }

    public String getProductName() {
        return productName;
    }

    public Long getProductPrice() {
        return productPrice;
    }

    public String getProductImageUrl() {
        return productImageUrl;
    }

    private Long productPrice;
    private String productImageUrl;

    public WishResponseDto() {
    }

    public WishResponseDto(Long id, Long productId, String productName, Long productPrice,
        String productImageUrl) {
        this.id = id;
        this.productId = productId;
        this.productName = productName;
        this.productPrice = productPrice;
        this.productImageUrl = productImageUrl;
    }
}
