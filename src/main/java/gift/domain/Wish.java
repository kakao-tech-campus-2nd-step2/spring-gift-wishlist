package gift.domain;

import jakarta.validation.constraints.NotNull;

public class Wish {
    @NotNull
    private String email;
    @NotNull
    private Long ProductId;

    public Wish(String email, Long productId) {
        this.email = email;
        ProductId = productId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Long getProductId() {
        return ProductId;
    }

    public void setProductId(Long productId) {
        ProductId = productId;
    }

}
