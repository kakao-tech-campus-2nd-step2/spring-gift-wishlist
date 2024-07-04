package gift.controller;

import gift.model.WishList;
import gift.model.WishListDTO;
import gift.security.JwtUtil;
import gift.service.WishListService;
import jakarta.servlet.http.HttpServletRequest;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/wishlist")
public class WishListController {
    private final WishListService wishListService;
    private final JwtUtil jwtUtil;
    private static final Logger logger = LoggerFactory.getLogger(WishListController.class);

    public WishListController(WishListService wishListService, JwtUtil jwtUtil) {
        this.wishListService = wishListService;
        this.jwtUtil = jwtUtil;
    }

    @GetMapping
    public ResponseEntity<List<WishList>> getAllWishList() {
        List<WishList> wishlists = wishListService.getAllWishList();
        return ResponseEntity.ok(wishlists);
    }
    @GetMapping("/{email}")
    public ResponseEntity<List<WishList>> getWishList(@PathVariable String email, HttpServletRequest request) {
        String authHeader = request.getHeader("Authorization");

        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            logger.error("Authorization header is missing or invalid");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build(); // Unauthorized
        }

        String token = authHeader.substring(7);
        String tokenEmail;

        try {
            tokenEmail = jwtUtil.extractEmail(token);
        } catch (Exception e) {
            logger.error("Error extracting email from token", e);
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build(); // Unauthorized
        }

        logger.info("URL Email: {}, Token Email: {}", email, tokenEmail);

        if (!email.equals(tokenEmail)) {
            logger.warn("Emails do not match: URL Email: {}, Token Email: {}", email, tokenEmail);
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build(); // Forbidden
        }
        List<WishList> wishLists = wishListService.getWishListByEmail(email);
        return ResponseEntity.ok(wishLists);
    }

    @PostMapping("/{email}")
    public ResponseEntity<?> createWishList(@PathVariable String email, HttpServletRequest request, @RequestBody WishListDTO wishListDTO) {
        String authHeader = request.getHeader("Authorization");

        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            logger.error("Authorization header is missing or invalid");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build(); // Unauthorized
        }

        String token = authHeader.substring(7);
        String tokenEmail;

        try {
            tokenEmail = jwtUtil.extractEmail(token);
        } catch (Exception e) {
            logger.error("Error extracting email from token", e);
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build(); // Unauthorized
        }

        logger.info("URL Email: {}, Token Email: {}", email, tokenEmail);

        if (!email.equals(tokenEmail)) {
            logger.warn("Emails do not match: URL Email: {}, Token Email: {}", email, tokenEmail);
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build(); // Forbidden
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(wishListDTO);
    }

    @DeleteMapping("/{email}/{product_name}")
    public ResponseEntity<?> deleteWishList(@PathVariable String email, @PathVariable String product_name, HttpServletRequest request) {
        String authHeader = request.getHeader("Authorization");

        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            logger.error("Authorization header is missing or invalid");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build(); // Unauthorized
        }

        String token = authHeader.substring(7);
        String tokenEmail;

        try {
            tokenEmail = jwtUtil.extractEmail(token);
        } catch (Exception e) {
            logger.error("Error extracting email from token", e);
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build(); // Unauthorized
        }

        logger.info("URL Email: {}, Token Email: {}", email, tokenEmail);

        if (!email.equals(tokenEmail)) {
            logger.warn("Emails do not match: URL Email: {}, Token Email: {}", email, tokenEmail);
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build(); // Forbidden
        }

        wishListService.deleteWishList(email, product_name);
        return ResponseEntity.noContent().build();
    }

}
