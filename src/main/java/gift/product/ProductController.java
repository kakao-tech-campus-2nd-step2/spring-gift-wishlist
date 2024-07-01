package gift.product;

import java.util.List;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class ProductController {

    private final ProductRepository productRepository;

    private static final Integer NO_OF_ROWS_AFFECTED = 1;

    public ProductController(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @GetMapping("/products")
    public List<ProductResDto> getProducts() {
        List<Product> productList = productRepository.findProducts();

        return productList.stream()
                .map(product -> new ProductResDto(
                        product.getId(),
                        product.getName(),
                        product.getPrice(),
                        product.getImageUrl()
                )).toList();
    }

    @GetMapping("/products/{productId}")
    public ProductResDto getProduct(@PathVariable Long productId) {
        Product product = getProductById(productId);

        return new ProductResDto(
                product.getId(),
                product.getName(),
                product.getPrice(),
                product.getImageUrl()
        );
    }

    @PostMapping("/products")
    public ProductResDto addProduct(@RequestBody ProductReqDto productReqDto) {
        Long productId = productRepository.addProduct(productReqDto);

        // 저장된 상품 가져오기
        Product newProduct = productRepository.findProductById(productId);

        return new ProductResDto(
                newProduct.getId(),
                newProduct.getName(),
                newProduct.getPrice(),
                newProduct.getImageUrl()
        );
    }

    @PutMapping("/products/{productId}")
    public Boolean updateProduct(@PathVariable Long productId, @RequestBody ProductReqDto productReqDto) {
        Integer noOfRowsAffected = productRepository.updateProduct(productId, productReqDto);

        return NO_OF_ROWS_AFFECTED.equals(noOfRowsAffected); // 수정된 행의 개수 반환 - 1이면 성공, 0이면 실패
    }

    @DeleteMapping("/products/{productId}")
    public Boolean deleteProduct(@PathVariable Long productId) {
        Integer noOfRowsAffected = productRepository.deleteProduct(productId);
        return NO_OF_ROWS_AFFECTED.equals(noOfRowsAffected); // 삭제된 행의 개수 반환 - 1이면 성공, 0이면 실패
    }

    private Product getProductById(Long productId) {
        Product product = productRepository.findProductById(productId);

        if (product == null) {
            throw new IllegalArgumentException("Product not found");
        }

        return product;
    }
}
