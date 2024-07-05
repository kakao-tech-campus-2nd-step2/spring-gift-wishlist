package gift.controller;

import gift.model.dto.LoginUserDto;
import gift.model.dto.ProductResponseDto;
import gift.model.dto.WishRequestDto;
import gift.repository.WishDao;
import gift.resolver.annotation.LoginUser;
import java.util.HashMap;
import java.util.Map;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WishController {

    private final WishDao wishDao;

    public WishController(WishDao wishDao) {
        this.wishDao = wishDao;
    }

    @GetMapping("/wishes")
    public Map<ProductResponseDto, Long> getWishList(@LoginUser LoginUserDto loginUserDto) {
        Map<ProductResponseDto, Long> wishlist = new HashMap<>();
        return wishlist;
    }

    @PostMapping("/wishes")
    public void addProductToWishList(@RequestBody WishRequestDto wishRequestDto,
        @LoginUser LoginUserDto loginUserDto) {
        //wishDao.
    }

    @PutMapping("/wishes/{id}")
    public void updateProductInWishList(@RequestBody WishRequestDto wishRequestDto,
        @PathVariable("id") Long id,
        @LoginUser LoginUserDto loginUserDto) {
        //wishDao.
    }

    @DeleteMapping("wishes/{id}")
    public void deleteProductInWishList(@PathVariable("id") Long id,
        @LoginUser LoginUserDto loginUserDto) {
        //wishDao.
    }
}
