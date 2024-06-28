package gift.product.model;

import gift.product.model.dto.GetProductRes;
import gift.product.model.dto.PatchProductReq;
import gift.product.model.dto.PostProductReq;
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
        var sql = "SELECT id, name, price, image_url FROM product WHERE id = ? AND is_active = true";
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
        var sql = "SELECT id, name, price, image_url FROM product where is_active = true";
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


    public int addProduct(PostProductReq postProductReq) {
        var sql = "INSERT INTO product (name, price, image_url) VALUES (?, ?, ?)";
        Object[] params = new Object[]{postProductReq.getName(), postProductReq.getPrice(),
                postProductReq.getImageUrl()};
        return jdbcTemplate.update(sql, params);
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
        var sql = "UPDATE product SET is_active = false WHERE id = ?";
        return jdbcTemplate.update(sql, id);
    }
}
