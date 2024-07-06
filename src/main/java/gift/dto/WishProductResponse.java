package gift.dto;

import gift.model.WishProduct;

public record WishProductResponse(
        Long id, ProductResponse product, Integer count) {
    public static WishProductResponse from(WishProduct wishProduct, ProductResponse product) {
        return new WishProductResponse(wishProduct.getId(), product, wishProduct.getCount());
    }
}
