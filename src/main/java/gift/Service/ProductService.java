package gift.Service;

import gift.Repository.ProductRepository;
import gift.DTO.ProductDTO;
import gift.Model.Product;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

@Service
public class ProductService {

    private final JdbcTemplate jdbcTemplate; // h2 DB 사용한 메모리 저장 방식

    @Autowired
    public ProductService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    /**
     * 상품 추가
     *
     * @param productDTO
     * @return 추가 성공 시 추가된 상품 정보, 실패 시 실패 메시지
     */
    public String postProduct(ProductDTO productDTO) {
        String sql = "INSERT INTO product (name, price, image_url) VALUES (?, ?, ?)";
        int rowNum = jdbcTemplate.update(sql, productDTO.getName(), productDTO.getPrice(),
            productDTO.getImageUrl());
        if (rowNum == 0) {
            return "상품 추가 과정에서 문제가 발생했습니다.";
        }
        return "상품이 성공적으로 추가되었습니다.";
    }

    /**
     * 상품 목록 전체 조회
     *
     * @return products (상품 목록)
     */
    public List<Product> getProducts() {
        String sql = "SELECT * FROM product ORDER BY id ASC";
        List<Product> products = jdbcTemplate.query(sql,
            BeanPropertyRowMapper.newInstance(Product.class));
        for (Product product : products) {
            System.out.println("product.id = " + product.getId());
        }
        return products;
    }

    /**
     * 상품 내용 수정
     *
     * @param id
     * @param productDTO
     * @return product (수정된 상품 정보)
     */
    public String updateProduct(Long id, ProductDTO productDTO) {
        String sql = "UPDATE product SET name = ?, price = ?, image_url = ? WHERE id = ?";
        // Execute the update query
        int rowNum = jdbcTemplate.update(sql, productDTO.getName(), productDTO.getPrice(),
            productDTO.getImageUrl(), id);
        if (rowNum == 0) {
            return "상품 수정 중 문제가 발생했습니다.";
        }
        return "상품이 수정되었습니다.";
    }


    /**
     * 해당 ID 를 가진 상품 삭제
     *
     * @param id
     * @return product (삭제된 상품 정보)
     */
    public String deleteProduct(Long id) {
        String sql = "DELETE FROM product WHERE id = ?";
        int rowNum = jdbcTemplate.update(sql, id);
        if (rowNum == 0) {
            return "상품이 삭제 과정 중 문제가 발생했습니다.";
        }
        return "상품이 삭제되었습니다.";
    }

    /**
     * 해당 ID 리스트에 속한 상품들 삭제
     *
     * @param productIds
     * @return 성공 여부 메시지
     */
    public String deleteProductsByIds(List<Long> productIds) {
        String sql = "DELETE FROM product WHERE id = ?";
        for (Long productId : productIds) {
            jdbcTemplate.update(sql, productId);
        }
        return "상품들이 성공적으로 제거되었습니다.";
    }
}
