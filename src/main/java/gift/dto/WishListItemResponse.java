package gift.dto;

public class WishListItemResponse {
    private Long id;
    private Long productId;
    private String productName;
    private int productPrice;
    private String productImageUrl;
    private int productQuantity;

    public WishListItemResponse(Long id, Long productId, String productName, int productPrice, String productImageUrl, int productQuantity) {
        this.id = id;
        this.productId = productId;
        this.productName = productName;
        this.productPrice = productPrice;
        this.productImageUrl = productImageUrl;
        this.productQuantity = productQuantity;
    }

    public Long getId() {
        return id;
    }

    public Long getProductId() {
        return productId;
    }

    public String getProductName() {
        return productName;
    }

    public int getProductPrice() {
        return productPrice;
    }

    public String getProductImageUrl() {
        return productImageUrl;
    }

    public int getProductQuantity() {
        return productQuantity;
    }

}