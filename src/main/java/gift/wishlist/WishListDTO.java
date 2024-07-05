package gift.wishlist;

public class WishListDTO {

    private long id;
    private String email;
    private String name;
    private int num;

    public WishListDTO() {}

    public WishListDTO(long id, String email, String name, int num) {
        this.id = id;
        this.email=email;
        this.name = name;
        this.num = num;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    public WishList toWishList() {
        return new WishList(id, email, name, num);
    }

    public static WishListDTO fromWishList(WishList wishList) {
        return new WishListDTO(wishList.getId(), wishList.getEmail(), wishList.getName(), wishList.getNum());
    }
}
