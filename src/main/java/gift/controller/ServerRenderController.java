package gift.controller;

import gift.model.Product;
import gift.model.ProductDto;
import gift.service.ProductService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

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
        List<Product> products = service.getAllProducts();
        model.addAttribute("products", products);
        return "products";
    }

    @GetMapping("/products/add")
    public String showAddProductForm(Model model) {
        model.addAttribute("productDto", new ProductDto("", 0L, ""));
        return "addProduct";
    }

    @PostMapping("/products/add")
    public String addProduct(@ModelAttribute ProductDto dto, Model model) {
        service.addProduct(dto);
        return "redirect:/products";
    }

    @GetMapping("/products/update/{id}")
    public String showUpdateProductForm(@PathVariable("id") Long id, Model model) {
        Product product = service.getProductById(id);
        ProductDto dto = new ProductDto(product.name(), product.price(), product.imageUrl());
        model.addAttribute("productDto", dto);
        model.addAttribute("productId", id);
        return "updateProduct";
    }

    @PostMapping("/products/update/{id}")
    public String updateProduct(@PathVariable("id") Long id, @ModelAttribute ProductDto dto) {
        service.updateProduct(id, dto);
        return "redirect:/products";
    }

    @GetMapping("/products/delete/{id}")
    public String deleteProduct(@PathVariable("id") Long id) {
        service.deleteProduct(id);
        return "redirect:/products";
    }
}
