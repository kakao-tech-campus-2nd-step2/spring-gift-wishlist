package gift.Controller;

import gift.Service.ProductService;
import gift.model.Product;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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
        Map<Long, Product> products = productService.getProducts();
        for (Product value : products.values()) {
            System.out.println("value = " + value);
        }
        System.out.println("products.size() = " + products.size());
        model.addAttribute("products", products);
        return "index";
    }
}
