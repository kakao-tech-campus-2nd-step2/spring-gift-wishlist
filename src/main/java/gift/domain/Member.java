package gift.domain;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

import java.util.UUID;

public class Member {

    private Long id;
    private String email;
    private String password;
    private String secretKey;

    public Member() {}

    public Member(Long id, String email, String password, String secretKey) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.secretKey = secretKey;
    }

    public Member(String email, String password) {
        this.email = email;
        this.password = password;
        this.secretKey = generateSecretKey();
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

    public String getSecretKey() {
        return secretKey;
    }

    public void setSecretKey(String secretKey) {
        this.secretKey = secretKey;
    }

    private String generateSecretKey() {
        return UUID.randomUUID().toString();
    }

}
