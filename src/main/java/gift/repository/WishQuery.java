package gift.repository;

public enum WishQuery {
    SELECT_ALL_WISHES_BY_USERID("select product_name, count from wishes where user_id=?"),
    INSERT_WISH_BY_USERID("insert into wishes (product_name, count, user_id) values (?, ?, ?)"),
    UPDATE_WISH_BY_USERID_PRODUCTNAME("update wishes set count=? where user_id=? and product_name=?"),
    DELETE_WISH_BY_USERID_PRODUCTNAME("delete from wishes where user_id=? and product_name=?");

    private final String query;

    WishQuery(String query) {
        this.query = query;
    }

    public String getQuery() {
        return query;
    }
}
