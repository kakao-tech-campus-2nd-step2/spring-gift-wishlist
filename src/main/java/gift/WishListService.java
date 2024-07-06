package gift;

import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class WishListService {
    private final WishListRepository wishListRepository;
    private final MemberRepository memberRepository;

    public WishListService(WishListRepository wishListRepository, MemberRepository memberRepository) {
        this.wishListRepository = wishListRepository;
        this.memberRepository = memberRepository;
    }

    public List<WishList> getWishList(String email) {
        Member member = memberRepository.findMemberByEmail(email);
        return wishListRepository.findByMemberId(member.getId());
    }

    public void addProductToWishList(String email, Long productId) {
        Member member = memberRepository.findMemberByEmail(email);
        wishListRepository.addProductToWishList(member.getId(), productId);
    }

    public void removeProductFromWishList(String email, Long productId) {
        Member member = memberRepository.findMemberByEmail(email);
        wishListRepository.removeProductFromWishList(member.getId(), productId);
    }
}
