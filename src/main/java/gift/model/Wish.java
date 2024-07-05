package gift.model;

public class Wish {

    private long id;

    private Member member;

    private Product product;

    public Wish() {}

    public Wish(long id, Member member, Product product) {
        this.id = id;
        this.member = member;
        this.product = product;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

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

}
