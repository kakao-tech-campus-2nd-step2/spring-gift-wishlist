package gift;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AdminPageController {
    private final ProductRepository productRepository = ProductRepository.getInstance();

    @GetMapping(path = "/admin")
    public String adminPage(Model model) {
        List<Product> products = productRepository.getAllProduct();
        model.addAttribute("products", products);

        return "adminPage";
    }
}
