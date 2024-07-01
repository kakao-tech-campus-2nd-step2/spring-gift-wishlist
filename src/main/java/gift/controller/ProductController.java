package gift.controller;

import gift.db.ProductH2DB;
import gift.dto.Product;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class ProductController {

    private final ProductH2DB productDB;

    public ProductController(ProductH2DB productDB) {
        this.productDB = productDB;
    }

    @GetMapping("/")
    public String getProducts(Model model) {
        model.addAttribute("products", productDB.getProducts());
        return "version-SSR/index";
    }

    @GetMapping("/add")
    public String getAddForm(Model model) {
        model.addAttribute("product", new Product()); // Add an empty Product object for the form
        return "version-SSR/add-form";
    }

    @PostMapping("/add")
    public String addProduct(Product product) {
        try {
            productDB.addProduct(product);
            return "redirect:/";
        } catch (Exception e) {
            return "version-SSR/add-error";
        }
    }

    @PostMapping("/deleteSelected")
    public String deleteSelectedProduct(@RequestParam("productIds") List<Long> productIds) {
        productDB.removeProducts(productIds);
        return "redirect:/";
    }

    @PostMapping("/delete")
    public String deleteProduct(@RequestParam("productId") Long productId) throws Exception {
        productDB.deleteProduct(productId);
        return "redirect:/";
    }

    @GetMapping("/edit/{id}")
    public String getEditForm(@PathVariable("id") long id, Model model) {
        model.addAttribute("product", productDB.getProduct(id)); // Add an empty Product object for the form
        return "version-SSR/edit-form";
    }

    @PostMapping("/edit")
    public String getEditForm(Product product) {
        try {
            productDB.updateProduct(product);
            return "redirect:/";
        } catch (Exception e) {
            return "version-SSR/edit-error";
        }
    }

}
