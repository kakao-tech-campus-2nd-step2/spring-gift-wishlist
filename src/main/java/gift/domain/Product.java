package gift.domain;

import java.util.concurrent.atomic.AtomicLong;

public record Product(Long id, String name, double price, String imageUrl) {

    private final static AtomicLong index = new AtomicLong();


    public String getName() {
        return name;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public double getPrice() {
        return price;
    }

    public Long getId() {
        return id;
    }
}
