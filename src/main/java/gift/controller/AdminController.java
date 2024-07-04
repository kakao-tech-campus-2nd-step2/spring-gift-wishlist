package gift.controller;

import gift.controller.dto.request.ProductRequest;
import gift.controller.dto.response.ProductResponse;
import gift.service.ProductService;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class AdminController {

    private final ProductService productService;

    public AdminController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("")
    public String index(Model model) {
        List<ProductResponse> products = productService.findAll();
        model.addAttribute("products", products);
        return "index";
    }

    @GetMapping("admin/product/new")
    public String newProduct() {
        return "new";
    }

    @GetMapping("admin/product/{id}")
    public String updateProduct(@PathVariable("id") @NotNull @Min(1) Long id, Model model) {
        ProductResponse product = productService.findById(id);
        model.addAttribute("product", product);
        return "edit";
    }

    @PostMapping("admin/product")
    public String newProduct(@ModelAttribute ProductRequest request) {
        productService.save(request);
        return "redirect:/";
    }

    @PutMapping("admin/product/{id}")
    public String updateProduct(@PathVariable("id") @NotNull @Min(1) Long id,
                                @ModelAttribute ProductRequest request) {
        productService.updateById(id, request);
        return "redirect:/";
    }

    @DeleteMapping("/admin/product/{id}")
    public String delete(@PathVariable("id") @NotNull @Min(1) Long id) {
        productService.deleteById(id);
        return "redirect:/";
    }
}
