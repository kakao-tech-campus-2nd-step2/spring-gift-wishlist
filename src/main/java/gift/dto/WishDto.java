package gift.dto;

public class WishDto {
    private Long productId;
    private String memberEmail;
    private int amount;

    public WishDto(Long productId, String memberEmail, int amount){
        this.productId = productId;
        this.memberEmail = memberEmail;
        this.amount = amount;
    }

    public Long getProductId(){
        return productId;
    }

    public String getMemberEmail(){
        return memberEmail;
    }

    public int getAmount(){
        return amount;
    }
}
