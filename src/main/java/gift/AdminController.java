package gift;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;


@Controller
public class AdminController {
    final String adminpath = "/admin/products";

    private final ProductService productService;

    public AdminController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping(adminpath)
    public String getProduct(Model model) {
        model.addAttribute("products", productService.getAllProducts());
        return "admin";
    }

    @GetMapping(adminpath + "/add")
    public String addProduct() {
        return "add";
    }

    @PostMapping(adminpath + "/add")
    public String addProduct(@ModelAttribute("product") Product product) {
        productService.addProduct(product);
        return "redirect:" + adminpath;
    }

    @GetMapping(adminpath + "/del/{id}")
    public String delProduct(@PathVariable int id) {
        productService.deleteProduct(id);
        return "redirect:" + adminpath;
    }

    @RequestMapping(adminpath + "/edit/{id}")
    public String editProduct(@PathVariable int id, Model model) {
        model.addAttribute("product", productService.getProductById(id));
        return "edit";

    }

    @PostMapping(adminpath + "/edit/{id}")
    public String updateProduct(@PathVariable int id, @ModelAttribute("product") Product product) {
        productService.updateProduct(id, product);
        return "redirect:" + adminpath;
    }
}
