package gift;

import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Repository;

@Repository
public class ProductRepository {

    public ProductRepository() {
    }

    private List<Product> products = new ArrayList<>();


    public List<Product> getProducts() {
        return products;
    }

    public Product getProductByName(String name) {
        for (Product product : products) {
            if (product.getName().equals(name)) {
                return product;
            }
        }
        return null;
    }

    public Product getProductById(Long id) {
        for (Product product : products) {
            if (product.getId().equals(id)) {
                return product;
            }
        }
        return null;
    }

    public void addProduct(Product product) {
        products.add(product);
    }

    public void deleteProduct(String name) {
        products.removeIf(product -> product.getName().equals(name));
    }

    public void updateProduct(String name, Product product) {
        for (int i = 0; i < products.size(); i++) {
            if (products.get(i).getName().equals(name)) {
                products.set(i, product);
            }
        }
    }

}
