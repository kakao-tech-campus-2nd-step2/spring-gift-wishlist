package gift.controller;

import gift.dto.MemberDto;
import gift.dto.WishDto;
import gift.model.member.LoginMember;
import gift.model.member.Member;
import gift.model.wish.Wish;
import gift.service.WishListService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/wishlist")
public class WishListController {

    private final WishListService wishListService;

    public WishListController(WishListService wishListService) {
        this.wishListService = wishListService;
    }

    @GetMapping
    public ResponseEntity<List<Wish>> getAllWishes(@LoginMember Member Member) {
        List<Wish> wishes = wishListService.getAllWishes();
        return ResponseEntity.ok(wishes);
    }

    @PostMapping
    public ResponseEntity<Void> insertWish(@LoginMember Member Member, @RequestBody WishDto wishDto) {
        wishListService.insertWish(wishDto);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> removeWish(@LoginMember MemberDto memberDto, @PathVariable Long id) {
        wishListService.deleteWish(id);
        return ResponseEntity.ok().build();
    }

    @PutMapping
    public ResponseEntity<Void> updateWish(@LoginMember MemberDto memberDto, @RequestBody WishDto wishDto) {
        wishListService.updateWish(wishDto);
        return ResponseEntity.ok().build();
    }
}