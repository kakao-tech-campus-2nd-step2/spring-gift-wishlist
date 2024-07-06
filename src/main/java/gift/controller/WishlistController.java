package gift.controller;

import gift.model.WishList;
import gift.service.WishlistService;
import gift.util.JwtUtil;
import io.jsonwebtoken.Claims;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;

import java.util.List;

@Controller
@RequestMapping("/wishlist")
public class WishlistController {
    private final WishlistService wishlistService;
    private final JwtUtil jwtUtil;

    public WishlistController(WishlistService wishlistService, JwtUtil jwtUtil) {
        this.wishlistService = wishlistService;
        this.jwtUtil = jwtUtil;
    }

}
