package gift.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import gift.service.WishListService;
import gift.dto.WishListDto;
import gift.dto.request.WishListRequest;
import gift.util.JwtUtil;
import jakarta.validation.Valid;

import java.util.List;

@RestController
@RequestMapping("/wishlist")
public class WishListController {
    
    private WishListService wishListService;

    public WishListController(WishListService wishListService, JwtUtil jwtUtil){
        this.wishListService = wishListService;
    }

    @GetMapping()
    public ResponseEntity<List<WishListDto>> getWishList(@RequestHeader("Authorization") String token){
        List<WishListDto> wishlist = wishListService.findWishListById(token);
        return new ResponseEntity<>(wishlist, HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<Void> addWishList(@RequestHeader("Authorization") String token, @Valid @RequestBody WishListRequest wishListRequest){
        wishListService.addWishList(token, wishListRequest.getProductId());
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping()
    public ResponseEntity<Void> deleteWishList(@RequestHeader("Authorization") String token, @Valid @RequestBody WishListRequest wishListRequest){
        wishListService.deleteWishList(token, wishListRequest.getProductId());
        return new ResponseEntity<>(HttpStatus.OK);
    }


}
