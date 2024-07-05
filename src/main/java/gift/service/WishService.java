package gift.service;

import gift.dto.response.WishResponseDto;
import gift.exception.ForbiddenException;
import gift.repository.wish.WishRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class WishService {

    private final WishRepository wishRepository;

    public WishService(WishRepository wishRepository) {
        this.wishRepository = wishRepository;
    }

    public List<WishResponseDto> findAllLikesProducts(String email){
        return wishRepository.findWishByMemberId(email).stream()
                .map(WishResponseDto::from)
                .collect(Collectors.toList());
    }

    public Long addLikesProduct(Long productId, String email, int count){
        return wishRepository.wishSave(productId, email, count);
    }

    public Long editLikes(Long wishId, String email, int count){
        Integer findCount = wishRepository.findWishCountByWishIdAndMemberId(wishId, email);

        if(findCount == 0){
            throw new ForbiddenException();
        }


        return wishRepository.updateWish(wishId, count);
    }

    public Long deleteLikes(Long wishId, String email){
        Integer findCount = wishRepository.findWishCountByWishIdAndMemberId(wishId, email);

        if(findCount == 0){
            throw new ForbiddenException();
        }

        wishRepository.deleteWish(wishId);

        return wishId;
    }
}
