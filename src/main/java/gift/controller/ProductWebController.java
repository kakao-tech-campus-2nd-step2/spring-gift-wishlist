package gift.controller;

import gift.dto.ProductDTO;
import gift.service.ProductService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/web/products")
public class ProductWebController {

    private final ProductService productService;
    public ProductWebController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/list")
    public String getAllProducts(Model model) {
        List<ProductDTO> products = productService.getAllProducts();
        model.addAttribute("products", products);
        return "productList"; // product list view의 이름을 반환
    }

    @GetMapping("/detail/{id}")
    public String getProductById(@PathVariable("id") Long id, Model model) {
        ProductDTO product = productService.getProductById(id);
        model.addAttribute("product", product);
        return "productDetail"; // product detail view의 이름을 반환
    }

    @GetMapping("/add")
    public String addProductForm(Model model) {
        model.addAttribute("product", new ProductDTO());
        return "addProduct"; // add product form view의 이름을 반환
    }

    @PostMapping("/add")
    public String addProduct(@ModelAttribute ProductDTO productDTO) {
        productService.saveProduct(productDTO);
        return "redirect:/web/products/list"; // 상품 목록 페이지로 리다이렉트
    }

    @GetMapping("/edit/{id}")
    public String editProductForm(@PathVariable("id") Long id, Model model) {
        ProductDTO product = productService.getProductById(id);
        model.addAttribute("product", product);
        return "editProduct"; // edit product form view의 이름을 반환
    }

    @PostMapping("/edit/{id}")
    public String updateProduct(
        @PathVariable("id") Long id,
        @ModelAttribute @Valid ProductDTO productDTO,
        BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return "404"; // 유효성 검사 실패 시 폼 페이지로 리다이렉트
        }

        productService.updateProduct(id, productDTO);
        return "redirect:/web/products/list"; // 상품 목록 페이지로 리다이렉트
    }

    @GetMapping("/delete/{id}")
    public String deleteProduct(@PathVariable("id") Long id) {
        productService.deleteProduct(id);
        return "redirect:/web/products/list"; // 상품 목록 페이지로 리다이렉트
    }
}

