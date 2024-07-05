package gift.repository;

public interface WishListRepository extends JpaRepository<WishList, Long> {
    WishList findByUserId(Long userId);
}