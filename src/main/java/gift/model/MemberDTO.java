package gift.model;

public class MemberDTO {

    private String password;
    private String email;

    public MemberDTO() {
    }

    public MemberDTO(String pw, String email) {
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

