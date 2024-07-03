package gift.controller;

import gift.domain.Product;

public class ProductRequest {
    private String name;
    private long price;
    private String imageUrl;

    public ProductRequest(String name, long price, String imageUrl) {
        this.name = name;
        this.price = price;
        this.imageUrl = imageUrl;
    }

    public ProductRequest() {
    }

    public String getName(){
        return name;
    }
    public void setName(String name){
        this.name = name;
    }
    public long getPrice(){
        return price;
    }
    public void setPrice(long price){
        this.price = price;
    }
    public String getImageUrl(){
        return imageUrl;
    }
    public void setImageUrl(String imageUrl){
        this.imageUrl = imageUrl;
    }

    public static ProductRequest entityToRequest(Product product){
        return new ProductRequest(product.getName(), product.getPrice(), product.getImageUrl());
    }
}
