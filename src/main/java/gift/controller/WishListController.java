package gift.controller;

import gift.model.*;
import gift.service.GiftService;
import gift.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
public class WishListController {

    private final UserService userService;
    private final GiftService giftService;

    @Autowired
    public WishListController(UserService userService, GiftService giftService) {
        this.userService = userService;
        this.giftService = giftService;
    }

    @GetMapping("/gifts")
    public ResponseEntity<List<GiftResponse>> getGiftList(@RequestHeader("Authorization") String authHeader) {
        Optional<User> user = getUserFromToken(authHeader);
        if (user.isPresent()) {
            List<GiftResponse> gifts = giftService.getAllGifts();
            return ResponseEntity.ok(gifts);
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
    }

    @PostMapping("/gifts/{giftId}")
    public ResponseEntity<String> addGiftToCart(
            @RequestHeader("Authorization") String authHeader,
            @PathVariable Long giftId) {
        Optional<User> user = getUserFromToken(authHeader);
        if (user.isPresent()) {
            userService.addGiftToUser(user.get().getId(), giftId);
            return ResponseEntity.ok("위시리스트에 상품이 추가되었습니다.");
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid token");
    }

    @DeleteMapping("/gifts/{giftId}")
    public ResponseEntity<String> removeGiftFromCart(
            @RequestHeader("Authorization") String authHeader,
            @PathVariable Long giftId) {
        Optional<User> user = getUserFromToken(authHeader);
        if (user.isPresent()) {
            userService.removeGiftFromUser(user.get().getId(), giftId);
            return ResponseEntity.ok("카트에서 상품이 삭제되었습니다.");
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid token");
    }

    @GetMapping("/my-gifts")
    public ResponseEntity<List<GiftResponse>> getUserGifts(@RequestHeader("Authorization") String authHeader) {
        Optional<User> user = getUserFromToken(authHeader);
        if (user.isPresent()) {
            List<Gift> gifts = userService.getGiftsForUser(user.get().getId());
            List<GiftResponse> giftResponses = gifts.stream()
                    .map(GiftResponse::from)
                    .collect(Collectors.toList());
            return ResponseEntity.ok(giftResponses);
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
    }

    private Optional<User> getUserFromToken(String authHeader) {
        String token = extractToken(authHeader);
        if (token != null && userService.validateToken(token)) {
            return userService.getUserByToken(token);
        }
        return Optional.empty();
    }

    private String extractToken(String authHeader) {
        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            return authHeader.replace("Bearer ", "").trim();
        }
        return null;
    }
}
