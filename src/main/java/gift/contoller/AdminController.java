package gift.contoller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import gift.dto.ProductResponseDto;
import gift.service.ProductService;

@Controller
public class AdminController {

	private ProductService productService;

	@GetMapping("/admin")
	public String viewProducts(Model model) {
		List<ProductResponseDto> products = productService.getAllProducts().stream()
			.map(ProductResponseDto::from)
			.toList();
		model.addAttribute("products", products);
		return "admin/mainPage";
	}

	@Autowired
	public AdminController(ProductService productService) {
		this.productService = productService;
	}
}
