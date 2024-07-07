package gift.controller;

import gift.domain.Member;
import gift.domain.Wish;
import gift.dto.WishRequest;
import gift.service.JwtService;
import gift.service.MemberService;
import gift.service.WishService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/wishes")
public class WishController {

    private WishService wishService;

    public WishController(WishService wishService) {
        this.wishService = wishService;
    }


    @GetMapping
    public ResponseEntity<List<Wish>> getWishes(@LoginMember Member member) {
        List<Wish> wishes = wishService.getWishes(member.getId());
        return ResponseEntity.ok(wishes);
    }

    @PostMapping
    public ResponseEntity<?> addWish(@RequestBody Wish wish, @LoginMember Member member) {
        try {
            wishService.addWish(wish.getId(), wish.getProductName(), member.getId());
            return ResponseEntity.ok("위시리스트 담기 완료!");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("위시리스트 담기 실패: " + e.getMessage());
        }

    }

    @DeleteMapping("/{wishId}")
    public ResponseEntity<Void> removeWish(@PathVariable Long wishId, @LoginMember Member member) {
        wishService.removeWish(wishId);
        return ResponseEntity.noContent().build();
    }

}
