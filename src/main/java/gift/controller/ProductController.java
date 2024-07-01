package gift.controller;

import gift.controller.dto.ProductRequestDto;
import gift.controller.dto.ProductResponseDto;
import gift.model.Product;
import gift.model.ProductDao;
import jakarta.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    private final ProductDao productDao;

    @Autowired
    public ProductController(ProductDao productDao) {
        this.productDao = productDao;
    }

    @GetMapping
    public List<ProductResponseDto> getAllProducts() {
        return productDao.selectAllProduct()
            .stream()
            .map(Product::toProductResponseDto)
            .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public ProductResponseDto getProduct(@PathVariable("id") Long id) {
        return productDao.selectProductById(id).toProductResponseDto();
    }

    @PostMapping
    public void addProduct(@Valid @RequestBody ProductRequestDto productRequestDto) {
        productDao.insertProduct(productRequestDto.toEntity());
    }

    @PutMapping("/{id}")
    public void updateProduct(@Valid @RequestBody ProductRequestDto productRequestDto,
        @PathVariable("id") Long id) {
        productDao.updateProductById(id, productRequestDto.toEntity());
    }

    @DeleteMapping("/{id}")
    public void deleteProduct(@PathVariable("id") Long id) {
        productDao.deleteProductById(id);
    }

}
