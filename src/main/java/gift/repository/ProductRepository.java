package gift.repository;

import gift.domain.Product;
import java.util.HashMap;
import java.util.Map;
import org.springframework.stereotype.Repository;

@Repository
public class ProductRepository {

    private final Map<Long, Product> products = new HashMap<>();


}
