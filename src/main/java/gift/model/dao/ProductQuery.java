package gift.model.dao;

public enum ProductQuery {
    CREATE_PRODUCT_TABLE("""
        CREATE TABLE IF NOT EXISTS products (
            id BIGINT PRIMARY KEY AUTO_INCREMENT,
            name VARCHAR(255) NOT NULL,
            price int NOT NULL,
            image_url VARCHAR(255),
            is_deleted BOOLEAN DEFAULT FALSE
        );
    """),
    INSERT_PRODUCT("""
        INSERT INTO products (name, price, image_url, is_deleted) VALUES (?, ?, ?, ?);
    """),
    SELECT_ALL_PRODUCTS("""
        SELECT * FROM products WHERE is_deleted = FALSE;
    """),
    SELECT_PRODUCT_BY_ID("""
        SELECT * FROM products WHERE id = ? AND is_deleted = FALSE;
    """),
    UPDATE_PRODUCT("""
        UPDATE products SET name = ?, price = ?, image_url = ?, is_deleted = ? WHERE id = ?;
    """),
    DELETE_PRODUCT("""
        DELETE FROM products WHERE id = ?;
    """);

    private final String query;

    ProductQuery(String query) {
        this.query = query;
    }

    public String getQuery() {
        return query;
    }
}
