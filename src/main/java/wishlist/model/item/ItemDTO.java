package wishlist.model.item;

import java.util.Objects;

public class ItemDTO {

    private Long id;
    private String name;
    private Long price;
    private String imgUrl;

    public ItemDTO() {
    }

    public ItemDTO(Long id, String name, Long price, String imgUrl) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.imgUrl = imgUrl;
    }

    public ItemDTO(String name, Long price, String imgUrl) {
        this.name = name;
        this.price = price;
        this.imgUrl = imgUrl;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Long getPrice() {
        return price;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, price, imgUrl);
    }
}