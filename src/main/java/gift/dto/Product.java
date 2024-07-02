package gift.dto;


import gift.validation.NoKakao;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class Product {

    private Long id;
    @Size(max = 15, message = "상품 이름은 공백을 포함하여 최대 15자까지 입력할 수 있습니다.")
    @Pattern(regexp = "^[a-zA-Z0-9가-힣 ()\\[\\]+\\-&/_]*$", message = "(),[],+,-,&,/,_를 제외한 특수 문자 사용은 불가능합니다.")
    @NoKakao
    private String name;
    private int price;
    private String img;

    public Product() {
    }

    public Product(Long id, String name, int price, String img) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.img = img;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

}
