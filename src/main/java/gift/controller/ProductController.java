package gift.controller;

import gift.Product;
import gift.ProductRepository;
import gift.ProductDto;
import java.util.List;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProductController {
    private final ProductRepository productRepository;

    public ProductController(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }
    // 모든 상품 Read
    @GetMapping("/api/products")
    public List<Product> readAll(){
        return productRepository.findAll();
    }

    // 특정 상품 Read
    @GetMapping("/api/products/{id}")
    public Product read(@PathVariable("id") Long id){
        return productRepository.findById(id);
    }

    // 새 상품 Create
    @PostMapping("/api/products")
    public Product create(@RequestBody ProductDto productDto){
        productRepository.save(productDto);
        return productRepository.findByName(productDto.getName());
    }

    @PutMapping("/api/products/{id}")
    public Product update(@PathVariable("id") Long id, @RequestBody ProductDto productDto){
        productRepository.update(id, productDto);

        return productRepository.findById(id);
    }

    @DeleteMapping("/api/products/{id}")
    public Product delete(@PathVariable("id") Long id){
        productRepository.delete(id);

        return null;
    }
}
