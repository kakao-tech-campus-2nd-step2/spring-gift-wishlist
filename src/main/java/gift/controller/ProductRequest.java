package gift.controller;

import gift.domain.Product;

public class ProductRequest {

    private String name;
    private int price;
    private String imageUrl;

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public Product toEntity() {
        return new Product(this.name, this.price, this.imageUrl);
    }

    public ProductRequest() {
    }

    public ProductRequest(String name, int price, String imageUrl) {
        this.name = name;
        this.price = price;
        this.imageUrl = imageUrl;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ProductRequest)) {
            return false;
        }

        ProductRequest that = (ProductRequest) o;

        if (price != that.price) {
            return false;
        }
        if (name != null ? !name.equals(that.name) : that.name != null) {
            return false;
        }
        return imageUrl != null ? imageUrl.equals(that.imageUrl) : that.imageUrl == null;
    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + price;
        result = 31 * result + (imageUrl != null ? imageUrl.hashCode() : 0);
        return result;
    }

}
