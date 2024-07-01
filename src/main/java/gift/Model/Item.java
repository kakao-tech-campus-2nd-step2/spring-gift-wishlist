package gift.Model;

import java.util.Objects;

public class Item {

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(Long price) {
        this.price = price;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    private Long id;
    private String name;
    private Long price;
    private String imgUrl;
    public Item(){}

    public Item(Long id, String name, Long price, String imgUrl) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.imgUrl = imgUrl;
    }
    public Long getId(){return id; }

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
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Item item = (Item) o;
        return Objects.equals(id, item.id) && Objects.equals(name, item.name)
            && Objects.equals(price, item.price) && Objects.equals(imgUrl,
            item.imgUrl);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, price, imgUrl);
    }
}
