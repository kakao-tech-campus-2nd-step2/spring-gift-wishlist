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
public class ProtectedController {


    private UserService userService;

    private GiftService giftService;

    @Autowired
    public ProtectedController(UserService userService, GiftService giftService) {
        this.userService = userService;
        this.giftService = giftService;
    }
    @GetMapping("/gifts")
    public ResponseEntity<List<GiftResponse>> getGiftList(@RequestHeader("Authorization") String authHeader) {
        String token = authHeader.replace("Bearer ", "").trim();
        if (userService.validateToken(token)) {
            List<GiftResponse> gifts = giftService.getAllGifts();
            return ResponseEntity.ok(gifts);
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
        }
    }
    @PostMapping("/gifts/{giftId}")
    public ResponseEntity<String> addGiftToCart(
            @RequestHeader("Authorization") String authHeader,
            @PathVariable Long giftId) {
        String token = authHeader.replace("Bearer ", "").trim();
        if (userService.validateToken(token)) {
            Optional<User> user = userService.getUserByToken(token);
            if (user.isPresent()) {
                userService.addGiftToUser(user.get().getId(), giftId);
                return ResponseEntity.ok("위시리스트에 상품이 추가되었습니다.");
            } else {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("유저정보가 없습니다.");
            }
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid token");
        }
    }
    @DeleteMapping("/gifts/{giftId}")
    public ResponseEntity<String> removeGiftFromCart(
            @RequestHeader("Authorization") String authHeader,
            @PathVariable Long giftId) {
        String token = authHeader.replace("Bearer ", "").trim();
        if (userService.validateToken(token)) {
            Optional<User> user = userService.getUserByToken(token);
            if (user.isPresent()) {
                userService.removeGiftFromUser(user.get().getId(), giftId);
                return ResponseEntity.ok("카트에서 상품이 삭제되었습니다.");
            } else {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("유저정보가 없습니다.");
            }
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid token");
        }
    }
    @GetMapping("/my-gifts")
    public ResponseEntity<List<GiftResponse>> getUserGifts(@RequestHeader("Authorization") String authHeader) {
        String token = authHeader.replace("Bearer ", "").trim();
        if (userService.validateToken(token)) {
            Optional<User> user = userService.getUserByToken(token);
            if (user.isPresent()) {
                List<Gift> gifts = userService.getGiftsForUser(user.get().getId());
                List<GiftResponse> giftResponses = gifts.stream()
                        .map(GiftResponse::from)
                        .collect(Collectors.toList());
                return ResponseEntity.ok(giftResponses);
            } else {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
            }
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
        }
    }


}