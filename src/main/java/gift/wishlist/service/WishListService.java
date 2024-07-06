package gift.wishlist.service;

import gift.wishlist.dto.WishListReqDto;
import gift.wishlist.dto.WishListResDto;
import gift.wishlist.exception.WishListNotFoundException;
import gift.wishlist.repository.WishListRepository;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class WishListService {

    private final WishListRepository wishListRepository;

    public WishListService(WishListRepository wishListRepository) {
        this.wishListRepository = wishListRepository;
    }

    public List<WishListResDto> getWishListsByMemberId(Long id) {

        return wishListRepository.findWishListsByMemberId(id).stream()
                .map(WishListResDto::new)
                .toList();
    }

    public void addWishList(Long memberId, WishListReqDto wishListReqDto) {
        // 이미 위시 리스트에 있는 상품이면 수량을 더한다.
        if (wishListRepository.isWishListExistByMemberIdAndProductId(memberId, wishListReqDto.productId())) {
            wishListRepository.updateWishListByMemberIdAndProductId(memberId, wishListReqDto.productId(), wishListReqDto.quantity());
            return;
        }
        wishListRepository.addWishList(memberId, wishListReqDto.productId(), wishListReqDto.quantity());
    }

    public void updateWishListById(Long wishListId, WishListReqDto wishListReqDto) {
        validateWishListExistsById(wishListId);

        // 수량이 0이면 삭제
        Integer quantity = wishListReqDto.quantity();
        if (quantity == 0) {
            wishListRepository.deleteWishListById(wishListId);
        } else {
            wishListRepository.updateWishListById(wishListId, quantity);
        }
    }

    public void deleteWishListById(Long wishListId) {
        validateWishListExistsById(wishListId);
        wishListRepository.deleteWishListById(wishListId);
    }

    private void validateWishListExistsById(Long wishListId) {
        if (!wishListRepository.isWishListExistById(wishListId)) {
            throw WishListNotFoundException.EXCEPTION;
        }
    }
}
