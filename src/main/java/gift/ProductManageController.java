package gift;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Controller
@RequestMapping("/api/manage/products")
public class ProductManageController {

    private final ProductService productService;
    public ProductManageController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public String retrieveProduct(Model model) {
        model.addAttribute("products", productService.getProduct());
        return "manage-products";
    }

    @PostMapping("/delete/{productId}")
    public String deleteProduct(@PathVariable("productId") Long productId) {
        productService.deleteTheProduct(productId);
        return "redirect:/api/manage/products";
    }

    @GetMapping("/update/{productId}")
    public String editProductForm(@PathVariable("productId") Long productId, Model model) {
        Product product = productService.getOneProduct(productId);
        model.addAttribute("product", product);
        return "product-update-form";
    }

    @PostMapping("/update/{productId}")
    public String updateProduct(@PathVariable("productId") Long productId, @ModelAttribute("product") Product updatedProduct) {
        productService.updateProductInfo(productId, updatedProduct);
        return "redirect:/api/manage/products";
    }

    @GetMapping("/add")
    public String addProductForm(Model model) {
        model.addAttribute("product", new Product());
        return "product-addition-form";
    }

    @PostMapping("/add")
    public String addProduct(@ModelAttribute("product") Product product) {
        System.out.println(product.getId());
        productService.addNewProduct(product);
        return "redirect:/api/manage/products";
    }

}
