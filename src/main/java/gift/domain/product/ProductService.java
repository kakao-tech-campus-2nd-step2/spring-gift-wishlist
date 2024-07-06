package gift.domain.product;

import gift.global.exception.BusinessException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

@Service
public class ProductService {

    private final JdbcTemplate jdbcTemplate; // h2 DB 사용한 메모리 저장 방식
    private final ProductRepository productRepository;

    @Autowired
    public ProductService(JdbcTemplate jdbcTemplate, JdbcTemplateProductRepository jdbcTemplateProductRepository) {
        this.jdbcTemplate = jdbcTemplate;
        this.productRepository = jdbcTemplateProductRepository;
    }

    /**
     * 상품 추가
     */
    public void createProduct(ProductDTO productDTO) {
        if (productRepository.existsByProductName(productDTO.getName())) {
            throw new BusinessException(HttpStatus.BAD_REQUEST, "해당 이름의 상품이 이미 존재합니다.");
        }

        productRepository.createProduct(productDTO);
    }

    /**
     * 전체 싱픔 목록 조회
     */
    public List<Product> getProducts() {
        List<Product> products = productRepository.getProducts();

        return products;
    }

    /**
     * 상품 수정
     */
    public void updateProduct(Long id, ProductDTO productDTO) {
        if (productRepository.existsByProductName(productDTO.getName())) {
            throw new BusinessException(HttpStatus.BAD_REQUEST, "해당 이름의 상품이 이미 존재합니다.");
        }

        productRepository.updateProduct(id, productDTO);
    }


    /**
     * 상품 삭제
     */
    public void deleteProduct(Long id) {
        productRepository.deleteProduct(id);
    }

    /**
     * 해당 ID 리스트에 속한 상품들 삭제
     */
    public void deleteProductsByIds(List<Long> productIds) {
        if (productIds.isEmpty()) {
            throw new BusinessException(HttpStatus.BAD_REQUEST, "삭제할 상품을 선택하세요.");
        }

        productRepository.deleteProductByIds(productIds);
    }

    /**
     * 장바구니에 상품 ID 추가
     */
    public void addProductToCart(Long userId, Long productId) {
        if(productRepository.isExistsInCart(userId, productId)){
            throw new BusinessException(HttpStatus.BAD_REQUEST, "해당 상품이 장바구니에 이미 존재합니다.");
        }

        productRepository.addProductToCart(userId, productId);
    }

    /**
     * 장바구니 조회
     */
    public List<Product> getProductsInCartByUserId(Long userId) {
        List<Product> products = productRepository.getProductsInCartByUserId(userId);

        return products;
    }

    /**
     * 장바구니에서 상품 삭제
     */
    public void deleteProductInCart(Long userId, Long productId) {
        productRepository.deleteProductInCart(userId, productId);
    }
}


