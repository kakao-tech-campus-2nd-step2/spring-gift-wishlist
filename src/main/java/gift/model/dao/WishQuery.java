package gift.model.dao;

public class WishQuery {
    public static final String INSERT_WISH = "INSERT INTO wishes (user_id, product_id, amount) VALUES (?, ?, ?)";
    public static final String UPDATE_WISH = "UPDATE wishes SET user_id = ?, product_id = ?, amount = ? WHERE user_id = ? AND product_id = ?";
}
