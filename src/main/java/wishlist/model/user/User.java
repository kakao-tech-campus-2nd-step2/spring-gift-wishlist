package wishlist.model.user;

public class User {
    private String Email;
    private String PassWord;

    public User(String email,String passWord) {
        PassWord = passWord;
        Email = email;
    }

    public String getPassWord() {
        return PassWord;
    }

    public String getEmail() {
        return Email;
    }
}