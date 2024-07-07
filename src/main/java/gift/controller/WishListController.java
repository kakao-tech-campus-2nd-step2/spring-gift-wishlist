package gift.controller;

import gift.dto.WishListItemRequest;
import gift.dto.WishListItemResponse;
import gift.model.Member;
import gift.model.annotation.LoginMember;
import gift.service.WishListService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/wishlist")
public class WishListController {

    private final WishListService wishListService;

    public WishListController(WishListService wishListService) {
        this.wishListService = wishListService;
    }

    @PostMapping("/items")
    public ResponseEntity<WishListItemResponse> addItem(@RequestBody WishListItemRequest request,
        @LoginMember Member member) {
        WishListItemResponse response = wishListService.addItemToWishList(request, member);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/items")
    public ResponseEntity<List<WishListItemResponse>> getWishList(@LoginMember Member member) {
        List<WishListItemResponse> wishList = wishListService.getWishListItems(member);
        return ResponseEntity.ok(wishList);
    }

    @DeleteMapping("/items/{itemId}")
    public ResponseEntity<Void> removeItem(@PathVariable Long itemId,
        @LoginMember Member member) {
        wishListService.removeItemFromWishList(itemId, member);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/items/{itemId}/{quantity}")
    public ResponseEntity<Void> changeItemQuantity(@PathVariable Long itemId,@PathVariable int quantity,
        @LoginMember Member member) {
       wishListService.changeItemNumberFromWishList(itemId,quantity,member);
        return ResponseEntity.noContent().build();
    }
}