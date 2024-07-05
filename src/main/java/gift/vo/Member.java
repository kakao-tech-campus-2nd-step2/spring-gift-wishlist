package gift.vo;

public class Member {
    private String email;
    private String password;
    private MemberRole role;  // @ToDo MemberRole 확인

    public Member(String email, String password) {
        this(email, password, null);
    }

    public Member(String email, String password, MemberRole role) {
        this.email = email;
        this.password = password;
        this.role = role;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public MemberRole getRole() {
        return role;
    }
}
