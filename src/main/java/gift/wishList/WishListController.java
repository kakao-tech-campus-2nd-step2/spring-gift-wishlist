package gift.wishList;

import gift.annotation.LoginUser;
import gift.user.User;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/wishes")
public class WishListController {

    private final WishListRepository wishListRepository;

    public WishListController(WishListRepository wishListRepository) {
        this.wishListRepository = wishListRepository;
    }

    //생성
    @PostMapping("/add")
    public ResponseEntity<?> addWishes(@LoginUser User user, @RequestBody WishListDTO wishListDTO){
        wishListRepository.insertWishList(user.getId(), wishListDTO);
        return ResponseEntity.ok(null);
    }

    //조회(userid)
    @GetMapping("")
    public ResponseEntity<?> getWishesByUserID(@LoginUser User user){
        System.out.println(user.getId());
        List<WishList> wishLists = wishListRepository.findWishListsByUserID(user.getId());
        return ResponseEntity.ok(wishLists);
    }

}
