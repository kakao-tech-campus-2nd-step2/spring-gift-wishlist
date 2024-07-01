package gift.controller;

import gift.model.Product;
import gift.service.ProductService;
import gift.service.ProductService.ProductServiceStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/admin/products")
public class AdminController {

    @Autowired
    private ProductService productService;

    @GetMapping
    public String listProducts(Model model) {
        model.addAttribute("products", productService.getAllProducts());
        return "list";
    }

    @GetMapping("/add")
    public String addProductForm(Model model) {
        model.addAttribute("product", new Product()); // 빈 Product 객체를 생성하여 모델에 추가
        return "create";
    }

    @PostMapping("/add")
    public String addProduct(@ModelAttribute Product product, Model model) {
        ProductServiceStatus response = productService.createProduct(product);
        if (response == ProductServiceStatus.SUCCESS) {
            model.addAttribute("products", productService.getAllProducts());
            return "redirect:/admin/products"; // 상품 추가 후 목록 페이지로 리다이렉트
        }
        return "create";
    }


    @GetMapping("/update/{id}")
    public String editProductForm(@PathVariable Long id, Model model) {
        Product product = productService.getProduct(id);
        model.addAttribute("product", product);
        return "update";
    }

    @PostMapping("/update/{id}")
    public String editProduct(@PathVariable Long id, @ModelAttribute Product product) {
        ProductServiceStatus response = productService.editProduct(id, product);
        if (response == ProductServiceStatus.SUCCESS) {
            return "redirect:/admin/products"; // DB에 상품 수정 후 목록 페이지로 리다이렉트
        }
        return "update";
    }

    @PostMapping("/delete/{id}")
    public String deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id); // 상품 삭제 메소드 호출
        return "redirect:/admin/products"; // 상품 삭제 후 목록 페이지로 리다이렉트
    }
}
