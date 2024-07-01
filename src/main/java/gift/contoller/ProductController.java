package gift.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import gift.domain.Product;
import gift.dto.ProductRequestDto;
import gift.dto.ProductResponseDto;
import gift.service.ProductService;
import java.util.List;

@RestController
public class ProductController {

	@Autowired
	private ProductService productService;


	@PostMapping("/api/v1/product")
	@ResponseBody
	public ResponseEntity<?> registerProduct(@RequestBody ProductRequestDto productRequestDto) {
		productService.saveProduct(productRequestDto);
		return ResponseEntity.ok().build();
	}

	@GetMapping("/api/v1/product/{id}")
	@ResponseBody
	public ResponseEntity<ProductResponseDto> getProduct(@PathVariable Long id) {
		Product product = productService.getProductById(id);
		return ResponseEntity.ok(ProductResponseDto.from(product));
	}

	@PutMapping("/api/v1/product/{id}")
	@ResponseBody
	public ResponseEntity<?> updateProduct(@PathVariable Long id, @RequestBody ProductRequestDto productRequestDto) {
		productService.updateProduct(id, productRequestDto);
		return ResponseEntity.ok().build();
	}

	@DeleteMapping("/api/v1/product/{id}")
	@ResponseBody
	public ResponseEntity<?> deleteProduct(@PathVariable Long id) {
		productService.deleteProduct(id);
		return ResponseEntity.ok().build();
	}
}