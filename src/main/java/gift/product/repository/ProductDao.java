package gift.product.repository;

import gift.product.dto.ProductDto;
import gift.product.entity.ProductEntity;
import java.util.List;
import java.util.NoSuchElementException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class ProductDao {

    private final JdbcTemplate jdbcTemplate;

    public ProductDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    // Create 처리 메서드
    public void insertProduct(ProductDto productDto) {
        ProductEntity productEntity = new ProductEntity(productDto.id(), productDto.name(),
            productDto.price(), productDto.image(), productDto.md());

        // 이미 존재하는 id에 넣으려고 하는지 검증
        long id = productDto.id();
        verifyProductAlreadyExist(id);

        var sql = """
            insert into products (id, name, price, image, md) values (?, ?, ?, ?, ?)
            """;

        jdbcTemplate.update(sql, productEntity.getId(), productEntity.getName(),
            productEntity.getPrice(), productEntity.getImage(), productEntity.getMd());
    }

    // Read 처리 메서드
    public List<ProductEntity> selectProducts() {
        var sql = """
            select id, name, price, image, md
            from products;
            """;

        return jdbcTemplate.query(sql, (resultSet, rowNum) -> new ProductEntity(
            resultSet.getLong("id"),
            resultSet.getString("name"),
            resultSet.getInt("price"),
            resultSet.getString("image"),
            resultSet.getBoolean("md")
        ));
    }

    // Update 처리 메서드
    public void updateProduct(ProductDto productDto) {
        ProductEntity productEntity = new ProductEntity(productDto.id(), productDto.name(),
            productDto.price(), productDto.image(), productDto.md());

        var sql = """
            update products set name = ?, price = ?, image = ?, md = ? where id = ?;
            """;

        jdbcTemplate.update(sql, productEntity.getName(), productEntity.getPrice(),
            productEntity.getImage(), productEntity.getMd(), productEntity.getId());
    }

    // Delete 처리 메서드
    public void deleteProduct(long id) {
        // 우선 해당하는 id가 있는지부터 검사
        verifyProductExist(id);

        var sql = """
            delete from products where id = ?;
            """;

        jdbcTemplate.update(sql, id);
    }

    // 모든 데이터 Delete 처리 메서드
    public void deleteProducts() {
        var sql = """
            delete from products;
            """;

        jdbcTemplate.execute(sql);
    }

    // db에서 특정 id를 갖는 로우를 반환
    public ProductEntity selectProduct(long id) {
        // 존재하는 id를 불러올 수 있도록 검증
        verifyProductExist(id);

        var sql = """
            select id, name, price, image, md
            from products
            where id = ?;
            """;

        return jdbcTemplate.queryForObject(sql, (resultSet, rowNum) -> new ProductEntity(
            resultSet.getLong("id"),
            resultSet.getString("name"),
            resultSet.getInt("price"),
            resultSet.getString("image"),
            resultSet.getBoolean("md")
        ), id);
    }

    // db에서 특정 id를 갖는 로우가 있는지 확인하는 메서드
    private boolean exists(long id) {
        var sql = """
            select id
            from products
            where id = ?;
            """;

        // 결과의 로우가 존재하는지 반환
        boolean isEmpty = jdbcTemplate.query(sql, (resultSet, rowNum) -> resultSet.getInt("id"), id)
            .isEmpty();

        return !isEmpty;
    }

    // 논리적 검증: 삽입 시에는 id가 중복되지 않아야 한다.
    private void verifyProductAlreadyExist(long id) {
        if (exists(id)) {
            // 의미있는 메시지를 위해 id를 함께 제공
            throw new IllegalArgumentException("id가 중복됩니다. (id: " + id + ")");
        }
    }

    // 논리적 검증: 조회 시에는 id가 존재해야 한다.
    private void verifyProductExist(long id) {
        if (!exists(id)) {
            // 의미있는 메시지를 위해 id를 함께 제공
            throw new NoSuchElementException("해당 id를 가진 제품이 존재하지 않습니다. (id: " + id + ")");
        }
    }
}
