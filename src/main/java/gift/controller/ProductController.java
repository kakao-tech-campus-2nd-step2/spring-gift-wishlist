package gift.controller;

import gift.domain.ProductService;
import gift.dto.ProductDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/products")
public class ProductController {
    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping("/add")
    public void addProduct(@RequestBody ProductDto productDto) {
        productService.save(productDto);
    }

    @PutMapping("/edit/{id}")
    public void editProduct(@PathVariable Long id, @RequestBody ProductDto productDto) {
        productService.update(id, productDto);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteProduct(@PathVariable Long id) {
        productService.deleteById(id);
    }
}