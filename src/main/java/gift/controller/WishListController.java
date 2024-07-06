package gift.controller;

import gift.domain.MemberRequest;
import gift.domain.WishListRequest;
import gift.domain.WishListResponse;
import gift.service.JwtService;
import gift.service.MemberService;
import gift.service.WishListService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
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

    @PostMapping("/read")
    public ResponseEntity<HashMap<String,Object>> read(){
        String jwtId = jwtService.getMemberId();
        if(jwtId == null){
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(null);
        }
        else{
            List<WishListResponse> nowWishList = wishListService.findById(jwtId);
            HashMap<String,Object> answer = new HashMap<>();
            answer.put("data",nowWishList);
            return ResponseEntity.ok().body(answer);
        }
    }

    @PostMapping("/delete")
    public ResponseEntity<String> delete(
            @RequestParam("menuId") Long menuId
    ){
        String jwtId = jwtService.getMemberId();
        if(jwtId == null){
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("권한이 없습니다.");
        }
        else{
            if(wishListService.delete(jwtId,menuId)){
                return ResponseEntity.ok().body("성공적으로 삭제되었습니다.");
            }
            else{
                return ResponseEntity.status(HttpStatus.FORBIDDEN).body("해당 메뉴가 존재하지 않습니다.");
            }
        }
    }
}