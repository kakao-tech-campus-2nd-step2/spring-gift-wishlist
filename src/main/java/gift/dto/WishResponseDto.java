package gift.dto;

public class WishResponseDto {
    private Long id;
    private Long productId;
    private String productName;
    private int productPrice;
    private String productImageUrl;
    private int quantity;

    public WishResponseDto(Long id, Long productId, String productName, int productPrice, String productImageUrl, int quantity) {
        this.id = id;
        this.productId = productId;
        this.productName = productName;
        this.productPrice = productPrice;
        this.productImageUrl = productImageUrl;
        this.quantity = quantity;
    }
}
