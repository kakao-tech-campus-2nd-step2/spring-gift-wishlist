package gift.controller;

import gift.domain.Product;
import gift.dto.requestDTO.ProductRequestDTO;
import gift.dto.responseDTO.ProductListResponseDTO;
import gift.dto.responseDTO.ProductResponseDTO;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Optional;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProductController {
    private final Map<Long, Product> products = new HashMap<>();

    @GetMapping("/api/products")
    public ResponseEntity<ProductListResponseDTO> getAllProducts(){
        List<ProductResponseDTO> productResponseDTOList = products.values()
            .stream()
            .map(ProductResponseDTO::of)
            .toList();

        ProductListResponseDTO productListResponseDTO = new ProductListResponseDTO(productResponseDTOList);
        return ResponseEntity.ok(productListResponseDTO);
    }

    @GetMapping("/api/product/{id}")
    public ResponseEntity<ProductResponseDTO> getOneProduct(@PathVariable("id") Long productId){
        Product product = Optional.ofNullable(products.get(productId))
            .orElseThrow(() -> new NoSuchElementException("id가 잘못되었습니다."));
        return ResponseEntity.ok(ProductResponseDTO.of(product));
    }

}
