package gift.dto;

public class UserDto {
    private Long id;
    private String userEmail;
    private String userPassword;

    public UserDto() {
    }

    public UserDto(String userEmail, String userPassword) {
        this.userEmail = userEmail;
        this.userPassword = userPassword;
    }

    public UserDto(long id, String userEmail, String userPassword) {
        this.id = id;
        this.userEmail = userEmail;
        this.userPassword = userPassword;
    }

    public Long getId() {
        return id;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public static class Request {
        private Long id;
        private String userEmail;
        private String userPassword;

        public Request(Long id, String userEmail, String userPassword) {
            this.id = id;
            this.userEmail = userEmail;
            this.userPassword = userPassword;
        }

        public Long getId() {
            return id;
        }

        public String getUserEmail() {
            return userEmail;
        }

        public String getUserPassword() {
            return userPassword;
        }
    }

    public static class Response {
        private Long id;
        private String userEmail;
        private String userPassword;

        public Response(Long id, String userEmail, String userPassword) {
            this.id = id;
            this.userEmail = userEmail;
            this.userPassword = userPassword;
        }

        public Long getId() {
            return id;
        }

        public String getUserEmail() {
            return userEmail;
        }

        public String getUserPassword() {
            return userPassword;
        }
    }
}
