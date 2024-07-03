package gift.controller;

import gift.ProductService;
import gift.dto.ProductRequestDto;
import gift.dto.ProductResponseDto;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class WebController {
    private final ProductService productService;

    public WebController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/")
    public String itemList(Model model) {
        List<ProductResponseDto> products = productService.findAll();
        model.addAttribute("products", products);
        return "items";
    }

    @PostMapping("products/add")
    public String add(@ModelAttribute ProductRequestDto requestDto) {
        productService.addProduct(requestDto);
        return "redirect:/";
    }

    @GetMapping("products/add")
    public String add() {
        return "addForm";
    }

    @GetMapping("products/edit/{id}")
    public String getEditForm(
            @PathVariable("id") Long id, Model model) {
        System.out.println(id);
        ProductResponseDto product = productService.findProduct(id);
        model.addAttribute("product", product);
        model.addAttribute("id", id);
        return "editForm";
    }

    @PutMapping("products/edit/{id}")
    public String editProduct(
            @PathVariable("id") Long id,
            @ModelAttribute ProductRequestDto product) {
        productService.editProduct(id, product);
        return "redirect:/";
    }

    @DeleteMapping("products/delete/{id}")
    public String deleteProduct(
            @PathVariable("id") Long id
    ) {
        productService.deleteProduct(id);
        return "redirect:/";
    }
}
