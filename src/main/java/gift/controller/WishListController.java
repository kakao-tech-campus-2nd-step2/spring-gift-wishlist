package gift.controller;

import gift.domain.MemberRequest;
import gift.domain.WishListRequest;
import gift.service.JwtService;
import gift.service.MemberService;
import gift.service.WishListService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

@RestController
@RequestMapping("/wishlist")
public class WishListController {
    WishListService wishListService;
    JwtService jwtService;

    public WishListController(WishListService wishListService, JwtService jwtService){
        this.wishListService = wishListService;
        this.jwtService = jwtService;
    }

    @PostMapping("/create")
    public ResponseEntity<String> create(
            @RequestHeader("Authorization") String token,
            @RequestParam("memberId") String memberId,
            @RequestParam("menuId") Long menuId
    ) {
        WishListRequest wishListRequest = new WishListRequest(memberId,menuId);
        System.out.println(token);
        String jwtId = jwtService.getMemberId();
        if(!Objects.equals(jwtId, memberId) || jwtId == null){
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("권한이 없습니다");
        }
        else{
            wishListService.create(wishListRequest);
            HttpHeaders headers = new HttpHeaders();
            headers.add("Authorization",token.replace("Bearer ",""));
            return ResponseEntity.ok().headers(headers).body("success");
        }
    }
}