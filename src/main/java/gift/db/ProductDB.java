package gift.db;

import gift.dto.Product;

import java.util.List;

public interface ProductDB {

    void addProduct(Product product) throws Exception;

    Product getProduct(Long id);

    List<Product> getProducts();

    void removeProduct(Long id) throws Exception;

    void removeProducts(List<Long> productIds);

    void editProduct(Product product) throws Exception;

}
