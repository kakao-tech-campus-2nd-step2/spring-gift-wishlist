package gift.model;

public class Member {

    private Long id;
    private String email;
    private String password;
    private MemberRole role;

    public Member(String email, String password) {
        this.email = email;
        this.password = password;
        this.role = MemberRole.MEMBER;
    }

    public Member(Long id, String email, String password, MemberRole role) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.role = role;
    }

    public Long getId() {
        return id;
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

    public void updatePassword(String password) {
        this.password = password;
    }

    public void updateRole(MemberRole role) {
        this.role = role;
    }
}
