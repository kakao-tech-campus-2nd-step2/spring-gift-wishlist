package gift;

import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.ui.Model;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/admin/product")
public class AdminController {

    @Autowired
    private ProductOperation productOperation;

    @GetMapping("/list")
    public String productList(Model model) {
        Map<Long, Product> products = productOperation.getAllProduct();
        model.addAttribute("products", products);
        return "product-list";
    }

    @GetMapping("/add")
    public ModelAndView showAddPage() {
        ModelAndView modelAndView = new ModelAndView("product-add");
        modelAndView.addObject("product", new Product(0L, "", 0L, ""));
        return modelAndView;
    }

    @PostMapping("/add")
    public String addProduct(@ModelAttribute Product p) {
        Product product = productOperation.createProduct(p);
        return "redirect:/admin/product/list";
    }

    @DeleteMapping("/delete/{id}")
    public String deleteProduct(@PathVariable Long id) {
        boolean check = productOperation.deleteProduct(id);
        return "redirect:/admin/product/list";
    }
}
