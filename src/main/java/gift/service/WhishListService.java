package gift.service;

import gift.model.WishList;
import gift.model.WishListDAO;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;

/**
 * WhishListService 클래스는 WishList 관련 비즈니스 로직을 처리하는 서비스 클래스입니다
 */
@Service
public class WhishListService {

    private final WishListDAO wishListDAO;

    /**
     * WhishListService 생성자
     *
     * @param wishListDAO WishListDAO 객체
     */
    public WhishListService(WishListDAO wishListDAO) {
        this.wishListDAO = wishListDAO;
    }

    /**
     * 새로운 WishList를 생성함
     *
     * @param productId WishList에 추가할 상품의 ID
     * @param userId    WishList에 추가할 사용자의 ID
     * @return 생성된 WishList 객체의 ID 리스트
     */
    public List<Long> createWishList(long productId, long userId) {
        WishList newWishList = wishListDAO.createWishList(productId, userId);
        return Collections.singletonList(newWishList.getId());
    }

    /**
     * 지정된 사용자의 모든 WishList를 조회함
     *
     * @param userId 조회할 사용자의 ID
     * @return 지정된 사용자의 모든 WishList 객체의 productId 리스트
     */
    public List<Long> getWishListsByUserId(long userId) {
        List<WishList> wishLists = wishListDAO.getWishListsByUserId(userId);
        return wishLists.stream()
                        .map(WishList::getProductId)
                        .collect(Collectors.toList());
    }

    /**
     * 지정된 사용자의 모든 WishList를 삭제함
     *
     * @param userId 삭제할 사용자의 ID
     * @return 삭제 성공 여부
     */
    public boolean deleteWishListsByUserId(long userId) {
        return wishListDAO.deleteWishListsByUserId(userId);
    }

    /**
     * 지정된 사용자가 지정된 상품을 위시리스트에서 삭제함
     *
     * @param userId    삭제할 사용자의 ID
     * @param productId 삭제할 상품의 ID
     * @return 삭제 성공 여부
     */
    public boolean deleteWishListByUserIdAndProductId(long userId, long productId) {
        return wishListDAO.deleteWishListByUserIdAndProductId(userId, productId);
    }

    /**
     * 지정된 사용자가 지정된 상품을 위시리스트에 추가함
     *
     * @param userId    추가할 사용자의 ID
     * @param productId 추가할 상품의 ID
     * @return 추가 성공 여부
     */
    public boolean addWishListByUserIdAndProductId(long userId, long productId) {
        return wishListDAO.addWishListByUserIdAndProductId(userId, productId);
    }
}
