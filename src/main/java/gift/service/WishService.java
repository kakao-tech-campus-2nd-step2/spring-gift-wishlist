package gift.service;

import static gift.util.Constants.PRODUCT_NOT_FOUND;
import static gift.util.Constants.WISH_ALREADY_EXISTS;
import static gift.util.Constants.WISH_NOT_FOUND;

import gift.dto.wish.WishRequest;
import gift.dto.wish.WishResponse;
import gift.exception.product.ProductNotFoundException;
import gift.exception.wish.DuplicateWishException;
import gift.exception.wish.WishNotFoundException;
import gift.model.Wish;
import gift.repository.ProductRepository;
import gift.repository.WishRepository;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class WishService {
    private final WishRepository wishRepository;
    private final ProductRepository productRepository;

    public WishService(WishRepository wishRepository, ProductRepository productRepository) {
        this.wishRepository = wishRepository;
        this.productRepository = productRepository;
    }

    public List<WishResponse> getWishlistByMemberId(Long memberId) {
        return wishRepository.findAllByMemberId(memberId).stream()
            .map(WishService::convertToDTO)
            .collect(Collectors.toList());
    }

    public WishResponse addWish(WishRequest wishRequest) {
        if (!productRepository.existsById(wishRequest.productId())) {
            throw new ProductNotFoundException(PRODUCT_NOT_FOUND + wishRequest.productId());
        }

        if (wishRepository.existsByMemberIdAndProductId(wishRequest.memberId(), wishRequest.productId())) {
            throw new DuplicateWishException(WISH_ALREADY_EXISTS);
        }

        Wish wish = convertToEntity(wishRequest);
        Wish savedWish = wishRepository.create(wish);
        return convertToDTO(savedWish);
    }

    public void deleteWish(Long id) {
        if (wishRepository.findById(id).isEmpty()) {
            throw new WishNotFoundException(WISH_NOT_FOUND + id);
        }
        wishRepository.deleteById(id);
    }

    // Mapper methods
    private static Wish convertToEntity(WishRequest wishRequest) {
        return new Wish(null, wishRequest.memberId(), wishRequest.productId());
    }

    private static WishResponse convertToDTO(Wish wish) {
        return new WishResponse(wish.getId(), wish.getMemberId(), wish.getProductId());
    }
}
