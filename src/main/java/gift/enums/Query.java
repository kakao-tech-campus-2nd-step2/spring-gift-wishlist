package gift.enums;

public enum Query {

    FIND_BY_ID("select id, name, price, imageUrl from product where id = ?"),
    FIND_ALL("select id, name, price, imageUrl from product"),
    UPDATE("update product set name = ?, price = ?, imageUrl = ? where id = ?"),
    DELETE("delete from product where id = ?");

    private final String query;

    Query(String query) {
        this.query = query;
    }

    public String getQuery() {
        return query;
    }
}
