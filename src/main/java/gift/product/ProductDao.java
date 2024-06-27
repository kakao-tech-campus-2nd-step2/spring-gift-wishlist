package gift.product;

import gift.product.model.GetProductRes;
import gift.product.model.PatchProductReq;
import gift.product.model.PostProductReq;
import java.util.List;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class ProductDao {
    private final JdbcTemplate jdbcTemplate;

    public ProductDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public GetProductRes findProduct(Long id) {
        var sql = "SELECT id, name, price, image_url FROM product WHERE id = ?";
        return jdbcTemplate.queryForObject(
                sql,
                (rs, rowNum) -> new GetProductRes(
                        rs.getLong("id"),
                        rs.getString("name"),
                        rs.getInt("price"),
                        rs.getString("image_url")
                ),
                id
        );
    }

    public List<GetProductRes> findAllProduct() {
        var sql = "SELECT id, name, price, image_url FROM product";
        return jdbcTemplate.query(
                sql,
                (rs, rowNum) -> new GetProductRes(
                        rs.getLong("id"),
                        rs.getString("name"),
                        rs.getInt("price"),
                        rs.getString("image_url")
                )
        );
    }


    public Long addProduct(PostProductReq postProductReq) {
        var sql = "INSERT INTO product (name, price, image_url) VALUES (?, ?, ?)";
        Object[] params = new Object[]{postProductReq.getName(), postProductReq.getPrice(),
                postProductReq.getImageUrl()};
        if (jdbcTemplate.update(sql, params) == 1) {
            return jdbcTemplate.queryForObject("SELECT SCOPE_IDENTITY()", Long.class);
        }
        return -1L;
    }

    public int updateProduct(PatchProductReq updatedProduct) {
        var sql = "UPDATE product SET name = ?, price = ?, image_url = ? WHERE id = ?";
        return jdbcTemplate.update(
                sql,
                updatedProduct.getName(),
                updatedProduct.getPrice(),
                updatedProduct.getImageUrl(),
                updatedProduct.getId()
        );
    }

    public int deleteProduct(Long id) {
        var sql = "UPDATE product SET isActive = false WHERE id = ?";
        return jdbcTemplate.update(sql);
    }
}
