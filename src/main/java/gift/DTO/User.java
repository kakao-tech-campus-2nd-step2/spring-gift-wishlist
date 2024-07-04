package gift.DTO;

public class User {

    private String email;
    private String password;

    // 기본 생성자
    public User() {
    }

    // 모든 필드를 초기화하는 생성자
    public User(String email, String password) {
        this.email = email;
        this.password = password;
    }

    // Getters and setters

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

}
