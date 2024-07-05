package gift.controller;

import gift.dto.Wishlist;
import gift.dto.Wishlist.Response;
import gift.repository.UserRepository;
import gift.service.WishlistService;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@AllArgsConstructor
public class WishlistController {

    private final WishlistService wishlistService;

    @GetMapping("/api/wishlist")
    @ResponseBody
    public List<Wishlist.Response> getAllWishlistItems(@RequestHeader("Authorization") String accessToken){
        long userId = wishlistService.findUserId(accessToken);

        List<Response> responseList = wishlistService.getAllWishlistItems(userId);
        return responseList;
    }
}
