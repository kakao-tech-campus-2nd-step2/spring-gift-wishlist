package gift.vo;

public class Member {
    private String email;
    private String password;
    private MemberRole role;

    public Member(String email, String password) {
        this(email, password, String.valueOf(MemberRole.USER));
    }

    public Member(String email, String password, String role) {
        this.email = email;
        this.password = password;
        this.role = MemberRole.valueOf(role);
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
