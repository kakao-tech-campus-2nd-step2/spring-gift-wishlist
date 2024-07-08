package gift.Controller;

import gift.Model.Product;
import gift.Model.RequestWishListDTO;
import gift.Model.User;
import gift.Service.WishListService;
import gift.annotation.ValidUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/api")
public class WishListController {
    private final WishListService wishListService;

    @Autowired
    public WishListController (WishListService wishListService){
        this.wishListService = wishListService;
    }

    @PostMapping("/wishList/add")
    public void addWishList(@ValidUser User user, @RequestBody RequestWishListDTO requestWishListDTO){
        wishListService.addWishList(user, requestWishListDTO);
    }
}
