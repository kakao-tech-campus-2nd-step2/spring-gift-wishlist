package gift.domain.product;

import gift.domain.user.UserInfo;
import gift.global.exception.BusinessException;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class ProductService {

    private final JdbcTemplate jdbcTemplate; // h2 DB 사용한 메모리 저장 방식
    private final gift.global.validation.validator validator; // 유효성 검증

    @Autowired
    public ProductService(JdbcTemplate jdbcTemplate, gift.global.validation.validator validator) {
        this.jdbcTemplate = jdbcTemplate;
        this.validator = validator;
    }

    /**
     * 상품 추가
     */
    public void createProduct(ProductDTO productDTO) {
        validator.validateDuplicateProduct(productDTO.getName());

        String sql = "INSERT INTO product (name, price, image_url) VALUES (?, ?, ?)";

        int rowNum = jdbcTemplate.update(sql, productDTO.getName(), productDTO.getPrice(),
            productDTO.getImageUrl());

        if (rowNum == 0) {
            throw new BusinessException(HttpStatus.BAD_REQUEST, "상품 추가에 실패했습니다.");
        }
    }

    /**
     * 전체 싱픔 목록 조회
     */
    public List<Product> getProducts() {
        String sql = "SELECT * FROM product ORDER BY ID ASC";

        List<Product> products = jdbcTemplate.query(sql,
            BeanPropertyRowMapper.newInstance(Product.class));

        return products;
    }

    /**
     * 상품 수정
     */
    public void updateProduct(Long id, ProductDTO productDTO) {
        validator.validateDuplicateProduct(productDTO.getName());

        String sql = "UPDATE product SET name = ?, price = ?, image_url = ? WHERE id = ?";

        int rowNum = jdbcTemplate.update(sql, productDTO.getName(), productDTO.getPrice(),
            productDTO.getImageUrl(), id);
        if (rowNum == 0) {
            throw new BusinessException(HttpStatus.NOT_FOUND, "상품 수정에 실패했습니다.");
        }
    }


    /**
     * 상품 삭제
     */
    public void deleteProduct(Long id) {
        String sql = "DELETE FROM product WHERE id = ?";
        int rowNum = jdbcTemplate.update(sql, id);
        if (rowNum == 0) {
            throw new BusinessException(HttpStatus.NOT_FOUND, "상품 삭제에 실패했습니다.");
        }
    }

    /**
     * 해당 ID 리스트에 속한 상품들 삭제
     */
    public void deleteProductsByIds(List<Long> productIds) {
        int rowNum = 0;
        String sql = "DELETE FROM product WHERE id = ?";
        for (Long productId : productIds) {
            int update = jdbcTemplate.update(sql, productId);
            rowNum += update;
        }
        // 모두 삭제가 이루어지지 않은 경우
        if (rowNum != productIds.size()) {
            throw new BusinessException(HttpStatus.NOT_FOUND, "선택된 상품들 삭제에 실패했습니다.");
        }
    }

    /**
     * 장바구니에 상품 ID 추가
     */
    public void addProductToCart(Long userId, Long productId) {
        if(isExistsInCart(userId, productId)){
            throw new BusinessException(HttpStatus.BAD_REQUEST, "해당 상품이 장바구니에 이미 존재합니다.");
        }

        String sql = "INSERT INTO cart (user_id, product_id) VALUES (?, ?)";

        int rowNum = jdbcTemplate.update(sql, userId, productId);

        if(rowNum != 1){
            throw new BusinessException(HttpStatus.BAD_REQUEST, "장바구니 담기에 실패했습니다.");
        }
    }

    /**
     * 장바구니 조회
     */
    public List<Product> getProductsInCartByUserId(Long userId) {
        String sql = "SELECT * FROM product WHERE id IN (SELECT product_id FROM cart WHERE user_id = ?)";

        List<Product> products = jdbcTemplate.query(sql,
            BeanPropertyRowMapper.newInstance(Product.class), userId);

        log.info("products = {}", products);

        return products;
    }

    /**
     * 장바구니 상품 존재 여부 확인
     */
    public boolean isExistsInCart(Long userId, Long productId) {
        String sql = "SELECT CASE WHEN EXISTS ("
                     + "    SELECT 1 "
                     + "    FROM cart "
                     + "    WHERE user_id = ? AND product_id = ?"
                     + ") THEN TRUE ELSE FALSE END AS product_exists";

        Boolean result = jdbcTemplate.queryForObject(sql, Boolean.class, userId, productId);
        return result;
    }
}
