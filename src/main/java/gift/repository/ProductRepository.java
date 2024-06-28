package gift.repository;

import gift.converter.StringToUrlConverter;
import gift.domain.Product;
import gift.domain.dto.ProductUpdateParam;
import java.util.List;
import java.util.Optional;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class ProductRepository {

    private final JdbcTemplate jdbcTemplate;
    private final StringToUrlConverter stringToUrlConverter;
    public ProductRepository(JdbcTemplate jdbcTemplate, StringToUrlConverter stringToUrlConverter) {
        this.jdbcTemplate = jdbcTemplate;
        this.stringToUrlConverter = stringToUrlConverter;
    }

    public Long save(Product product) {
        String sql = "insert into product (name, price, image_url) values (?, ?, ?)";
        jdbcTemplate.update(sql,
            product.getName(), product.getPrice(), product.getImageUrl().toString());
        return product.getId(); //todo 반환값 수정(Product를 식별할 수 있는 값으로)
    }

    public Optional<Product> findById(Long id) {
        return Optional.ofNullable(jdbcTemplate.queryForObject("select id, name, price, image_url from product where id = ?",
            (rs, rowNum) -> new Product.Builder()
                .id(rs.getLong("id"))
                .name(rs.getString("name"))
                .price(rs.getInt("price"))
                .imageUrl(stringToUrlConverter.convert(rs.getString("image_url")))
                .build(), id));
    }

    public List<Product> findAll() {
        return jdbcTemplate.query("select id, name, price, image_url from product",
            (rs, rowNum) -> new Product.Builder()
                .id(rs.getLong("id"))
                .name(rs.getString("name"))
                .price(rs.getInt("price"))
                .imageUrl(stringToUrlConverter.convert(rs.getString("image_url")))
                .build());
    }

    public boolean deleteById(Long id) {
        if (existsById(id)) {
            jdbcTemplate.update("delete from product where id = ?", id);
            return true;
        }
        return false;
    }

    public int update(Long id, ProductUpdateParam productUpdateParam) {
        String sql = "update product set name = ?, price = ?, image_url = ? where id = ?";
        return jdbcTemplate.update(sql, productUpdateParam.getName(), productUpdateParam.getPrice(),
            productUpdateParam.getImageUrl().toString(), id);
    }

    public boolean existsById(Long id) {
        String sql = "select count(*) from product where id = ?";
        Integer count = jdbcTemplate.queryForObject(sql, Integer.class, id);
        return count != null && count > 0;
    }

}
