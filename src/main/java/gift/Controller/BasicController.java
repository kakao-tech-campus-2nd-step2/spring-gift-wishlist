package gift.Controller;

import gift.Service.ProductService;
import gift.Model.Product;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/api")
public class BasicController {

    private final ProductService productService;
    @Autowired
    public BasicController(ProductService productService) {
        this.productService = productService;
    }
    @GetMapping
    String homePage(Model model) {
        // 현재 상품 목록 조회
        List<Product> products = productService.getProducts();
        System.out.println("products.size() = " + products.size());
        model.addAttribute("products", products);
        return "index";
    }
}
