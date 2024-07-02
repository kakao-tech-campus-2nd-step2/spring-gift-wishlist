package gift;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/manager")
public class ProductController {
    private final ProductRepository productRepository;

    public ProductController(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @PostMapping("/products/add")
    public String addProduct(@ModelAttribute ProductDTO newProduct, RedirectAttributes redirectAttributes){
        System.out.println("add");
        Product product1 = productRepository.insertProduct(newProduct);
        redirectAttributes.addAttribute("id", product1.id());
        System.out.println(product1.id());
        return "redirect:/manager/products/{id}";
    }

    @PutMapping("/products/update/{id}")
    public String updateProduct(@PathVariable Long id, @ModelAttribute ProductDTO product, RedirectAttributes redirectAttributes){
        System.out.println("update");
        productRepository.updateProduct(id, product);
        redirectAttributes.addAttribute("id", id);
        return "redirect:/manager/products/{id}";
    }

    @DeleteMapping("/products/delete/{id}")
    public String deleteProduct(@PathVariable Long id){
        System.out.println("delete");
        Product product = productRepository.selectProduct(id);
        if(product != null){
            productRepository.deleteProduct(id);
        }
        return "redirect:/manager/products";
    }

    @GetMapping("/products")
    public String getProductsView(Model model){
        model.addAttribute("products", productRepository.selectProducts());
        return "ManageProduct";
    }

    @GetMapping("/products/add")
    public String addProductView(Model model){
        model.addAttribute("product", new Product(null,null,null,null));
        return "AddOrUpdateProduct";
    }

    @GetMapping("/products/update/{id}")
    public String updateProductView(@PathVariable Long id, Model model){
        model.addAttribute("product", productRepository.selectProduct(id));
        return "AddOrUpdateProduct";
    }

    @GetMapping("/products/{id}")
    public String getProduct(@PathVariable long id, Model model) {
        Product product = productRepository.selectProduct(id);
        model.addAttribute("product", product);
        return "ProductInfo";
    }
}

