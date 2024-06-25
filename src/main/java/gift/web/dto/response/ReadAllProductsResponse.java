package gift.web.dto.response;

import gift.domain.Product;
import java.util.List;

public class ReadAllProductsResponse {

    private List<Product> products;

    public ReadAllProductsResponse(List<Product> products) {
        this.products = products;
    }

    public List<Product> getProducts() {
        return products;
    }
}
