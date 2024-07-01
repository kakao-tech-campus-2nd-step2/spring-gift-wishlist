package gift.enums;

public enum ReadQuery {

    FIND_BY_ID("select id, name, price, imageUrl from product where id = ?"),
    FIND_ALL("select id, name, price, imageUrl from product");

    private final String query;

    ReadQuery(String query) {
        this.query = query;
    }

    public String getQuery() {
        return query;
    }
}
