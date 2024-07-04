package gift.product.dao;

import gift.product.model.Product;
import gift.product.model.WishProduct;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class WishListDao {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public WishListDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void createWishListTable() {
        System.out.println("[WishListDao] createWishListTable()");
        var sql = """
            create table wish_list (
              id bigint not null,
              productId bigint not null,
              count int not null,
              memberEmail varchar(255) not null,
              primary key (id)
            )
            """;
        jdbcTemplate.execute(sql);
    }

    public List<Product> getAllProducts(String email) {
        System.out.println("[WishListDao] getAllProducts()");
        var sql = """
                    select p.id, p.name, p.price, p.imageUrl, w.count
                    from product_list p
                    join wish_list w on p.id = w.productId
                    where w.memberEmail = ?
                  """;
        return jdbcTemplate.query(sql, (resultSet, rowNum) -> new Product(
            resultSet.getLong("id"),
            resultSet.getString("name"),
            resultSet.getInt("price"),
            resultSet.getString("imageUrl")
        ), email);
    }

    public void registerWishProduct(WishProduct wProduct) {
        System.out.println("[WishListDao] registerWishProduct()");
        var sql = "insert into wish_list (id, productId, count, memberEmail) values (?, ?, ?, ?)";
        System.out.println(wProduct.getId()+" "+wProduct.getProductId()+" "+wProduct.getCount()+" "+wProduct.getMemberEmail());
        jdbcTemplate.update(sql, wProduct.getId(), wProduct.getProductId(), wProduct.getCount(), wProduct.getMemberEmail());
    }

    public void updateCountWishProduct(Long id, int count) {
        System.out.println("[WishProductDao] updateCountWishProduct()");
        var sql = "update wish_list set count = ? where id = ?";
        jdbcTemplate.update(sql, count, id);
    }

    public void deleteWishProduct(long id) {
        System.out.println("[WishProductDao] deleteWishProduct()");
        var sql = "delete from wish_list where id = ?";
        jdbcTemplate.update(sql, id);
    }
}
