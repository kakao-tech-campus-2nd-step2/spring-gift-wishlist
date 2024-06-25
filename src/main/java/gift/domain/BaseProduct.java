package gift.domain;

public abstract class BaseProduct {

    private Long id;
    private String name;

    protected BaseProduct(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
