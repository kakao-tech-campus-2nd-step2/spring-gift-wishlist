package gift.domain.product;

import gift.dto.ProductRequestDto;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.NoSuchElementException;


@Repository
@Primary
public class ProductJdbcRepository implements ProductRepository {
    private final JdbcTemplate jdbcTemplate;

    public ProductJdbcRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void save(Product product) {
        String sql = "INSERT INTO product(name, price, img_url) VALUES(?, ?, ?)";
        jdbcTemplate.update(sql, product.getName(), product.getPrice(), product.getImgUrl());
    }

    @Override
    public ArrayList<Product> findAll() {
        String sql = "SELECT * FROM product";
        return (ArrayList<Product>) jdbcTemplate.query(sql, (rs, rowNum) -> {
            Long id = rs.getLong("id");
            String name = rs.getString("name");
            int price = rs.getInt("price");
            String imgUrl = rs.getString("img_url");
            return new Product(id, name, price, imgUrl);
        });
    }

    @Override
    public Product findById(Long id) {
        if (isNotValidProductId(id)){
            throw new NoSuchElementException("유효하지 않은 id입니다.");
        }
        String sql = "SELECT * FROM product where id = ?";
        return jdbcTemplate.queryForObject(sql, (rs, rowNum) -> {
            String name = rs.getString("name");
            int price = rs.getInt("price");
            String imgUrl = rs.getString("img_url");
            return new Product(id, name, price, imgUrl);
        }, id);
    }

    @Override
    public void deleteById(Long id) {
        if (isNotValidProductId(id)){
            throw new NoSuchElementException("유효하지 않은 id입니다.");
        }
        String sql = "DELETE FROM product where id = ?";
        jdbcTemplate.update(sql, id);
    }

    @Override
    public int update(Long id, ProductRequestDto requestDto) {
        if (isNotValidProductId(id)){
            throw new NoSuchElementException("유효하지 않은 id입니다.");
        }
        String sql = "UPDATE product set name = ?, price = ?, img_url = ? where id = ?";
        return jdbcTemplate.update(sql,
                requestDto.getName(),
                requestDto.getPrice(),
                requestDto.getImgUrl(),
                id);
    }
  
    private boolean isNotValidProductId(Long id){
        String sql = "SELECT * FROM product WHERE id=?";
        return jdbcTemplate.query(sql, (rs,rowNum)-> 0, id).isEmpty();
    }
}
