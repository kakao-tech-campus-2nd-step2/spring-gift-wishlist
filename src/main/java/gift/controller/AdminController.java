package gift.controller;

import gift.domain.ProductService;
import gift.dto.ProductDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {
    private final ProductService productService;

    @Autowired
    public AdminController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/products/list")
    public String listProducts(Model model) {
        List<ProductDto> products = productService.findAll();
        model.addAttribute("products", products);
        return "list"; // list.html 파일 보여주기
    }

    @GetMapping("/products/view/{id}")
    public String viewProduct(@PathVariable Long id, Model model) {
        ProductDto product = productService.findById(id);
        model.addAttribute("product", product);
        return "view"; // view.html 파일 보여주기
    }

    @GetMapping("/products/add")
    public String showAddProductForm(Model model) {
        model.addAttribute("productDto", new ProductDto());
        return "add"; // add.html 파일 보여주기
    }

    @GetMapping("/products/edit/{id}")
    public String showEditProductForm(@PathVariable Long id, Model model) {
        ProductDto product = productService.findById(id);
        model.addAttribute("productDto", new ProductDto(product.getId(), product.getName(), product.getPrice(), product.getImgUrl()));
        return "edit";
    }
}