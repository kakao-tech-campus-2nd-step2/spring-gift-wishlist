package gift.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

public class UserSignUp {

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class Request{
        private String email;
        private String password;
    }

    public static class Response {
        private String accessToken;

        public Response(String accessToken) {
            this.accessToken = accessToken;
        }

        public String getAccessToken() {
            return this.accessToken;
        }

        public void setAccessToken(String accessToken) {
            this.accessToken = accessToken;
        }
    }
}
