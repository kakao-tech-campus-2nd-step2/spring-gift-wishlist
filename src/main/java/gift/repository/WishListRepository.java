package gift.repository;

import gift.model.WishList;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface WishListRepository extends JpaRepository<WishList, Long> {
    Optional<WishList> findByUsername(String username);
}
