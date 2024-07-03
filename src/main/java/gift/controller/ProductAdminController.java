package gift.controller;

import gift.entity.Product;
import gift.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class ProductAdminController {
    @Autowired
    private ProductService productService;

    @GetMapping("/admin/products")
    public ModelAndView adminProducts(Model model){
        List<Product> products = productService.getAllProducts();
        model.addAttribute("products", products);
        return new ModelAndView("admin/products");
    }

    @GetMapping("/admin/add")
    public ModelAndView adminProductsAdd(Model model){
        return new ModelAndView("admin/add");
    }

    @GetMapping("/admin/modify")
    public ModelAndView adminProductsModify(Model model){
        return new ModelAndView("admin/modify");
    }

    @GetMapping("/admin/delete")
    public ModelAndView adminProductsDelete(Model model){
        return new ModelAndView("admin/delete");
    }
}
