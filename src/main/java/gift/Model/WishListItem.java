package gift.Model;

public class WishListItem {
    private int userId;
    private int productId;
    private int count;
    private String productName;
    private int price;

    public WishListItem() {
    }

    public WishListItem(int userId, int productId, int count, String productName, int price) {
        this.userId = userId;
        this.productId = productId;
        this.count = count;
        this.productName = productName;
        this.price = price;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
