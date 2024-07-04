package gift.model;

import java.time.LocalDateTime;

public class Wish {
    private Long id;
    private Long memberId;
    private Product product;
    private LocalDateTime createAt;
    private LocalDateTime updateAt;

    public Wish(Long id, Long memberId, Product product, LocalDateTime createAt, LocalDateTime updateAt) {
        this.id = id;
        this.memberId = memberId;
        this.product = product;
        this.createAt = createAt;
        this.updateAt = updateAt;
    }

    public Long getId() {
        return id;
    }

    public Product getProduct() {
        return product;
    }

    public LocalDateTime getCreateAt() {
        return createAt;
    }

    public LocalDateTime getUpdateAt() {
        return updateAt;
    }
}
