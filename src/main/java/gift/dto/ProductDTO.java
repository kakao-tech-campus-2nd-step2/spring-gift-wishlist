package gift.dto;

import gift.entity.Product;

public class ProductDTO {
    private long id;
    private String name,url;
    private int price;


    public ProductDTO(String name, int price, String url) {
        this.name=name;
        this.price=price;
        this.url=url;
    }

    public ProductDTO(long id, String name, int price, String url) {
        this.id = id;
        this.name=name;
        this.price=price;
        this.url=url;
    }

    public long getId() { return id; }
    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }

    public String getUrl() {
        return url;
    }

    public static ProductDTO fromEntity(Product product) {
        return new ProductDTO(product.getName(), product.getPrice(), product.getUrl());
    }

    public void setId(long id) {this.id= id;}
    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
