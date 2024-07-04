package gift.model;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class Product {


    private long id;

    @NotNull(message = "Name is required")
    @Pattern(
            regexp = "^[a-zA-Z0-9ㄱ-ㅎㅏ-ㅣ가-힣\\(\\)\\[\\]\\+\\-\\&\\/\\_\\s]{1,15}$",
            message = "상품 이름은 공백을 포함하여 최대 15자까지 입력할 수 있으며, ( ), [ ], +, -, &, /, _ 이외의 특수 문자 사용 불가"
    )
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
