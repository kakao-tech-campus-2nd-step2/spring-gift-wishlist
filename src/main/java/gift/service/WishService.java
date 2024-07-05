package gift.service;

import gift.dto.response.WishResponseDto;
import gift.exception.EntityNotFoundException;
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

    public List<WishResponseDto> findAllWish(String email){
        return wishRepository.findWishByMemberEmail(email).stream()
                .map(WishResponseDto::from)
                .collect(Collectors.toList());
    }

    public Long addWish(Long productId, String email, int count){
        return wishRepository.wishSave(productId, email, count);
    }

    public Long editWish(Long wishId, String email, int count){
        wishRepository.findById(wishId).orElseThrow(EntityNotFoundException::new);
        Integer findCount = wishRepository.findWishCountByWishIdAndMemberEmail(wishId, email);

        if(findCount == 0){
            throw new ForbiddenException();
        }


        return wishRepository.updateWish(wishId, count);
    }

    public Long deleteWish(Long wishId, String email){
        wishRepository.findById(wishId).orElseThrow(EntityNotFoundException::new);
        Integer findCount = wishRepository.findWishCountByWishIdAndMemberEmail(wishId, email);

        if(findCount == 0){
            throw new ForbiddenException();
        }

        wishRepository.deleteWish(wishId);

        return wishId;
    }
}
