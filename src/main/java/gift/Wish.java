package gift;

public class Wish {
    private Member member;
    private Product product;
    private int Quantity;

    public Member getMember() {
        return member;
    }
    public void setMember(Member member) {
        this.member = member;
    }
    public Product getProduct() {
        return product;
    }
    public void setProduct(Product product) {
        this.product = product;
    }
    public int getQuantity() {
        return Quantity;
    }
}
