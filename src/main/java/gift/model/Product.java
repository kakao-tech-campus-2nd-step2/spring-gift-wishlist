package gift.model;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class Product {


    private long id;

    @NotNull(message = "Name is required")
    @Size(max= 15, message = "Name must be at most 15 characters")
//    @Pattern(
//            regexp = "[a-zA-z0-9ㄱ-ㅎㅏ-ㅣ가-힣()\\\\[\\\\]+\\\\-&/_\\\\s]+",
//            message = "Name contains invalid characters"
//    )
    private String name;
    private int price;
    private String imageUrl;

    public Product(){}

    public Product(long id, String name, int price, String imageUrl) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.imageUrl = imageUrl;
    }

    public long getId(){
        return id;
    }

    public void setId(long id){
        this.id = id;
    }

    public String getName(){
        return name;
    }

    public void setName(String name){
        this.name = name;
    }

    public int getPrice(){
        return price;
    }

    public void setPrice(int price){
        this.price = price;
    }

    public String getImageUrl(){
        return imageUrl;
    }

    public void setImageUrl(String imageUrl){
        this.imageUrl = imageUrl;
    }
}
