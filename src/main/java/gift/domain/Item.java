package gift.domain;

import java.util.Objects;
import java.util.UUID;

public abstract class Item {

    protected final UUID id;
    protected String name;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Item item = (Item) o;
        return Objects.equals(id, item.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
