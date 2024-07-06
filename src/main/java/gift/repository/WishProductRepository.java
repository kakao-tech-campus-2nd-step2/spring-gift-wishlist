package gift.repository;

import gift.model.WishProduct;

import java.util.List;

public interface WishProductRepository {
    WishProduct save(WishProduct wishProduct);

    void update(WishProduct wishProduct);

    boolean existsByProductAndMember(Long productId, Long memberId);

    WishProduct findById(Long id);

    List<WishProduct> findAll();

    void deleteById(Long id);
}