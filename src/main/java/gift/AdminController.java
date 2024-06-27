package gift;

import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.ui.Model;

@Controller
@RequestMapping("/admin/product")
public class AdminController {
    @Autowired
    private ProductOperation productOperation;

    @GetMapping("/list")
    public String productList(Model model) {
        Map<Long, Product> products = productOperation.getAllProduct();
        model.addAttribute("products", products);
        return "admin/product-list";
    }

}
