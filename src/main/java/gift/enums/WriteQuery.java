package gift.enums;

public enum WriteQuery {

    UPDATE("update product set name = ?, price = ?, imageUrl = ? where id = ?"),
    DELETE("delete from product where id = ?");

    private final String query;

    WriteQuery(String query) {
        this.query = query;
    }

    public String getQuery() {
        return query;
    }
}
