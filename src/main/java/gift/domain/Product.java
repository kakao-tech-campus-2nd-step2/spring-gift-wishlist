package gift.domain;

import java.net.URL;

public class Product extends Item {

    private Integer price;
    private URL imageUrl;

    public static class Builder extends Item.Builder<Builder> {

        private Integer price;
        private URL imageUrl;

        public Builder price(Integer price) {
            this.price = price;
            return this;
        }

        public Builder imageUrl(URL imageUrl) {
            this.imageUrl = imageUrl;
            return this;
        }

        @Override
        protected Builder self() {
            return this;
        }

        @Override
        public Product build() {
            return new Product(this);
        }
    }

    private Product(Builder builder) {
        super(builder);
        price = builder.price;
        imageUrl = builder.imageUrl;
    }

    public Integer getPrice() {
        return price;
    }

    public URL getImageUrl() {
        return imageUrl;
    }

}
