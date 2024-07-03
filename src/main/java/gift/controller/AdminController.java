package gift.controller;

import gift.domain.IdentifiedProductDto;
import gift.domain.UnidentifiedProductDto;
import gift.service.ProductService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/admin/products")
public class AdminController {
    private final ProductService productService;

    @Autowired
    public AdminController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public String listProducts(Model model) {
        List<IdentifiedProductDto> products = productService.findAll();
        model.addAttribute("products", products);
        return "admin";
    }

    @GetMapping("/add")
    public String addForm(Model model) {
        model.addAttribute("product", new UnidentifiedProductDto(null, null, null));
        return "add-form";
    }

    @PostMapping("/add")
    public String addProduct(@ModelAttribute UnidentifiedProductDto product) {
        productService.save(product);
        return "redirect:/admin/products";
    }

    @GetMapping("/edit/{id}")
    public String editForm(@PathVariable Long id, Model model) {
        model.addAttribute("product", productService.find(id));
        return "edit-form";
    }

    @PostMapping("/edit/{id}")
    public String editForm(@PathVariable Long id, @ModelAttribute UnidentifiedProductDto product) {
        productService.update(id, product);
        return "redirect:/admin/products";
    }

    @GetMapping("/delete/{id}")
    public String deleteProduct(@PathVariable Long id) {
        productService.delete(id);
        return "redirect:/admin/products";
    }
}
