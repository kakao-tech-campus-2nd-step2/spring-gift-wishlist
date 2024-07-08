package gift;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class WishListService {
    private final WishListRepository wishListRepository;
    private final MemberRepository memberRepository;

    public WishListService(WishListRepository wishListRepository, MemberRepository memberRepository) {
        this.wishListRepository = wishListRepository;
        this.memberRepository = memberRepository;
    }

    public List<WishList> getWishList(String email) {
        Member member = memberRepository.findMemberByEmail(email)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 이메일입니다."));
        return wishListRepository.findByMemberId(member.getId());
    }

    public void addProductToWishList(String email, Long productId) {
        Member member = memberRepository.findMemberByEmail(email)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 이메일입니다."));
        wishListRepository.addProductToWishList(member.getId(), productId);
    }

    public void removeProductFromWishList(String email, Long productId) {
        Member member = memberRepository.findMemberByEmail(email)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 이메일입니다."));
        wishListRepository.removeProductFromWishList(member.getId(), productId);
    }
}
