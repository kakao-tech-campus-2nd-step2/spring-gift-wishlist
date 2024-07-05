package gift.wishlist;

public class WishList {

    private long id;
    private String email;
    private String name;
    private int num;

    public WishList(){}

    public WishList(long id, String email, String name, int num) {
        this.id=id;
        this.email=email;
        this.name=name;
        this.num=num;
    }

    public long getId(){
        return id;
    }

    public void setId(long id){
        this.id=id;
    }

    public String getEmail(){
        return email;
    }

    public void setEmail(String email){
        this.email=email;
    }

    public String getName(){
        return name;
    }

    public void setName(String name){
        this.name=name;
    }

    public int getNum(){
        return num;
    }

    public void setName(int num){
        this.num=num;
    }
}
