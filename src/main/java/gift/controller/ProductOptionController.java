package gift.controller;

import gift.dto.ProductOptionRequest;
import gift.dto.ProductOptionResponse;
import gift.dto.ProductResponse;
import gift.model.ProductOption;
import gift.service.ProductOptionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/options")
public class ProductOptionController {

    private final ProductOptionService service;

    public ProductOptionController(ProductOptionService service) {
        this.service = service;
    }

    @PostMapping("/add")
    public ResponseEntity<Void> addOption(@RequestBody ProductOptionRequest productOptionRequest) {
        var productOption = service.addOption(productOptionRequest);
        return ResponseEntity.created(URI.create("/api/options/" + productOption.getId())).build();
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<ProductOptionResponse> updateOption(@PathVariable Long id, @RequestBody ProductOptionRequest productOptionRequest) {
        var productOption = service.updateOption(id, productOptionRequest);
        return ResponseEntity.ok(ProductOptionResponse.from(productOption));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductOptionResponse> getOption(@PathVariable Long id) {
        var productOption = service.getOption(id);
        return ResponseEntity.ok(ProductOptionResponse.from(productOption));
    }

    @GetMapping
    public ResponseEntity<List<ProductOptionResponse>> getOptions(@RequestParam Long productId) {
        var options = service.getOptions(productId);
        return ResponseEntity.ok(options.stream().map(ProductOptionResponse::from).toList());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
        service.deleteOption(id);
        return ResponseEntity.noContent().build();
    }
}
