package gift;

public class MemberDto {
    private String email;
    private String password;

    public MemberDto(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }
}
