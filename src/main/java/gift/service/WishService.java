package gift.service;

import static gift.util.JwtUtil.generateJwtToken;

import gift.domain.Member;
import gift.domain.Wish;
import gift.dto.MemberDto;
import gift.dto.WishDto;
import gift.exception.ForbiddenException;
import gift.repository.MemberDao;
import gift.repository.WishDao;
import org.springframework.stereotype.Service;

@Service
public class WishService {
    private final WishDao wishDao;

    public WishService(WishDao wishDao) {
        this.wishDao = wishDao;
    }

    public void addWish(Long memberId, WishDto wishDto) {
        Wish newWish = new Wish(memberId, wishDto.getProductId());
        wishDao.addWish(newWish);
    }

    public void deleteWishbyId(Long wishId){
        wishDao.deleteWishById(wishId);
    }

}
