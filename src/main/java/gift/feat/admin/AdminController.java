package gift.feat.admin;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import gift.feat.product.dto.ProductResponseDto;
import gift.feat.product.dto.UserResponseDto;
import gift.feat.product.service.ProductService;
import gift.feat.user.UserDao;
import gift.feat.wishList.WishListDao;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class AdminController {
	private final ProductService productService;
	private final WishListDao wishListDao;
	private final UserDao userDao;

	@GetMapping("/admin/product")
	public String viewProducts(Model model) {
		List<ProductResponseDto> products = productService.getAllProducts().stream()
			.map(ProductResponseDto::from)
			.toList();
		model.addAttribute("products", products);
		return "admin/productPage";
	}


	@GetMapping("/admin/user/wishlist/{userId}")
	public String viewWishList(Model model,@PathVariable Long userId) {
		List<ProductResponseDto> products = wishListDao.findByUserId(userId).stream()
			.map(wish -> ProductResponseDto.from(productService.getProductById(wish.getProductId())))
			.toList();
		model.addAttribute("products", products);
		return "admin/wishListPage";
	}

}
