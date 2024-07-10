package gift.controller;

import gift.annotation.TokenEmail;
import gift.dto.WishResponseEntity;
import gift.service.WishService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class WishController {
    private final WishService wishService;

    public WishController(WishService wishService) {
        this.wishService = wishService;
    }

    @GetMapping("/api/wishes")
    public ResponseEntity<List<WishResponseEntity>> getWishes(@TokenEmail String email) {
        return ResponseEntity.ok(wishService.getWishes(email));
    }
}
