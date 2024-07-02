package gift;

import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
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
    public String addProduct(@Valid @ModelAttribute("newProduct") ProductDTO newProduct, BindingResult bindingResult, RedirectAttributes redirectAttributes){

        if(newProduct.name.contains("카카오")){
            bindingResult.addError(new FieldError("product", "name", "\"카카오\"가 포함된 문구는 담당 MD와 협의한 경우에만 사용할 수 있습니다."));
        }

        if (bindingResult.hasErrors()) {
            return "AddProduct";
        }

        System.out.println("add");
        Product product = productRepository.insertProduct(newProduct);
        redirectAttributes.addAttribute("id", product.getId());
        System.out.println(product.id);
        return "redirect:/manager/products/{id}";
    }

    @PutMapping("/products/update/{id}")
    public String updateProduct(@PathVariable Long id, @Valid @ModelAttribute("product") ProductDTO product, BindingResult bindingResult, RedirectAttributes redirectAttributes){
        if(product.name.contains("카카오")){
            bindingResult.addError(new FieldError("product", "name", "\"카카오\"가 포함된 문구는 담당 MD와 협의한 경우에만 사용할 수 있습니다."));
        }

        if (bindingResult.hasErrors()) {
            return "UpdateProduct";
        }

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
        model.addAttribute("newProduct", new Product());
        return "AddProduct";
    }

    @GetMapping("/products/update/{id}")
    public String updateProductView(@PathVariable Long id, Model model){
        model.addAttribute("product", productRepository.selectProduct(id));
        return "UpdateProduct";
    }

    @GetMapping("/products/{id}")
    public String getProduct(@PathVariable long id, Model model) {
        Product product = productRepository.selectProduct(id);
        model.addAttribute("product", product);
        return "ProductInfo";
    }
}
