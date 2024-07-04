package gift.DTO;

import gift.domain.UserEntity;

public class User {
    String email;
    String password;

    public User(){

    }

    public User(String email, String password){
        this.email = email;
        this.password = password;
    }

    public User(UserEntity userEntity){
        this.email = userEntity.getEmail();
        this.password = userEntity.getPassword();
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
