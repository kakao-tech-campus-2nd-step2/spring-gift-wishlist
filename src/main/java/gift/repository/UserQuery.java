package gift.repository;

public enum UserQuery {
    SELECT_ALL_USER("select email, password from users"),
    SELECT_USER_BY_EMAIL("select email, password from users where email=?"),
    INSERT_USER("insert into users (email, password) values (?, ?)"),
    UPDATE_USER_BY_EMAIL("update users set email=?, password=? where email=?"),
    DELETE_USER_BY_EMAIL("delete from users where email=?");
    private final String query;

    UserQuery(String query) {
        this.query = query;
    }

    public String getQuery() {
        return query;
    }
}
