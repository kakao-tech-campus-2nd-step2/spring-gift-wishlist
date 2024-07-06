package gift.wishlist.controller;

import gift.user.exception.ForbiddenException;
import gift.user.model.dto.User;
import gift.user.resolver.LoginUser;
import gift.wishlist.model.WishListRepository;
import gift.wishlist.model.dto.WishListResponse;
import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/wishes")
public class WishListController {
    private final WishListRepository wishListRepository;

    public WishListController(WishListRepository wishListRepository) {
        this.wishListRepository = wishListRepository;
    }

    @GetMapping("")
    public List<WishListResponse> getWishListForUser(@LoginUser User loginUser) {
        List<WishListResponse> wishList = wishListRepository.findWishesByUserId(loginUser.id());
        if (wishList.isEmpty()) {
            throw new IllegalArgumentException("해당 사용자의 위시리스트가 존재하지 않습니다.");
        }
        return wishList;
    }

    @GetMapping("/admin/{userId}")
    public List<WishListResponse> getWishListForAdmin(@LoginUser User loginUser, @PathVariable("userId") Long userId) {
        if (!loginUser.role().equals("ADMIN")) {
            throw new ForbiddenException("해당 요청에 대한 관리자 권한이 없습니다.");
        }
        List<WishListResponse> wishList = wishListRepository.findWishesByUserId(userId);
        if (wishList.isEmpty()) {
            throw new IllegalArgumentException("해당 사용자의 위시리스트가 존재하지 않습니다.");
        }
        return wishList;
    }
}
