package gift.Controller;

import gift.Model.ProductModel;
import gift.Service.ProductService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/admin/products")
public class ProductAdminController{

    private final ProductService productService;

    public ProductAdminController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public String listProducts(Model model) {
        model.addAttribute("products", productService.getAllProducts());
        return "product-list";
    }

    @GetMapping("/create")
    public String showCreateForm(Model model) {
        model.addAttribute("product", new ProductModel());
        return "product-new";
    }

    @PostMapping
    public String createProduct(@Valid @ModelAttribute ProductModel product) {
        productService.saveProduct(product);
        return "redirect:/admin/products";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable("id") Long id, Model model) {
        if (productService.getProductById(id) == null) {
            throw new RuntimeException("해당 id의 상품이 존재하지 않습니다");
        }
        model.addAttribute("product", productService.getProductById(id));
        return "product-edit";
    }

    @PutMapping ("/{id}")
    public String updateProduct(@Valid @PathVariable("id") Long id, @ModelAttribute ProductModel product) {
        product.setId(id);
        productService.updateProduct(product);
        return "redirect:/admin/products";
    }

    @GetMapping("/delete/{id}")
    public String deleteProduct(@PathVariable("id") Long id) {
        if (productService.getProductById(id) == null) {
            throw new RuntimeException("해당 id의 상품이 존재하지 않습니다");
        }
        productService.deleteProduct(id);
        return "redirect:/admin/products";
    }
}

