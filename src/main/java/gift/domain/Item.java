package gift.domain;

import java.util.UUID;

public abstract class Item {

    private final UUID id;
    private String name;

    abstract static class Builder<T extends Builder<T>> {

        UUID id = UUID.randomUUID();
        String name = "";

        public T name(String name) {
            this.name = name;
            return self();
        }

        abstract Item build();

        protected abstract T self();
    }

    protected Item(Builder<?> builder) {
        id = builder.id;
        name = builder.name;
    }

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
