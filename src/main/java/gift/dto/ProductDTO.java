package gift.dto;

import gift.model.Product;

public class ProductDTO {

    private  Long id;
    private  String name;
    private  Integer price;
    private  String imageUrl;

    //타임리프 사용을 위한 기본 생성자
    public ProductDTO() {}

    public ProductDTO(Long id, String name, Integer price, String imageUrl) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.imageUrl = imageUrl;
    }

    //상품 모델을 DTO로 빠르게 전환
    public ProductDTO(Product product) {
        this.id = product.getId();
        this.name = product.getName();
        this.price = product.getPrice();
        this.imageUrl = product.getImageUrl();
    }

    //디버깅 필요 시 체크용 toString
    @Override
    public String toString() {
        return "ProductDTO{" +
            "id=" + id +
            ", name='" + name + '\'' +
            ", price=" + price +
            ", imageUrl='" + imageUrl + '\'' +
            '}';
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Integer getPrice() {
        return price;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
