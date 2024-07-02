package gift.Controller;

import gift.Model.Product;
import gift.Model.ProductDAO;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin/products")
public class AdminController {
    private ProductDAO productDAO;

    @Autowired
    public void setProductDao(ProductDAO productDao){
        this.productDAO = productDao;
    }

    @GetMapping
    public String getAllProducts(Model model){
        List<Product> products = productDAO.selectAllProduct();
        model.addAttribute("products", products);
        return "products";
    }

    @GetMapping("/add")
    public String addProductForm(Model model){
        model.addAttribute("product", new Product(0,"",0,""));
        return "add";
    }

    @PostMapping("/add")
    public String addProduct(@ModelAttribute Product product){
        productDAO.insertProduct(product);
        return "redirect:/admin/products";
    }

    @GetMapping("/edit/{id}")
    public String updateProductForm(@PathVariable("id") Long id, Model model){
        model.addAttribute("product", productDAO.selectProduct(id));
        return "edit";
    }

    @PostMapping("edit/{id}")
    public String updateProduct(@PathVariable("id") Long id, @ModelAttribute Product product){
        productDAO.updateProduct(id, product);
        return "redirect:/admin/products";
    }

    @GetMapping("/delete/{id}")
    public String deleteProduct(@PathVariable("id") Long id){
        productDAO.deleteProduct(id);
        return "redirect:/admin/products";
    }
}