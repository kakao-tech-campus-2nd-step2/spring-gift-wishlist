package gift.controller;

import gift.config.auth.LoginUser;
import gift.domain.model.User;
import gift.domain.model.WishResponseDto;
import gift.service.WishService;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import gift.util.JwtUtil;

@RestController
@RequestMapping("/api/wishes")
public class WishController {

    private final WishService wishService;
    private final JwtUtil jwtUtil;

    public WishController(WishService wishService, JwtUtil jwtUtil) {
        this.wishService = wishService;
        this.jwtUtil = jwtUtil;
    }

    @GetMapping
    public ResponseEntity<Object> getWishes(@LoginUser User user) {
        List<WishResponseDto> wishes = wishService.getWishesByUserEmail(user.getEmail());
        return ResponseEntity.ok(wishes);
    }
}
