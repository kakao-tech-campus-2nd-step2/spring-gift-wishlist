package gift.controller;

import gift.controller.dto.ProductDTO;
import gift.domain.Product;
import gift.service.GiftService;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/products")
public class GiftController {

    private final GiftService giftService;

    public GiftController(GiftService giftService) {
        this.giftService = giftService;
    }


    @GetMapping
    public ResponseEntity<List<Product>> getAllProduct() {
        List<Product> allProduct = giftService.getAllProduct();
        return ResponseEntity.ok(allProduct);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable Long id) {
        Product product = giftService.getProduct(id);
        return ResponseEntity.ok(product);
    }

    @PostMapping
    public ResponseEntity<String> postProduct(@RequestBody ProductDTO productDTO) {
        String s = giftService.postProducts(productDTO);
        return new ResponseEntity<>(s, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateProduct(@PathVariable Long id,
        @RequestBody ProductDTO productDTO) {
        String s = giftService.putProducts(productDTO, id);
        return ResponseEntity.ok(s);
    }

    @PostMapping("/{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable Long id) {
        String s = giftService.deleteProducts(id);
        return ResponseEntity.ok(s);
    }
}
