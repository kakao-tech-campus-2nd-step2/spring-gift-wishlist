package gift;

import java.util.ArrayList;
import java.util.List;

public class Product {
    private Long id;
    private String name;
    private Integer price;
    private String imageUrl;
    private List<Option> options;

    public Product() {
        this.options = new ArrayList<>();
    }


    public Product(Long id, String name, Integer price, String imageUrl, List<Option> options) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.imageUrl = imageUrl;
        this.options = options;
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

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
    public List<Option> getOptions() {
        return options;
    }

    public void setOptions(List<Option> options) {
        this.options = options;
    }
    public void addOption(Option option) {
        this.options.add(option);
    }

    public void removeOption(Option option) {
        this.options.remove(option);
    }


}