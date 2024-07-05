package gift.service;

import gift.dto.wish.WishRequestDTO;
import gift.dto.wish.WishResponseDTO;
import gift.model.Wish;
import gift.repository.WishRepository;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class WishService {
    private final WishRepository wishRepository;

    public WishService(WishRepository wishRepository) {
        this.wishRepository = wishRepository;
    }

    public List<WishResponseDTO> getWishlistByMemberId(Long memberId) {
        return wishRepository.findAllByMemberId(memberId).stream()
            .map(WishService::convertToDTO)
            .collect(Collectors.toList());
    }

    public WishResponseDTO addWish(WishRequestDTO wishRequestDTO) {
        Wish wish = convertToEntity(wishRequestDTO);
        Wish savedWish = wishRepository.create(wish);
        return convertToDTO(savedWish);
    }

    // Mapper methods
    private static Wish convertToEntity(WishRequestDTO wishRequestDTO) {
        return new Wish(null, wishRequestDTO.memberId(), wishRequestDTO.productId());
    }

    private static WishResponseDTO convertToDTO(Wish wish) {
        return new WishResponseDTO(wish.getId(), wish.getMemberId(), wish.getProductId());
    }
}
