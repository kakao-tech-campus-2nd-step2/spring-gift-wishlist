package gift.dto;

public class Member {

    private String email;
    private String password;

    /**
     * MockMvc 테스트를 위해 선언
     */
    public Member() {
    }

    public Member(String email, String password) {
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
