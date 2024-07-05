package gift.controller;

import gift.model.dto.LoginUserDto;
import gift.model.dto.ProductResponseDto;
import gift.resolver.annotation.LoginUser;
import java.util.HashMap;
import java.util.Map;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WishController {

    @GetMapping("/wishes")
    public Map<ProductResponseDto, Long> getWishList(@LoginUser LoginUserDto loginUserDto){
        Map<ProductResponseDto, Long> wishlist = new HashMap<>();
        return wishlist;
    }


}
