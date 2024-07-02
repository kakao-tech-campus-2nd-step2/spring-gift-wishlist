package gift;

import java.sql.Types;
import java.util.List;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.stereotype.Repository;

@Repository
public class ProductDao {

    private final JdbcClient jdbcClient;

    public ProductDao(JdbcClient jdbcClient) {
        this.jdbcClient = jdbcClient;
    }

    /**
     * 새 상품을 DB에 저장
     *
     * @return 반영된 row의 개수
     */
    public Integer insertNewProduct(Product newProduct) {
        String sql = """
                INSERT INTO product (id, name, price, imageUrl)
                VALUES (:id, :name, :price, :imageUrl);
            """;
        return jdbcClient.sql(sql)
            .param("id", newProduct.id(), Types.BIGINT)
            .param("name", newProduct.name(), Types.VARCHAR)
            .param("price", newProduct.price(), Types.BIGINT)
            .param("imageUrl", newProduct.imageUrl(), Types.VARCHAR)
            .update();
    }

    /**
     * 상품 전체를 반환
     *
     * @return 상품 객체가 담긴 List
     */
    public List<Product> selectProducts() {
        String sql = "SELECT * FROM product;";
        return jdbcClient.sql(sql)
            .query((rs, rowNum) -> new Product(
                rs.getLong("id"),
                rs.getString("name"),
                rs.getLong("price"),
                rs.getString("imageUrl")
            ))
            .list();
    }

    /**
     * 전달받은 id에 해당하는 상품을 반환
     *
     * @return 상품 객체
     */
    public Product selectOneProduct(Long id) {
        String sql = "SELECT * FROM product WHERE id = :id;";
        return jdbcClient.sql(sql)
            .param("id", id)
            .query((rs, rowNum) -> new Product(
                rs.getLong("id"),
                rs.getString("name"),
                rs.getLong("price"),
                rs.getString("imageUrl")
            ))
            .single();
    }

    /**
     * 전달 받은 상품으로 기존 상품을 수정
     *
     * @return 반영된 row의 개수
     */
    public Integer updateProduct(Product editedProduct) {
        String sql = """
            UPDATE product
            SET id = :id, name = :name, price = :price, imageUrl = :imageUrl
            WHERE id = :id;
            """;
        return jdbcClient.sql(sql)
            .param("id", editedProduct.id(), Types.BIGINT)
            .param("name", editedProduct.name(), Types.VARCHAR)
            .param("price", editedProduct.price(), Types.BIGINT)
            .param("imageUrl", editedProduct.imageUrl(), Types.VARCHAR)
            .update();
    }

    /**
     * id에 해당하는 상품 삭제
     *
     * @return 반영된 row의 개수
     */
    public Integer deleteProduct(Long id) {
        String sql = "DELETE FROM product WHERE id = :id";
        return jdbcClient.sql(sql)
            .param("id", id)
            .update();
    }
}