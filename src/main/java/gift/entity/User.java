package gift.entity;

public class User {
    private static int id = 1;

    private String email;

    private String userPw;

    public int getId() {
        return id;
    }

    public String getUserId() {
        return email;
    }

    public String getUserPw() {
        return userPw;
    }

    public User(String email, String userPw) {
        this.id = id++;
        this.email = email;
        this.userPw = userPw;
    }
}
