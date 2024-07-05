package gift.service;

import static gift.util.Constants.PRODUCT_NOT_FOUND;
import static gift.util.Constants.WISH_ALREADY_EXISTS;
import static gift.util.Constants.WISH_NOT_FOUND;

import gift.dto.wish.WishRequestDTO;
import gift.dto.wish.WishResponseDTO;
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

    public List<WishResponseDTO> getWishlistByMemberId(Long memberId) {
        return wishRepository.findAllByMemberId(memberId).stream()
            .map(WishService::convertToDTO)
            .collect(Collectors.toList());
    }

    public WishResponseDTO addWish(WishRequestDTO wishRequestDTO) {
        if (!productRepository.existsById(wishRequestDTO.productId())) {
            throw new ProductNotFoundException(PRODUCT_NOT_FOUND + wishRequestDTO.productId());
        }

        if (wishRepository.existsByMemberIdAndProductId(wishRequestDTO.memberId(), wishRequestDTO.productId())) {
            throw new DuplicateWishException(WISH_ALREADY_EXISTS);
        }

        Wish wish = convertToEntity(wishRequestDTO);
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
    private static Wish convertToEntity(WishRequestDTO wishRequestDTO) {
        return new Wish(null, wishRequestDTO.memberId(), wishRequestDTO.productId());
    }

    private static WishResponseDTO convertToDTO(Wish wish) {
        return new WishResponseDTO(wish.getId(), wish.getMemberId(), wish.getProductId());
    }
}
