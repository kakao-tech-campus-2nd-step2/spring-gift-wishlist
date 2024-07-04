package gift.Model;

import jakarta.validation.constraints.Email;

public class Member {
    private String password;
    private String email;

    public Member(){
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
