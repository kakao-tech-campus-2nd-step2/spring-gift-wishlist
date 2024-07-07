package gift.model;

import gift.dto.GetProductRes;
import gift.dto.PatchProductReq;
import gift.dto.PostProductReq;
import gift.exception.ProductNotFoundException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;

@Repository
public class ProductDao {
    private final JdbcTemplate jdbcTemplate;

    public ProductDao(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }

    public GetProductRes addProduct(PostProductReq postProductReq) {
        var sql = "INSERT INTO product (name, price, image_url) VALUES (?, ?, ?)";
        Object[] params = new Object[]{postProductReq.getName(), postProductReq.getPrice(), postProductReq.getImageUrl()};
        jdbcTemplate.update(sql, params);

        var selectSql = "SELECT id, name, price, image_url FROM product WHERE name = ? AND price = ? AND image_url = ? ORDER BY id DESC LIMIT 1";
        return jdbcTemplate.queryForObject(selectSql, new Object[]{postProductReq.getName(), postProductReq.getPrice(), postProductReq.getImageUrl()},
            (rs, rowNum) -> new GetProductRes(
                rs.getLong("id"),
                rs.getString("name"),
                rs.getInt("price"),
                rs.getString("image_url")
            ));
    }

    public List<GetProductRes> findAll() {
        var sql = "SELECT id, name, price, image_url FROM product WHERE is_active = true";
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

    public GetProductRes findById(Long id) throws ProductNotFoundException {
        var sql = "SELECT id, name, price, image_url FROM product WHERE id = ? AND is_active = true";
        try {
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
        } catch (Exception e) {
            throw new ProductNotFoundException("Product not found");
        }
    }

    public GetProductRes updateProduct(Long id, PatchProductReq updateProductReq) throws ProductNotFoundException {
        var sql = "UPDATE product SET name = ?, price = ?, image_url = ? WHERE id = ?";
        int rows = jdbcTemplate.update(
            sql,
            updateProductReq.getName(),
            updateProductReq.getPrice(),
            updateProductReq.getImageUrl(),
            id
        );

        if (rows == 0) {
            throw new ProductNotFoundException("Product not found");
        }

        return findById(id);
    }

    public void deleteProduct(Long id) throws ProductNotFoundException {
        var sql = "UPDATE product SET is_active = false WHERE id = ?";
        int rows = jdbcTemplate.update(sql, id);

        if (rows == 0) {
            throw new ProductNotFoundException("Product not found");
        }
    }

}
