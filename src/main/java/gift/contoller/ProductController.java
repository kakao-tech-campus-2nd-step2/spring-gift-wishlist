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
import jakarta.validation.Valid;

import java.util.List;

@RestController
public class ProductController {

	private final ProductService productService;


	@PostMapping("/api/v1/product")
	@ResponseBody
	public ResponseEntity<?> registerProduct(@RequestBody @Valid ProductRequestDto productRequestDto) {
		productService.saveProduct(productRequestDto);
		// TODO: 등록된 상품 ID를 같이 반환한다.
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
	public ResponseEntity<?> updateProduct(@PathVariable Long id, @RequestBody @Valid ProductRequestDto productRequestDto) {
		productService.updateProduct(id, productRequestDto);
		// TODO: 수정된 상품 ID를 같이 반환한다.
		return ResponseEntity.ok().build();
	}

	@DeleteMapping("/api/v1/product/{id}")
	@ResponseBody
	public ResponseEntity<?> deleteProduct(@PathVariable Long id) {
		productService.deleteProduct(id);
		// TODO: 삭제된 상품 ID를 같이 반환한다.
		return ResponseEntity.ok().build();
	}

	@Autowired
	public ProductController(ProductService productService) {
		this.productService = productService;
	}
}