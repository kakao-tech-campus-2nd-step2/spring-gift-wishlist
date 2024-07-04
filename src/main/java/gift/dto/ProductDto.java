package gift.dto;

// 상품의 정보를 담는 클래스
// DTO와 Entity를 분리. 모델은 자주 수정될 수 있지만, DB와 매칭되는 모델은 자주 수정하면 안되므로 이를 DTO와 Entity로 분리
public class ProductDto {
    private long id;
    private String name;
    private int price;
    private String image;

    // 명시적으로 작성한 기본 생성자
    public ProductDto() {
        id = 0;
        name = null;
        price = 0;
        image = null;
    }

    public ProductDto(long id, String name, int price, String image) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.image = image;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
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

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
