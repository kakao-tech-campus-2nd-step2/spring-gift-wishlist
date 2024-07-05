package gift.domain;

public class WishList {
    Long id;
    String memberId;
    Long menuId;

    public WishList(Long id, String memberId, Long menuId){
        this.id = id;
        this.memberId = memberId;
        this.menuId = menuId;
    }
}
