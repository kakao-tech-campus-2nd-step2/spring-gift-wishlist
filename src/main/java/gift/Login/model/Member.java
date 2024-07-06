package gift.Login.model;

import java.util.UUID;

public class Member {
    private UUID id;
    private String email;
    private String password;

    // constructor
    public Member() {
    }

    public Member(String email, String password) {
        this.email = email;
        this.password = password;
    }

    // getters and setters
    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
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
}
