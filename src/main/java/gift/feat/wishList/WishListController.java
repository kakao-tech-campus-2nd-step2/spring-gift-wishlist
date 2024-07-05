package gift.feat.wishList;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import gift.core.authorization.UserDetails;
import gift.feat.product.service.ProductService;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("api/v1/wishlist")
@RequiredArgsConstructor
public class WishListController {
	private final WishListDao wishListDao;
	private final ProductService productService;

	// 위시리스트에 상품 추가
	@PostMapping("")
	public void addWishList(@RequestAttribute("USER") UserDetails userDetails, @RequestParam Long productId) {
		wishListDao.save(new WishProduct(userDetails.getUserId(), productId));
	}

	//로그인한 유저의 위시리스트 조회
	@GetMapping("")
	public List<WishListResponseDto> getWishList(@RequestAttribute("USER") UserDetails userDetails) {
		List<WishProduct> wishList = wishListDao.findByUserId(userDetails.getUserId());
		return wishList.stream().map(wish -> WishListResponseDto.from(productService.getProductById(wish.getProductId()))).toList();
	}
}
