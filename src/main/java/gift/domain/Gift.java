package gift.domain;

public class Gift {
    Long id;
    String name;
    String description;
    Integer price;
    String url;

    public Gift(String name, String description, Integer price, String url) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.url = url;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
