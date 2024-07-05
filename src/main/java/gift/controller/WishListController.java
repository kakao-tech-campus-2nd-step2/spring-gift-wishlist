package gift.controller;

import gift.DTO.ProductDTO;
import gift.DTO.Token;
import gift.DTO.UserDTO;
import gift.DTO.WishProductDTO;
import gift.service.ProductService;
import gift.service.UserService;
import gift.service.WishListService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class WishListController {
    private final WishListService wishListService;
    private final ProductService productService;
    private final UserService userService;

    public WishListController(
            WishListService wishListService, ProductService productService, UserService userService
    ){
        this.wishListService = wishListService;
        this.productService = productService;
        this.userService = userService;
    }
    /*
     * 위시리스트 받아오기
     * 성공 시 : 200, 위시리스트를 받아와 반환
     */
    @GetMapping("api/users/{userEmail}")
    public ResponseEntity<List<ProductDTO>> getWishList(@PathVariable("userEmail") String email){
        List<WishProductDTO> wishList = wishListService.loadWishList(email);

        List<ProductDTO> list = new ArrayList<>();
        for (WishProductDTO wishProduct : wishList) {
            list.add(productService.loadOneProduct(wishProduct.getProductId()));
        }

        return new ResponseEntity<>(list, HttpStatus.OK);
    }
    /*
     * 위시리스트 내용 추가
     * email, productId를 받아 위시리스트에 상품을 추가
     * 성공 시 : 200, 성공
     * 실패 시 : 401, 권한 없음 ( 토큰 필요 작업 )
     */
    @PostMapping("api/users/{userEmail}/{productId}")
    public ResponseEntity<Void> addWishList(
            @PathVariable("userEmail") String email,
            @PathVariable("productId") Long id,
            @RequestBody Token token
    ){
        UserDTO userDTO = userService.loadOneUser(email);
        Token checkToken = userService.makeToken(userDTO);

        if(!checkToken.getToken().equals(token.getToken())){
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }

        WishProductDTO wishProductDTO = new WishProductDTO(email, id);
        wishListService.addWishList(wishProductDTO);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    /*
     * 위시리스트 내용 삭제
     * email, productId를 받아 위시리스트에 상품을 추가
     * 성공 시 : 200, 성공
     * 실패 시 : 401, 권한 없음 ( 토큰 필요 작업 )
     */
    @DeleteMapping("api/users/{userEmail}/{productId}")
    public ResponseEntity<Void> deleteWishProduct(
            @PathVariable("userEmail") String email,
            @PathVariable("productId") Long id,
            @RequestBody Token token
    ){
        UserDTO userDTO = userService.loadOneUser(email);
        Token checkToken = userService.makeToken(userDTO);

        if(!checkToken.getToken().equals(token.getToken())){
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }

        wishListService.deleteWishProduct(email, id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
