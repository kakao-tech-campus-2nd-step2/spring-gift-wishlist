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
        Optional<Member> memberOptional = memberRepository.findMemberByEmail(email);
        if (memberOptional.isEmpty()) {
            throw new IllegalArgumentException("존재하지 않는 이메일입니다.");
        }
        Member member = memberOptional.get();
        return wishListRepository.findByMemberId(member.getId());
    }

    public void addProductToWishList(String email, Long productId) {
        Optional<Member> memberOptional = memberRepository.findMemberByEmail(email);
        if (memberOptional.isEmpty()) {
            throw new IllegalArgumentException("존재하지 않는 이메일입니다.");
        }
        Member member = memberOptional.get();
        wishListRepository.addProductToWishList(member.getId(), productId);
    }

    public void removeProductFromWishList(String email, Long productId) {
        Optional<Member> memberOptional = memberRepository.findMemberByEmail(email);
        if (memberOptional.isEmpty()) {
            throw new IllegalArgumentException("존재하지 않는 이메일입니다.");
        }
        Member member = memberOptional.get();
        wishListRepository.removeProductFromWishList(member.getId(), productId);
    }
}
