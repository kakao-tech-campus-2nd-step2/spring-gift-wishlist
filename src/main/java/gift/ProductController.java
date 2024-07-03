package gift;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.validation.Valid;

import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

@Controller
@RequestMapping("/admin")
public class ProductController {

    private ProductDao productDao;

    public ProductController(ProductDao productDao){
        this.productDao = productDao;
    }

    @GetMapping()
    public String getProducts(Model model) {
        List<Product> productList = productDao.findAll();
        model.addAttribute("products", productList);
        return "admin_page";
    }

    @GetMapping("/new")
    public String showProductForm(Model model){
        model.addAttribute("product", new Product(0, "", 0, ""));
        return "product_form";
    }

    @PostMapping("/new")
    public String addProduct(@Valid @ModelAttribute Product product, BindingResult bindingResult, Model model) {
        
        if(bindingResult.hasErrors()){
            model.addAttribute("product", product);
            return "product_form";
        }
        
        if(productDao.findOne(product.id()) != null){
            model.addAttribute("errorMessage", "This ID exists");
            model.addAttribute("product", product);
            return "product_form";
        }

        
        productDao.insertProduct(product);
        return "redirect:/admin";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        Product product = productDao.findOne(id);
        if (product != null) {
            model.addAttribute("product", product);
            return "edit_product_form";
        }
        return "redirect:/admin";
    }

    @PostMapping("/edit/{id}")
    public String updateProduct(@PathVariable Long id,@Valid @ModelAttribute Product updatedProduct, BindingResult bindingResult, Model model) {
        if(bindingResult.hasErrors()){
            model.addAttribute("product", updatedProduct);
            return "product_form";
        }
        productDao.updateProduct(updatedProduct);
        return "redirect:/admin";
    }

    @PostMapping("/delete/{id}")
    public String deleteProduct(@PathVariable Long id) {
        productDao.deleteProduct(id);
        return "redirect:/admin";
    }
}