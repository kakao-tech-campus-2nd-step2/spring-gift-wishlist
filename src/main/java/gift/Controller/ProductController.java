package gift.Controller;

import gift.Repository.ProductRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import gift.Model.Product;
import gift.Model.RequestProduct;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@Controller
@RequestMapping("/api")
public class ProductController {
    private final ProductRepository productRepository;

    @Autowired
    public ProductController(ProductRepository productRepository) {
        this.productRepository = productRepository;
        productRepository.createProductTable();
    }

    @GetMapping("/products")
    public String getProduct(Model model) {
        List<Product> list = productRepository.selectProduct();
        model.addAttribute("products", list);
        return "products";
    }

    @GetMapping("/products/new")
    public String newProductForm(Model model) {
        model.addAttribute("product", new RequestProduct("", 0, ""));
        return "new-product";
    }

    @PostMapping("/products")
    public String newProduct(@Valid @ModelAttribute RequestProduct requestProduct) {
        Product product = new Product(requestProduct.name(), requestProduct.price(), requestProduct.imageUrl());
        productRepository.insertProduct(product);
        return "redirect:/api/products";
    }

    @GetMapping("/products/edit/{id}")
    public String editProductForm(@PathVariable("id") Long id, Model model) {
        Product product = productRepository.selectProductById(id);
        model.addAttribute("product", new RequestProduct(product.getName(), product.getPrice(), product.getImageUrl()));
        model.addAttribute("id", id);
        return "edit-product";
    }

    @PutMapping("/products/edit/{id}")
    public String updateProduct(@PathVariable("id") Long id, @Valid @ModelAttribute RequestProduct requestProduct) {
        Product product = new Product(id, requestProduct.name(), requestProduct.price(), requestProduct.imageUrl());
        productRepository.updateProduct(id, product);
        return "redirect:/api/products";
    }

    @DeleteMapping("/products/delete/{id}")
    public String deleteProduct(@PathVariable("id") Long id) {
        productRepository.deleteProduct(id);
        return "redirect:/api/products";
    }

}