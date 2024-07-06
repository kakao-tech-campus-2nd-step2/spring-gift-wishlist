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
}
