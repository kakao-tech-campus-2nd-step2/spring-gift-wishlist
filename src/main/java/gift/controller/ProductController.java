package gift.controller;

import gift.exception.ProductAlreadyExistsException;
import gift.exception.ProductNotFoundException;
import gift.exception.InvalidProductDataException;
import gift.model.Product;
import gift.service.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@Controller
@RequestMapping("/products")
public class ProductController {
    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public String showProductsForm(Model model) {
        List<Product> products = productService.getAllProducts();
        model.addAttribute("products", products);
        return "products";
    }

    @GetMapping("/new")
    public String showCreateForm(Model model) {
        model.addAttribute("product", new Product());
        return "create_product";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable("id") Long id, Model model) {
        Product product = productService.getProductById(id);
        if (product == null) {
            throw new ProductNotFoundException(id);
        }
        model.addAttribute("product", product);
        return "edit_product";
    }

    @PostMapping
    public String createProduct(Product product) {
        if (productService.getProductById(product.getId()) != null) {
            throw new ProductAlreadyExistsException(product.getName());
        }

        if (product.getId() == null || product.getId() < 0 || product.getId() == 0){
            throw new InvalidProductDataException("ID를 다시 입력해주세요.");
        }
        if (product.getName() == null || product.getName().isEmpty()) {
            throw new InvalidProductDataException("이름을 다시 입력해주세요.");
        }
        if (product.getPrice() == null || product.getPrice() < 0 || product.getPrice() == 0){
            throw new InvalidProductDataException("가격을 다시 입력해주세요.");
        }
        if (product.getImageurl() == null || product.getImageurl().isEmpty()) {
            throw new InvalidProductDataException("이미지 URL을 다시 입력해주세요.");
        }

        productService.createProduct(product);
        return "redirect:/products";
    }

    @PostMapping("/update/{id}")
    public String updateProduct(@PathVariable("id") Long id, Product product) {
        product.setId(id);
        if (productService.getProductById(product.getId()) != null) {
            throw new ProductAlreadyExistsException(product.getName());
        }
        if (product.getId() == null || product.getId() < 0 || product.getId() == 0){
            throw new InvalidProductDataException("ID를 다시 입력해주세요.");
        }
        if (product.getName() == null || product.getName().isEmpty()) {
            throw new InvalidProductDataException("이름을 다시 입력해주세요.");
        }
        if (product.getPrice() == null || product.getPrice() < 0 || product.getPrice() == 0){
            throw new InvalidProductDataException("가격을 다시 입력해주세요.");
        }
        if (product.getImageurl() == null || product.getImageurl().isEmpty()) {
            throw new InvalidProductDataException("이미지 URL을 다시 입력해주세요.");
        }
        productService.updateProduct(product);
        return "redirect:/products";
    }

    @GetMapping("/delete/{id}")
    public String deleteProduct(@PathVariable("id") Long id) {
        Product product = productService.getProductById(id);
        if (product == null) {
            throw new ProductNotFoundException(id);
        }
        productService.deleteProduct(id);
        return "redirect:/products";
    }
}
