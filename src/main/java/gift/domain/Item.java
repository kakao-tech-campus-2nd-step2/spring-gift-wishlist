package gift.domain;

import java.util.Objects;

public abstract class Item {

    protected final Long id;
    protected String name;

    abstract static class Builder<T extends Builder<T>> {

        Long id;
        String name = "";

        public T id(Long id) {
            this.id = id;
            return self();
        }

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

    public Long getId() {
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
