package gift.controller;

import gift.dto.WishRequestDto;
import gift.service.JwtUtil;
import gift.service.WishService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/wishes")
public class WishController {
    private final WishService wishService;
    private final JwtUtil jwtUtil;

    public WishController(WishService wishService, JwtUtil jwtUtil) {
        this.wishService = wishService;
        this.jwtUtil = jwtUtil;
    }

    @PostMapping
    public ResponseEntity<Void> addWish(@RequestHeader("Authorization") String token, @RequestBody WishRequestDto wishRequest){
        String userEmail = jwtUtil.extractEmail(token);
        wishService.save(userEmail, wishRequest);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

}
