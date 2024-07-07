package gift.model;

import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class GiftRequest {

    @Size(max = 15)
    @Pattern(regexp = "[\\s\\(\\)\\[\\]\\+\\-&/_a-zA-Z0-9\uAC00-\uD7AF]*", message = "특수문자 오류")
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
