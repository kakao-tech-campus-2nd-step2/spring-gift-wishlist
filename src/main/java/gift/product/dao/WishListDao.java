package gift.product.dao;

import gift.product.model.Product;
import gift.product.model.WishProduct;
import java.util.List;

import gift.product.model.WishProduct2;
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

    public List<WishProduct2> getAllProducts(String email) {
        System.out.println("[WishListDao] getAllProducts()");
        var sql = """
                    select p.name, p.price, p.imageUrl, w.count
                    from product_list p
                    join wish_list w on p.id = w.productId
                    where w.memberEmail = ?
                  """;
        return jdbcTemplate.query(sql, (resultSet, rowNum) -> new WishProduct2(
            resultSet.getString("name"),
            resultSet.getInt("price"),
            resultSet.getString("imageUrl"),
                resultSet.getInt("count")
        ), email);
    }

    public void registerWishProduct(WishProduct wProduct) {
        System.out.println("[WishListDao] registerWishProduct()");
        var sql = "insert into wish_list (id, productId, count, memberEmail) values (?, ?, ?, ?)";
        System.out.println(wProduct.getId()+" "+wProduct.getProductId()+" "+wProduct.getCount()+" "+wProduct.getMemberEmail());
        jdbcTemplate.update(sql, wProduct.getId(), wProduct.getProductId(), wProduct.getCount(), wProduct.getMemberEmail());
    }

    public void updateCountWishProduct(Long pId, int count, String email) {
        System.out.println("[WishProductDao] updateCountWishProduct()");
        var sql = "update wish_list set count = ? where productId = ? and memberEmail = ?";
        jdbcTemplate.update(sql, count, pId, email);
    }

    public void deleteWishProduct(long pId, String email) {
        System.out.println("[WishProductDao] deleteWishProduct()");
        var sql = "delete from wish_list where productId = ? and memberEmail = ?";
        jdbcTemplate.update(sql, pId, email);
    }

    public boolean existsByPId(Long pId, String email) {
        System.out.println("[WishListDao] existsByPid()");
        var sql = "select count(*) from wish_list where productId = ? and memeberEmail = ?";
        Integer count = jdbcTemplate.queryForObject(sql, Integer.class, pId, email);
        return count != null && count > 0;
    }
}
