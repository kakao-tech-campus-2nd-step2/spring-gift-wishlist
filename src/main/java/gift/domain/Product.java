package gift.domain;

import java.util.concurrent.atomic.AtomicLong;

public record Product(Long id, String name, double price, String imageUrl) {

    private static final AtomicLong index = new AtomicLong(3);

    public Product(String name, double price, String imageUrl) {
        this(index.incrementAndGet(), name, price, imageUrl);
    }


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
