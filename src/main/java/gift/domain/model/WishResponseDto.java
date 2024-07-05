package gift.domain.model;

public class WishResponseDto {

    private Long id;
    private Long productId;
    private String productName;
    private Long productPrice;
    private String productImageUrl;

    public WishResponseDto(Long id, Long productId, String productName, Long productPrice,
        String productImageUrl) {
        this.id = id;
        this.productId = productId;
        this.productName = productName;
        this.productPrice = productPrice;
        this.productImageUrl = productImageUrl;
    }
}
