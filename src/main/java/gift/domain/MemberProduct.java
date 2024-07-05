package gift.domain;

/**
 * 회원(Member)와 상품(product)의 관계를 나타내는 엔티티
 */
public class MemberProduct extends BaseEntity {

    private final Long memberId;
    private final Long productId;

    public MemberProduct(Long id, Long memberId, Long productId) {
        super(id);
        this.memberId = memberId;
        this.productId = productId;
    }

    public MemberProduct(Builder builder) {
        super(builder);
        memberId = builder.memberId;
        productId = builder.productId;
    }

    public static class Builder extends BaseEntity.Builder<MemberProduct.Builder> {

        private Long memberId;
        private Long productId;

        public Builder memberId(Long memberId) {
            this.memberId = memberId;
            return this;
        }

        public Builder productId(Long productId) {
            this.productId = productId;
            return this;
        }

        @Override
        protected Builder self() {
            return this;
        }

        @Override
        public MemberProduct build() {
            return new MemberProduct(this);
        }
    }

    public Long getMemberId() {
        return memberId;
    }

    public Long getProductId() {
        return productId;
    }
}
