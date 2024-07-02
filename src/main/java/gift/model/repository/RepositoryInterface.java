package gift.model.repository;

import java.util.List;
import java.util.Optional;

public interface RepositoryInterface<T, ID> {
    void save(final T entity);
    void update(final T entity);
    Optional<T> find(final ID id);
    void delete(final T entity);
    List<T> findAll();
}
