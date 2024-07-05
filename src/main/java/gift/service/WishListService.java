package gift.service;

import gift.dto.ProductAmount;
import gift.repository.WishListRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WishListService {

    private final WishListRepository wishListRepository;

    public WishListService(WishListRepository wIshListRepository) {
        this.wishListRepository = wIshListRepository;
    }

    public void addProductToWishList(Long memberId, Long productId) {
        wishListRepository.addWishProduct(memberId, productId);
    }

    public List<ProductAmount> getProductIdsAndAmount(Long memberId) {
        return wishListRepository.getWishListProductIdsByMemberId(memberId);
    }

    public Long deleteProductInWishList(Long memberId, Long productId) {
        return wishListRepository.deleteProduct(memberId, productId);
    }

}
