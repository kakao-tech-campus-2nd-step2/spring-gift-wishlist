package gift.controller;

import gift.controller.dto.ProductRequest;
import gift.controller.dto.ProductResponse;
import gift.common.exception.InvalidWordException;
import gift.model.Product;
import gift.model.ProductDao;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ProductController {
    private final ProductDao productDao;

    public ProductController(ProductDao productDao) {
        this.productDao = productDao;
    }

    @GetMapping("/products")
    public ResponseEntity<List<ProductResponse>> getProducts() {
        List<Product> products = productDao.findAll();
        List<ProductResponse> responses = products.stream().map(
                ProductResponse::from
        ).toList();
        return ResponseEntity.ok().body(responses);
    }

    @GetMapping("/product/{id}")
    public ResponseEntity<ProductResponse> getProduct(@PathVariable("id")
                                                      @NotNull(message = "ID cannot be null")
                                                      Long id) {
        Product product = productDao.findById(id);
        ProductResponse response = ProductResponse.from(product);
        return ResponseEntity.ok().body(response);
    }

    @PostMapping("/product")
    public ResponseEntity<Long> createProduct(@Valid @RequestBody ProductRequest request, UriComponentsBuilder uriBuilder) {
//        if (request.name().contains("카카오")) {
//            throw new InvalidWordException("\"카카오\"가 포함된 문구는 담당 MD와 협의한 경우에만 사용할 수 있습니다.");
//        }
        Long id = productDao.save(request);
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(uriBuilder.path("/api/product/{id}").buildAndExpand(id).toUri());
        return ResponseEntity.status(HttpStatus.CREATED).headers(headers).body(id);
    }

    @PutMapping("/product/{id}")
    public ResponseEntity<Long> updateProduct(@PathVariable("id") @NotNull @Min(1) Long id,
                                              @RequestBody ProductRequest request) {
//        if (request.name().contains("카카오")) {
//            throw new InvalidWordException("\"카카오\"가 포함된 문구는 담당 MD와 협의한 경우에만 사용할 수 있습니다.");
//        }
        productDao.updateById(id, request);
        return ResponseEntity.ok().body(id);
    }

    @DeleteMapping("/product/{id}")
    public ResponseEntity<Long> deleteProduct(@PathVariable("id") @NotNull @Min(1) Long id) {
        productDao.deleteById(id);
        return ResponseEntity.ok().body(id);
    }

}
