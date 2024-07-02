package gift.controller.dto.request;

import gift.model.Product;

public record ProductRequest(
        String name,
        Integer price,
        String imgUrl
) {
    public Product toModel() {
        return new Product(null, name(), price(), imgUrl());
    }

    public Product toModel(final Long id) {
        return new Product(id, name(), price(), imgUrl());
    }
}
