package gift.domain.product;

public interface ProductRepository {

    boolean existsByProductName(String name);
}
