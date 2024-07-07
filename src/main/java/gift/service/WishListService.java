package gift.service;

import gift.entity.Product;
import gift.entity.WishList;
import gift.repository.WishListRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WishListService {

    @Autowired
    private WishListRepository wishListRepository;

    // id로 위시리스트 찾기
    public WishList findByUserId(Long userId) {
        return wishListRepository.findByUserId(userId);
    }

    // 위시리스트에 상품 추가
    public void addProductToWishList(Long userId, Product product) {
        wishListRepository.addProductToWishList(userId, product);
    }

    // 위시리스트에 상품 삭제
    public void removeProductFromWishList(Long userId, Long productId) {
        wishListRepository.removeProductFromWishList(userId, productId);
    }
}