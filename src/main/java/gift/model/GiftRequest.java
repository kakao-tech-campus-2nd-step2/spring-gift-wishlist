package gift.model;

public class GiftRequest {

    private String name;
    private int price;
    private String imageUrl;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
    public Gift toEntity(){
        return new Gift(null,name,price,imageUrl);
    }

    public Gift toEntity(Long id){
        return new Gift(id,name,price,imageUrl);
    }

}
