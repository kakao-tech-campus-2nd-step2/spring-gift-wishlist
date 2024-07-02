package gift;

import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/api/products")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService){
        this.productService = productService;
    }

    @GetMapping
    public String getAllProduct(Model model){
        model.addAttribute("products", productService.getAllProducts());
        return "products";
    }

    @GetMapping("/add")
    public String showPostProduct(Model model){
        model.addAttribute("product", new Product(1L,"1",1,"1"));
        return "add";
    }

    @GetMapping("/update/{id}")
    public String showPutProduct(@PathVariable("id") Long id, Model model) {
        Product product = productService.getProductById(id);
        model.addAttribute("product", product);
        return "update";
    }

    @GetMapping("/delete/{id}")
    public String deleteProduct(@PathVariable("id") Long id){
        productService.deleteProduct(id);
        return "redirect:/api/products";
    }

    @PostMapping("/add")
    public String postProduct(@ModelAttribute @Valid Product product, BindingResult result, Model model){
        if(result.hasErrors()){
            return "add";
        }
        Product newProduct= new Product(null, product.name(), product.price(), product.imageUrl());
        productService.addProduct(newProduct);
        model.addAttribute("product",newProduct);
        return "redirect:/api/products";
    }

    @PostMapping("/update/{id}")
    public String putProduct(@PathVariable("id") Long id,  @ModelAttribute @Valid Product product, BindingResult result){
        if(result.hasErrors()){
            return "update";
        }
        Product newProduct= new Product(id, product.name(), product.price(), product.imageUrl());
        productService.updateProduct(newProduct);
        return "redirect:/api/products";
    }
}
