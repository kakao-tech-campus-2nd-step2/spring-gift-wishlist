// WishList.java
package gift.entity;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Wish {

    private Long id;

    @JsonProperty("member_id")
    private Long memberId;

    @JsonProperty("product_id")
    private Long productId;

    @JsonProperty("product_number")
    private int productNumber;

    public Wish() {}

    public Wish(Long id, Long memberId, Long productId, int productNumber) {
        this.id = id;
        this.memberId = memberId;
        this.productId = productId;
        this.productNumber = productNumber;
    }

    public Long getId() {
        return id;
    }

    public Long getMemberId() {
        return memberId;
    }

    public Long getProductId() {
        return productId;
    }

    public int getProductNumber() {
        return productNumber;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setMemberId(Long memberId) {
        this.memberId = memberId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public void setProductNumber(int productNumber) {
        this.productNumber = productNumber;
    }
}
