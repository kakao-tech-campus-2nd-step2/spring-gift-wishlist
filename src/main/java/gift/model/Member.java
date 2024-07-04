package gift.model;

public class Member {

    private String password;
    private String email;

    public Member() {
    }

    public Member(String pw, String email) {
        this.password = pw;
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
