package gift.controller;


import gift.exception.KakaoValidationException;
import gift.exception.StringValidationException

import jakarta.validation.Valid;


@RestController
@RequestMapping("/api/products")
public class ProductController {
  private final ProductService productService;

  public ProductController(ProductService productService) {
    this.productService = productService;
  }

  @GetMapping
  public ResponseEntity<List<Product>> getAllProducts() {
    return ResponseEntity.ok(productService.findAll());
  }

  @GetMapping("/{id}")
  public ResponseEntity<Product> getProductById(@PathVariable Long id) {
    return productService.findById(id)
            .map(ResponseEntity::ok)
            .orElseGet(() -> ResponseEntity.notFound().build());
  }

  @PostMapping
  public ResponseEntity<Product> createProduct(@Valid @RequestBody Product product) {

  @PutMapping("/{id}")
  public ResponseEntity<Product> updateProduct(@PathVariable Long id, @Valid @RequestBody Product product) {
    if (!productService.findById(id).isPresent()) {
      return ResponseEntity.notFound().build();
    }
    product.setId(id);
    return ResponseEntity.ok(productService.save(product));
  }
  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
    if (!productService.findById(id).isPresent()) {
      return ResponseEntity.notFound().build();
    }
    productService.deleteById(id);
    return ResponseEntity.noContent().build();
  }
}
