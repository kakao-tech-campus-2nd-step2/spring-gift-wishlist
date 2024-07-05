package gift.service;

import gift.dto.wish.WishRequestDTO;
import gift.dto.wish.WishResponseDTO;
import gift.model.Wish;
import gift.repository.WishRepository;
import org.springframework.stereotype.Service;

@Service
public class WishService {
    private final WishRepository wishRepository;

    public WishService(WishRepository wishRepository) {
        this.wishRepository = wishRepository;
    }

    // Mapper methods
    private static Wish convertToEntity(WishRequestDTO wishRequestDTO) {
        return new Wish(null, wishRequestDTO.memberId(), wishRequestDTO.productId());
    }

    private static WishResponseDTO convertToDTO(Wish wish) {
        return new WishResponseDTO(wish.getId(), wish.getMemberId(), wish.getProductId());
    }
}
