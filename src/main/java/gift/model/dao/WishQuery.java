package gift.model.dao;

public class WishQuery {
    public static final String INSERT_WISH = "INSERT INTO wishes (user_id, product_id, amount) VALUES (?, ?, ?)";
    public static final String UPDATE_WISH = "UPDATE wishes SET amount = ? WHERE id = ?";
    public static final String SELECT_WISH_BY_ID_AND_USER_ID = "SELECT * FROM wishes WHERE id = ? AND user_id = ? where is_deleted = false";
    public static final String SELECT_WISH_BY_USER_ID = "SELECT * FROM wishes WHERE user_id = ? where is_deleted = false";
}
