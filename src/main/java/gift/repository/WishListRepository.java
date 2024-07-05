package gift.repository;

import gift.domain.Product;
import gift.domain.User;
import gift.domain.WishProduct;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class WishListRepository {
    private final JdbcTemplate jdbcTemplate;
    private Long id = 0L;

    public WishListRepository(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }
    /*
     * DB에 저장된 모든 위시리스트 내용을 반환
     */
    public List<WishProduct> findAll(){
        String sql = "select email, productId from wishList";
        List<WishProduct> list = jdbcTemplate.query(
                sql, (resultSet, rowNum) -> {
                    WishProduct wishProduct = new WishProduct(
                            resultSet.getString("email"),
                            resultSet.getLong("productId")
                    );
                    return wishProduct;
                });
        return list;
    }
    /*
     * DB에 위시리스트 내용을 입력받아 저장
     */
    public void save(WishProduct wishProduct){
        var sql = "INSERT INTO wishList(id, email, productId) VALUES (?,?,?)";
        jdbcTemplate.update(sql, ++id, wishProduct.getEmail(), wishProduct.getProductId());
    }
    /*
     * DB에서 email 열을 기준으로 위시리스트를 반환
     */
    public List<WishProduct> findByEmail(String email) {
        String sql = "select email, productId from wishList where email = ?";
        List<WishProduct> list = jdbcTemplate.query(
                sql, new Object[]{email}, (resultSet, rowNum) ->{
                    WishProduct wishProduct = new WishProduct(
                            resultSet.getString("email"),
                            resultSet.getLong("productId")
                    );
                    return wishProduct;
                });
        return list;
    }
    /*
     * DB에서 email과 id 열을 기준으로 위시리스트 내용을 삭제
     */
    public void delete(String email, Long id){
        String sql = "DELETE FROM wishList WHERE email = ? AND productId = ?";
        jdbcTemplate.update(sql, email, id);
    }
}
