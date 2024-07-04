package gift.controller;

import gift.model.*;
import gift.service.GiftService;
import gift.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
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
    public ResponseEntity<?> getGiftList(HttpServletRequest request) {
        User user = (User) request.getAttribute("user");
        if (user != null) {
            List<GiftResponse> gifts = giftService.getAllGifts();
            return ResponseEntity.ok(gifts);
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid token");
    }

    @PostMapping("/gifts/{giftId}")
    public ResponseEntity<String> addGiftToCart(
            HttpServletRequest request,
            @PathVariable Long giftId) {
        User user = (User) request.getAttribute("user");
        if (user != null) {
            userService.addGiftToUser(user.getId(), giftId);
            return ResponseEntity.ok("위시리스트에 상품이 추가되었습니다.");
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid token");
    }

    @DeleteMapping("/gifts/{giftId}")
    public ResponseEntity<String> removeGiftFromCart(
            HttpServletRequest request,
            @PathVariable Long giftId) {
        User user = (User) request.getAttribute("user");
        if (user != null) {
            userService.removeGiftFromUser(user.getId(), giftId);
            return ResponseEntity.ok("카트에서 상품이 삭제되었습니다.");
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid token");
    }

    @GetMapping("/my-gifts")
    public ResponseEntity<List<GiftResponse>> getUserGifts(HttpServletRequest request) {
        User user = (User) request.getAttribute("user");
        if (user != null) {
            List<Gift> gifts = userService.getGiftsForUser(user.getId());
            List<GiftResponse> giftResponses = gifts.stream()
                    .map(GiftResponse::from)
                    .collect(Collectors.toList());
            return ResponseEntity.ok(giftResponses);
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
    }

}
