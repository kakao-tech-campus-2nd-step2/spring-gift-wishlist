package gift.dto;

import gift.domain.User;

public class UserDto {
    private long id;
    private String name;
    private String password;
    private String email;

    public UserDto() {
    }

    public UserDto(long id, String name, String password, String email) {
        this.id = id;
        this.name = name;
        this.password = password;
        this.email = email;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public User toEntity(UserDto userDto){
        return new User(this.id, this.name, this.password, this.email);
    }
}
