package gift.domain.wishlist.dao;

import gift.domain.product.dao.ProductDao;
import gift.domain.product.entity.Product;
import gift.domain.user.dao.UserDao;
import gift.domain.user.entity.User;
import gift.domain.wishlist.dto.WishItemDto;
import gift.domain.wishlist.entity.WishItem;
import java.util.List;
import java.util.Objects;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

@Repository
public class WishlistDao {

    private final JdbcClient jdbcClient;
    private final UserDao userDao;
    private final ProductDao productDao;

    public WishlistDao(JdbcClient jdbcClient, UserDao userDao, ProductDao productDao) {
        this.jdbcClient = jdbcClient;
        this.userDao = userDao;
        this.productDao = productDao;
    }

    public WishItem insert(WishItem wishItem) {
        String sql = "INSERT INTO wishlist (user_id, product_id) VALUES (?, ?)";
        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcClient.sql(sql)
            .param(wishItem.getUserId())
            .param(wishItem.getProductId())
            .update(keyHolder);

        wishItem.setId(Objects.requireNonNull(keyHolder.getKey()).longValue());

        return wishItem;
    }

    public List<WishItem> findAll(User user) {
        String sql = "SELECT * FROM wishlist WHERE user_id = ?";

        return jdbcClient.sql(sql)
            .param(user.getId())
            .query(WishItemDto.class)
            .stream()
            .map(wishItemDto -> {
                Product product = productDao.findById(wishItemDto.productId()).orElse(null);
                return wishItemDto.toWishItem(user, product);
            }).toList();
    }
}
