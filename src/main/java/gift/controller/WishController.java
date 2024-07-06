package gift.controller;

import gift.dto.ProductRequest;
import gift.dto.ProductResponse;
import gift.dto.WishDto;
import gift.login.LoginMember;
import gift.dto.MemberDto;
import gift.service.ProductService;
import gift.service.WishService;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/wishes")
public class WishController {
    private final WishService wishService;

    public WishController(WishService wishService) {
        this.wishService = wishService;
    }

    @GetMapping("/{email}")
    public ResponseEntity<List<WishDto>> getAllWishes(@LoginMember MemberDto member, @PathVariable String email) {
        if (!member.email().equals(email)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        System.out.println("controller~~~");
        return ResponseEntity.status(HttpStatus.OK)
            .body(wishService.findAll(email));
    }

    @PostMapping
    public ResponseEntity<WishDto> addWish(@LoginMember MemberDto member, @RequestBody WishDto wish) {
        return ResponseEntity.status(HttpStatus.CREATED).body(wishService.update(member.email(), wish));
    }

    @PutMapping("/{email}/{productId}")
    public ResponseEntity<WishDto> putWish(@LoginMember MemberDto member, @PathVariable Long productId, @RequestBody WishDto wish) {
        return ResponseEntity.status(HttpStatus.OK).body(wishService.update(member.email(), wish));
    }

    @DeleteMapping("/{email}/{productId}")
    public ResponseEntity<Void> deleteProduct(@LoginMember MemberDto member, @PathVariable Long productId) {
        wishService.delete(member.email(), productId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
    }
}

