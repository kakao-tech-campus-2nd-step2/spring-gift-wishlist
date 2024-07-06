package gift.repository;

public enum UserQuery {
    SELECT_ALL_USER("select id, email, password, name, role from users"),
    SELECT_USER_BY_EMAIL("select id, email, password, name, role from users where email=?"),
    SELECT_USER_BY_ID("select id, email, password, name, role from users where id=?"),
    INSERT_USER("insert into users (email, password, name, role) values (?, ?, ?, ?)"),
    UPDATE_USER_BY_EMAIL("update users set email=?, password=?, name=?, role=? where email=?"),
    DELETE_USER_BY_EMAIL("delete from users where email=?");
    private final String query;

    UserQuery(String query) {
        this.query = query;
    }

    public String getQuery() {
        return query;
    }
}
