package gift.controller;

import gift.dto.wish.WishResponseDTO;
import gift.service.MemberService;
import gift.service.WishService;
import jakarta.servlet.http.HttpServletRequest;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/wishes")
public class WishController {
    private final WishService wishService;
    private final MemberService memberService;

    public WishController(WishService wishService, MemberService memberService) {
        this.wishService = wishService;
        this.memberService = memberService;
    }

    private Long getMemberIdFromRequest(HttpServletRequest request) {
        memberService.validateToken(request);
        return (Long) request.getAttribute("memberId");
    }

    @GetMapping
    public ResponseEntity<List<WishResponseDTO>> getWishlist(HttpServletRequest request) {
        Long memberId = getMemberIdFromRequest(request);
        List<WishResponseDTO> wishlist = wishService.getWishlistByMemberId(memberId);
        return ResponseEntity.ok(wishlist);
    }
}
