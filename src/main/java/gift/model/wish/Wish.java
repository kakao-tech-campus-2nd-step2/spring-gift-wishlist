package gift.model.wish;

public class Wish {
    private Long productId;
    private String memberEmail;
    private String productName;
    private int amount;

    public Wish(Long productId, String memberEmail, String productName,int amount){
        this.productId = productId;
        this.memberEmail = memberEmail;
        this.productName = productName;
        this.amount = amount;
    }

    public Long getProductId(){
        return productId;
    }

    public String getMemberEmail(){
        return memberEmail;
    }

    public String getProductName(){
        return productName;
    }

    public int getAmount(){
        return amount;
    }
}
