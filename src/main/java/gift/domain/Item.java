package gift.domain;

import java.util.Objects;

public abstract class Item {

    protected final Long id;

    abstract static class Builder<T extends Builder<T>> {

        Long id;

        public T id(Long id) {
            this.id = id;
            return self();
        }

        abstract Item build();

        protected abstract T self();
    }

    protected Item(Builder<?> builder) {
        id = builder.id;
    }

    public Long getId() {
        return id;
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
