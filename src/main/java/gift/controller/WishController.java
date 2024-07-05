package gift.controller;

import gift.service.JwtProvider;
import gift.service.WishService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/wishes")
public class WishController {
    private final WishService wishService;
    private final JwtProvider jwtProvider;

    public WishController(WishService wishService, JwtProvider jwtProvider) {
        this.wishService = wishService;
        this.jwtProvider = jwtProvider;
    }

    @GetMapping
    public ResponseEntity<List<WishResponseDto>> getWishList(@RequestHeader("Authorization") Stirng token){
    }



}
