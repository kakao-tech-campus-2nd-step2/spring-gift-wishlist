package gift.Service;

import gift.DTO.ProductDTO;
import gift.Global.Exception.BusinessException;
import gift.Global.Response.ErrorCode;
import gift.Model.Product;
import gift.Global.Validation.Validation;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

@Service
public class ProductService {

    private final JdbcTemplate jdbcTemplate; // h2 DB 사용한 메모리 저장 방식
    private final Validation validation; // 유효성 검증

    @Autowired
    public ProductService(JdbcTemplate jdbcTemplate, Validation validation) {
        this.jdbcTemplate = jdbcTemplate;
        this.validation = validation;
    }

    /**
     * 상품 추가
     *
     * @param productDTO
     */
    public void postProduct(ProductDTO productDTO) {
        System.out.println("여기까지 올 수 있나?");

        validation.validateProduct(productDTO);

        String sql = "INSERT INTO product (name, price, image_url) VALUES (?, ?, ?)";

        int rowNum = jdbcTemplate.update(sql, productDTO.getName(), productDTO.getPrice(),
            productDTO.getImageUrl());

        if (rowNum == 0) {
            throw new BusinessException(ErrorCode.CREATE_PRODUCT_FAILED);
        }
    }

    /**
     * 전체 싱픔 목록 조회
     *
     * @return 전체 상품 목록
     */
    public List<Product> getProducts() {
        String sql = "SELECT * FROM product ORDER BY ID ASC";

        List<Product> products = jdbcTemplate.query(sql,
            BeanPropertyRowMapper.newInstance(Product.class));

        if (products == null) {
            throw new BusinessException(ErrorCode.GET_ALL_PRODUCTS_FAILED);
        }
        return products;
    }

    /**
     * 상품 수정
     *
     * @param id
     * @param productDTO
     */
    public void updateProduct(Long id, ProductDTO productDTO) {
        validation.validateProduct(productDTO);

        String sql = "UPDATE product SET name = ?, price = ?, image_url = ? WHERE id = ?";

        int rowNum = jdbcTemplate.update(sql, productDTO.getName(), productDTO.getPrice(),
            productDTO.getImageUrl(), id);
        if (rowNum == 0) {
            throw new BusinessException(ErrorCode.UPDATE_PRODUCT_FAILED);
        }
    }


    /**
     * 상품 삭제
     *
     * @param id
     */
    public void deleteProduct(Long id) {
        String sql = "DELETE FROM product WHERE id = ?";
        int rowNum = jdbcTemplate.update(sql, id);
        if (rowNum == 0) {
            throw new BusinessException(ErrorCode.DELETE_PRODUCT_FAILED);
        }
    }

    /**
     * 해당 ID 리스트에 속한 상품들 삭제
     *
     * @param productIds
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
            throw new BusinessException(ErrorCode.DELETE_PRODUCTS_FAILED);
        }
    }
}
