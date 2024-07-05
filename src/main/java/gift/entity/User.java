package gift.entity;

public class User {
    private static int id = 1;
    private String userId;
    private String userPw;

    public int getId() {
        return id;
    }

    public String getUserId() {
        return userId;
    }

    public String getUserPw() {
        return userPw;
    }

    public User(String userId, String userPw) {
        this.id = id++;
        this.userId = userId;
        this.userPw = userPw;
    }
}
