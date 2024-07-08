package gift.service.wish;

import gift.domain.wish.Wish;
import gift.domain.wish.WishRepository;
import gift.web.dto.WishDto;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class WishService {
    private final WishRepository wishRepository;

    public WishService(WishRepository wishRepository) {
        this.wishRepository = wishRepository;
    }

    public List<WishDto> findAllWishes(String email) {
        return List.copyOf(wishRepository.selectAllWishes(email)
                            .stream()
                            .map(WishDto::from)
                            .toList()
                            );
    }

    public WishDto insertWish(String email, WishDto wishDto) {
        return WishDto.from(wishRepository.insertWish(WishDto.toEntity(wishDto, email)));
    }

    public WishDto updateWish(String email, WishDto wishDto) {
        wishRepository.getWishBySignature(email, wishDto.productId());
        Wish newWish = WishDto.toEntity(wishDto, email);
        wishRepository.updateWish(newWish);
        return WishDto.from(newWish);
    }
}
