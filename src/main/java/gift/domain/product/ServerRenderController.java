package gift.domain.product;

import gift.domain.product.dto.ProductRequestDto;
import gift.domain.product.dto.ProductResponseDto;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class ServerRenderController {

    private final ProductService service;

    @Autowired
    public ServerRenderController(ProductService service) {
        this.service = service;
    }

    @GetMapping("/")
    public String index() {
        return "redirect:/products";
    }

    @GetMapping("/products")
    public String showProducts(Model model) {
        List<ProductResponseDto> products = service.getAllProducts();
        model.addAttribute("products", products);
        return "products";
    }

    @GetMapping("/products/add")
    public String showAddProductForm(Model model) {
        model.addAttribute("productRequestDto", new ProductRequestDto("", 0L, ""));
        return "addProduct";
    }

    @PostMapping("/products/add")
    public String addProduct(@ModelAttribute ProductRequestDto requestDto, Model model) {
        service.addProduct(requestDto);
        return "redirect:/products";
    }

    @GetMapping("/products/update/{id}")
    public String showUpdateProductForm(@PathVariable("id") Long id, Model model) {
        ProductResponseDto product = service.getProductById(id);
        ProductRequestDto dto = new ProductRequestDto(product.name(), product.price(), product.imageUrl());
        model.addAttribute("productRequestDto", dto);
        model.addAttribute("productId", id);
        return "updateProduct";
    }

    @PutMapping("/products/update/{id}")
    public String updateProduct(@PathVariable("id") Long id, @ModelAttribute ProductRequestDto requestDto) {
        service.updateProductById(id, requestDto);
        return "redirect:/products";
    }

    @GetMapping("/products/delete/{id}")
    public String deleteProduct(@PathVariable("id") Long id) {
        service.deleteProduct(id);
        return "redirect:/products";
    }
}
