package gift.dto;

public class ProductRequestDto {
    private String name;
    private String imgUrl;
    private int price;

    public String getName() {
        return name;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public int getPrice() {
        return price;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
