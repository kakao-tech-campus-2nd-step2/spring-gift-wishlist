package gift.dto;

public class WishDto {
    private Long id;
    private Long productId;
    private String token;

    public WishDto(Long id, Long productId, String token) {
        this.id= id;
        this.productId = productId;
        this.token= token;
    }

    public Long getId(){ return id; }
    public Long getProductId() { return productId; }
    public String getToken() { return token; }


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
