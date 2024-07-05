package gift.entity;

public class User {

    private static int id_cnt = 1;
    private int id;
    private String email;

    private String userPw;

    public int getId() {
        return id;
    }


    public String getUserEmail() {
        return email;

    }

    public String getUserPw() {
        return userPw;
    }


    public User(String email, String userPw) {
        this.id = id_cnt++;
        this.email = email;
        this.userPw = userPw;
    }
    
    public User(int id, String email, String userPw){
        this.id = id;
        this.email = email;

        this.userPw = userPw;
    }
}
