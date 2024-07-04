package gift.repository;

import gift.dto.ProductDto;
import gift.entity.ProductEntity;
import gift.exception.ValidationException;
import java.util.List;
import java.util.NoSuchElementException;
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
        ProductEntity productEntity = new ProductEntity(productDto);

        // 이미 존재하는 id에 넣으려고 하는지 검증
        long id = productDto.getId();
        verifyProductAlreadyExist(id);

        var sql = """
            insert into productDto (id, name, price, image) values (?, ?, ?, ?)
            """;

        jdbcTemplate.update(sql, productEntity.getId(), productEntity.getName(), productEntity.getPrice(), productEntity.getImage());
    }

    // Read 처리 메서드
    public List<ProductDto> selectProducts() {
        var sql = """
            select id, name, price, image
            from productDto;
            """;

        return jdbcTemplate.query(sql, (resultSet, rowNum) -> new ProductDto(
            resultSet.getLong("id"),
            resultSet.getString("name"),
            resultSet.getInt("price"),
            resultSet.getString("image")
        ));
    }

    // Update 처리 메서드
    public void updateProduct(ProductDto productDto) {
        ProductEntity productEntity = new ProductEntity(productDto);

        var sql = """
            update productDto set name = ?, price = ?, image = ? where id = ?;
            """;

        jdbcTemplate.update(sql, productEntity.getName(), productEntity.getPrice(), productEntity.getImage(), productEntity.getId());
    }

    // Delete 처리 메서드
    public void deleteProduct(long id) {
        // 우선 해당하는 id가 있는지부터 검사
        verifyProductExist(id);

        var sql = """
            delete from productDto where id = ?;
            """;

        jdbcTemplate.update(sql, id);
    }

    // 모든 데이터 Delete 처리 메서드
    public void deleteProducts() {
        var sql = """
            delete from productDto;
            """;

        jdbcTemplate.execute(sql);
    }

    // db에서 특정 id를 갖는 로우를 반환
    public ProductDto selectProduct(long id) {
        // 존재하는 id를 불러올 수 있도록 검증
        verifyProductExist(id);

        var sql = """
            select id, name, price, image
            from productDto
            where id = ?;
            """;

        return jdbcTemplate.queryForObject(sql, (resultSet, rowNum) -> new ProductDto(
            resultSet.getLong("id"),
            resultSet.getString("name"),
            resultSet.getInt("price"),
            resultSet.getString("image")
        ), id);
    }

    // db에서 특정 id를 갖는 로우가 있는지 확인하는 메서드
    private boolean exists(long id) {
        var sql = """
            select id
            from productDto
            where id = ?;
            """;

        // 결과의 로우가 존재하는지 반환
        boolean isEmpty = jdbcTemplate.query(sql, (resultSet, rowNum) -> resultSet.getInt("id"), id).isEmpty();

        return !isEmpty;
    }

    // 검증: 삽입 시에는 id가 중복되지 않아야 한다.
    private void verifyProductAlreadyExist(long id) {
        if (exists(id)) {
            // 의미있는 메시지를 위해 id를 함께 제공
            throw new ValidationException("id "+ id + "이/가 중복됩니다.");
        }
    }

    // 검증: 조회 시에는 id가 존재해야 한다.
    private void verifyProductExist(long id) {
        if (!exists(id)) {
            // 의미있는 메시지를 위해 id를 함께 제공
            throw new NoSuchElementException("id " + id + "을/를 가진 제품이 존재하지 않습니다.");
        }
    }
}
