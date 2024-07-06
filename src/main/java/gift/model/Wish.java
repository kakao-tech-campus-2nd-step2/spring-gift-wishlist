package gift.model;

public class Wish {

    private Long userId;
    private String productName;
    private Integer count;

    public Wish(String productName, Integer count) {
        this.productName = productName;
        this.count = count;
    }

    public Wish(Long userId, String productName, Integer count) {
        this.userId = userId;
        this.productName = productName;
        this.count = count;
    }

    public Long getUserId() {
        return userId;
    }

    public String getProductName() {
        return productName;
    }

    public Integer getCount() {
        return count;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
