package gift.domain;

public class User {
    private String email;
    private String password;
    private String role = Role.USER.name();

    public User(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }
    public String getRole() {
        return role;
    }

    public void updateRole(Role role){
        this.role = role.name();
    }
}
