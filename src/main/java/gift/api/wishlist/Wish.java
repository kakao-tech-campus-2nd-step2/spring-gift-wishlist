package gift.api.wishlist;

import gift.api.member.Member;
import gift.api.product.Product;

public class Wish {

    private Member member;
    private Product product;
    private Integer quantity;

    public Member getMember() {
        return member;
    }

    public Product getProduct() {
        return product;
    }

    public Integer getQuantity() {
        return quantity;
    }
}
