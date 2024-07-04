package gift.entity;

import gift.dto.UserDTO;


public class User {

    private String email;
    private String password;

    User() {
    }

    public User(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public User(UserDTO userDTO) {
        this.email = userDTO.email();
        this.password = userDTO.password();
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
