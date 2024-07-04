package gift.product.domain;

import gift.product.application.command.ProductCreateCommand;
import gift.product.application.command.ProductUpdateCommand;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

public class ProductMemoryRepository implements ProductRepository {
    private final List<Product> products = new ArrayList<>();
    private final AtomicLong nextId = new AtomicLong(1);

    public List<Product> findAll() {
        return products;
    }

    public Optional<Product> findById(Long productId) {
        return products.stream()
                .filter(product -> product.getId().equals(productId))
                .findFirst();
    }

    public void addProduct(ProductCreateCommand product) {
        products.add(
                new Product(
                        nextId.getAndIncrement(),
                        product.name(),
                        product.price(),
                        product.imageUrl()
                )
        );
    }

    public void deleteProduct(Long productId) {
        products.removeIf(product -> product.getId().equals(productId));
    }

    @Override
    public void updateProduct(ProductUpdateCommand command) {
        products.stream()
                .filter(product -> product.getId().equals(command.productId()))
                .findFirst()
                .ifPresent(product -> {
                    product.update(
                            command.name(),
                            command.price(),
                            command.imageUrl()
                    );
                });
    }
}
