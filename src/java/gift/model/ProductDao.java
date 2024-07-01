package gift.model;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 상품 데이터에 대한 데이터베이스 접근 객체(DAO)임. 이 클래스는 상품의 CRUD 연산을 처리함.
 */
@Repository
public class ProductDao implements InitializingBean {

    private final JdbcTemplate jdbcTemplate;

    /**
     * ProductDao의 생성자임.
     *
     * @param jdbcTemplate 데이터베이스 연산을 위한 JdbcTemplate 객체
     */
    public ProductDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    /**
     * 빈 초기화 시 실행되는 메서드임. 필요한 테이블이 없는 경우 생성함.
     *
     * @throws Exception 테이블 생성 중 발생할 수 있는 예외
     */
    @Override
    public void afterPropertiesSet() throws Exception {
        jdbcTemplate.execute("CREATE TABLE IF NOT EXISTS product (" +
            "id BIGINT AUTO_INCREMENT PRIMARY KEY, " +
            "name VARCHAR(255) NOT NULL, " +
            "image_url TEXT NOT NULL," +
            "price BIGINT NOT NULL)");
    }

    private RowMapper<Product> productRowMapper = (rs, rowNum) ->
        new Product(rs.getLong("id"), rs.getString("name"),
            rs.getLong("price"), rs.getString("image_url"));

    /**
     * 새로운 상품을 생성함.
     *
     * @param product 생성할 상품 정보
     * @return 생성된 상품 객체
     */
    public Product createProduct(Product product) {
        String sql = "INSERT INTO product (name, price, image_url) VALUES (?, ?, ?)";
        jdbcTemplate.update(sql, product.name(), product.price(), product.imageUrl());
        return product;
    }

    /**
     * 지정된 ID의 상품을 조회함.
     *
     * @param id 조회할 상품의 ID
     * @return 조회된 상품 객체
     */
    public Product getProduct(Long id) {
        String sql = "SELECT * FROM product WHERE id = ?";
        return jdbcTemplate.queryForObject(sql, productRowMapper, id);
    }

    /**
     * 모든 상품을 조회함.
     *
     * @return 모든 상품 객체의 리스트
     */
    public List<Product> getAllProducts() {
        String sql = "SELECT * FROM product";
        return jdbcTemplate.query(sql, productRowMapper);
    }

    /**
     * 지정된 ID의 상품을 삭제함.
     *
     * @param id 삭제할 상품의 ID
     */
    public void deleteProduct(Long id) {
        String sql = "DELETE FROM product WHERE id = ?";
        jdbcTemplate.update(sql, id);
    }

    /**
     * 지정된 ID의 상품을 업데이트함.
     *
     * @param id      업데이트할 상품의 ID
     * @param product 업데이트할 상품 정보
     * @return 업데이트된 상품 객체
     */
    public Product updateProduct(Long id, Product product) {
        String sql = "UPDATE product SET name = ?, price = ?, image_url = ? WHERE id = ?";
        jdbcTemplate.update(sql, product.name(), product.price(), product.imageUrl(), id);
        return getProduct(id);
    }
}