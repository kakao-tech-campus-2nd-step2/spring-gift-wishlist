package wishlist.model.user;

import jakarta.validation.constraints.NotBlank;

public class UserDTO {

    @NotBlank
    private String Email;
    @NotBlank
    private String PassWord;

    public UserDTO(String email, String passWord) {
        PassWord = passWord;
        Email = email;
    }

    public String getPassWord() {
        return PassWord;
    }

    public String getEmail() {
        return Email;
    }

    public void setPassWord(String passWord) {
        PassWord = passWord;
    }

    public void setEmail(String email) {
        Email = email;
    }
}