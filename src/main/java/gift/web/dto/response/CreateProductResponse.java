package gift.web.dto.response;

import java.util.UUID;

public class CreateProductResponse {

    private UUID id;

    public CreateProductResponse(UUID id) {
        this.id = id;
    }

    public UUID getId() {
        return id;
    }
}
