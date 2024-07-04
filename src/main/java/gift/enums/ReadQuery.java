package gift.enums;

public enum ReadQuery {

    FIND_BY_ID("select id, name, price, imageUrl from product where id = ?"),
    FIND_ALL("select id, name, price, imageUrl from product"),
    FIND_BY_EMAIL("select id, password, email from users where email = ?");

    private final String query;

    ReadQuery(String query) {
        this.query = query;
    }

    public String getQuery() {
        return query;
    }
}
