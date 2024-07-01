package gift;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.ui.Model;

@Controller
@RequestMapping("/admin")
public class ProductController {

    private ProductDao productDao;

    public ProductController(ProductDao productDao){
        this.productDao = productDao;
    }

    @GetMapping("")
    public String getProducts(Model model) {
        List<Product> productList = productDao.getProducts();
        model.addAttribute("products", productList);
        return "admin_page";
    }

    @GetMapping("/new")
    public String showProductForm(){
        return "product_form";
    }

    @PostMapping("/new")
    public String addProduct(@ModelAttribute Product product) {
        productDao.insertProduct(product);
        return "redirect:/admin";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        Product product = productDao.getProduct(id);
        if (product != null) {
            model.addAttribute("product", product);
            return "edit_product_form";
        }
        return "redirect:/admin";
    }

    @PostMapping("/edit/{id}")
    public String updateProduct(@PathVariable Long id, @ModelAttribute Product updatedProduct) {
        productDao.updateProduct(updatedProduct);
        return "redirect:/admin";
    }

    @PostMapping("/delete/{id}")
    public String deleteProduct(@PathVariable Long id) {
        productDao.deleteProduct(id);
        return "redirect:/admin";
    }
}