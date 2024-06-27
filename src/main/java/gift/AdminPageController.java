package gift;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class AdminPageController {
    private final ProductRepository productRepository = ProductRepository.getInstance();
    private final Integer pageSize = 5;

    @GetMapping(path = "/admin/{page}")
    public String adminPage(Model model, @PathVariable("page") Integer page) {
        List<Product> products = productRepository.getAllProduct();
        products = products.subList((page - 1) * pageSize, Math.min(page * pageSize, products.size()));
        model.addAttribute("products", products);

        return "adminPage";
    }
}
