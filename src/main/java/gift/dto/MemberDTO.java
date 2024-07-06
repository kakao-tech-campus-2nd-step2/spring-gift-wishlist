package gift.dto;

import gift.model.MemberRole;

public class MemberDTO {

    private Long id;
    private String email;
    private String password;
    private MemberRole role;

    public MemberDTO(String email, String password, MemberRole role) {
        this.email = email;
        this.password = password;
        this.role = role;
        if (this.role == null) {
            this.role = MemberRole.COMMON_MEMBER;
        }
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public MemberRole getRole() {
        return role;
    }

    public void setRole(MemberRole role) {
        this.role = role;
    }
}
