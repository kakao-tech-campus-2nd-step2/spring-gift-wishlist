package gift.web.dto.response;

public class CreateProductResponse {

    private Long id;

    public CreateProductResponse(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
