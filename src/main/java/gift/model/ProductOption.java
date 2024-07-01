package gift.model;

import gift.dto.ProductOptionRequest;

public class ProductOption {
    private Long id;
    private Long productId;

    private String name;
    private Integer additionalPrice;

    public ProductOption(Long productId, String name, Integer additionalPrice) {
        this.productId = productId;
        this.name = name;
        this.additionalPrice = additionalPrice;
    }

    public ProductOption(Long id, Long productId, String name, Integer additionalPrice) {
        this.id = id;
        this.productId = productId;
        this.name = name;
        this.additionalPrice = additionalPrice;
    }

    public Long getId() {
        return id;
    }

    public Long getProductId() {
        return productId;
    }

    public String getName() {
        return name;
    }

    public Integer getAdditionalPrice() {
        return additionalPrice;
    }

    public void updateFrom(ProductOptionRequest productOptionRequest) {
        this.name = productOptionRequest.name();
        this.additionalPrice = productOptionRequest.additionalPrice();
    }

    public static ProductOption from(ProductOptionRequest dto) {
        return new ProductOption(dto.productId(), dto.name(), dto.additionalPrice());
    }
}
