package gift.service;

import gift.domain.member.MemberRepository;
import gift.domain.product.ProductRepository;
import gift.domain.wish.WishRepository;
import gift.dto.WishAddRequestDto;
import gift.dto.WishResponseDto;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class WishService {
    private final WishRepository wishRepository;
    private final MemberRepository memberRepository;
    private final ProductRepository productRepository;

    public WishService(WishRepository wishRepository, MemberRepository memberRepository, ProductRepository productRepository) {
        this.wishRepository = wishRepository;
        this.memberRepository = memberRepository;
        this.productRepository = productRepository;
    }

    public void addWish(Long memberId, WishAddRequestDto request) {
        if (productRepository.isNotValidProductId(request.getProductId())) {
            throw new IllegalArgumentException("유효하지 않은 상품 정보입니다.");
        }
        wishRepository.addWish(memberId, request);
    }

    public List<WishResponseDto> getAllWishes(Long id) {
        if (memberRepository.isNotExistMemberById(id)) {
            throw new IllegalArgumentException("존재하지 않는 유저 정보입니다.");
        }
        return wishRepository.getAllWishes(id)
                .stream()
                .map(WishResponseDto::new)
                .toList();
    }

    public void deleteWish(Long memberId, Long productId) {
        if (memberRepository.isNotExistMemberById(memberId)) {
            throw new IllegalArgumentException("존재하지 않는 유저 정보입니다.");
        }
        if (productRepository.isNotValidProductId(productId)) {
            throw new IllegalArgumentException("유효하지 않은 상품 정보입니다.");
        }
        wishRepository.deleteWish(memberId,productId);
    }
}
