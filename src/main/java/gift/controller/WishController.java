package gift.controller;

import gift.model.dto.LoginUserDto;
import gift.model.dto.ProductResponseDto;
import gift.model.dto.WishRequestDto;
import gift.model.dto.WishResponseDto;
import gift.repository.WishDao;
import gift.resolver.annotation.LoginUser;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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
    public List<WishResponseDto> getWishList(@LoginUser LoginUserDto loginUserDto) {
        List<WishResponseDto> wishlist = new ArrayList<>();
        return wishlist;
    }

    @PostMapping("/wishes")
    public void addProductToWishList(@RequestBody WishRequestDto wishRequestDto,
        @LoginUser LoginUserDto loginUserDto) {
        //wishDao.
    }

    @PutMapping("/wishes")
    public void updateProductInWishList(@RequestBody WishRequestDto wishRequestDto,
        @LoginUser LoginUserDto loginUserDto) {
        //wishDao.
    }

    @DeleteMapping("/wishes")
    public void deleteProductInWishList(
        @LoginUser LoginUserDto loginUserDto) {
        //wishDao.
    }
}
