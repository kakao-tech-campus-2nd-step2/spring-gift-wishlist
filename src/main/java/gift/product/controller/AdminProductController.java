package gift.product.controller;

import gift.product.model.Product;
import gift.product.service.AdminProductService;
import gift.product.validation.ProductValidation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.atomic.AtomicLong;

@Controller
@RequestMapping("/admin/product")
public class AdminProductController {

    private final AdminProductService adminProductService;
    private final AtomicLong idCounter = new AtomicLong();
    private final ProductValidation productValidation;

    @Autowired
    public AdminProductController(AdminProductService adminProductService, ProductValidation productValidation) {
        this.adminProductService = adminProductService;
        this.productValidation = productValidation;
    }

    @GetMapping("/list")
    public String showProductList(Model model) {
        System.out.println("[ProductController] showProductList()");
        model.addAttribute("productList", adminProductService.getAllProducts());
        return "product";
    }

    @GetMapping("/register")
    public String showProductForm(Model model) {
        System.out.println("[ProductController] showProductForm()");
        model.addAttribute("product", new Product(idCounter.incrementAndGet(), "", 0, ""));
        return "product-form";
    }

    @PostMapping()
    public String registerProduct(@ModelAttribute("id") Long id,
                                    @ModelAttribute("name") String name,
                                    @ModelAttribute("price") int price,
                                    @ModelAttribute("imageUrl") String imageUrl) {
        System.out.println("[ProductController] registerProduct()");
        adminProductService.registerProduct(new Product(id, name, price, imageUrl));
        return "redirect:/admin/product/list";
    }

    @PutMapping()
    public String updateProduct(@ModelAttribute("id") Long id,
                                @ModelAttribute("name") String name,
                                @ModelAttribute("price") int price,
                                @ModelAttribute("imageUrl") String imageUrl,
                                Model model) {
        System.out.println("[ProductController] updateProduct()");
        adminProductService.updateProduct(new Product(id, name, price, imageUrl));
        model.addAttribute("productList", adminProductService.getAllProducts());
        return "product";
    }

    @DeleteMapping("/{id}")
    public String deleteProduct(@PathVariable Long id, Model model) {
        System.out.println("[ProductController] deleteProduct()");
        if(productValidation.existsById(id))
            adminProductService.deleteProduct(id);
        model.addAttribute("productList", adminProductService.getAllProducts());
        return "product";
    }

    @GetMapping("/search")
    public String searchProduct(@RequestParam("keyword") String keyword, Model model) {
        System.out.println("[ProductController] searchProduct()");
        model.addAttribute("productList", adminProductService.getAllProducts());
        model.addAttribute("searchResults", adminProductService.searchProducts(keyword));
        model.addAttribute("keyword", keyword);
        return "product";
    }
}
