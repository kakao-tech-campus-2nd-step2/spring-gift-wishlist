package gift.dto;

public class WishDto {

    public WishDto(int anInt, int anInt1, String string) {
    }

    public static class Request {
        private Long id;
        private Long productId;
        private String token;

        public Request(Long id, Long productId, String token) {
            this.id = id;
            this.productId = productId;
            this.token = token;
        }

        public Long getId() {
            return id;
        }

        public Long getProductId() {
            return productId;
        }

        public String getToken() {
            return token;
        }
    }
}
