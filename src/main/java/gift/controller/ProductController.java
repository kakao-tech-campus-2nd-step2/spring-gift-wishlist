package gift.controller;

import gift.model.Product;
import gift.model.ProductDao;
import gift.model.ProductRequest;
import gift.model.ProductResponse;
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
@RequestMapping("/api/v1")
public class ProductController {

    private ProductDao productDao;

    ProductController(ProductDao productDao) {
        this.productDao = productDao;
    }

    @PostMapping("/product")
    public ProductResponse registerProduct(@RequestBody ProductRequest productRequest) {
        Product product = productDao.save(productRequest);
        return ProductResponse.from(product);
    }

    @GetMapping("/products")
    public List<ProductResponse> getAllProducts() {
        List<Product> productList = productDao.findAll();
        return productList.stream().map(ProductResponse::from).collect(Collectors.toList());
    }

    @GetMapping("/product/{id}")
    public ProductResponse getProduct(@PathVariable("id") Long id) {
        Product product = productDao.findById(id);
        return ProductResponse.from(product);
    }

    @PutMapping("/product/{id}")
    public ProductResponse updateProduct(@PathVariable("id") Long id, @RequestBody ProductRequest productRequest) {
        Product product = productDao.update(id, productRequest);
        return ProductResponse.from(product);
    }

    @DeleteMapping("/product/{id}")
    public void deleteProduct(@PathVariable("id") Long id) {
        productDao.delete(id);
    }
}

