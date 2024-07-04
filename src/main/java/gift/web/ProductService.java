package gift.web;

import gift.web.dto.Product;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;
import org.springframework.stereotype.Service;

@Service
public class ProductService {
    private final ProductDAO productDAO;

    public ProductService(ProductDAO productDAO) {
        this.productDAO = productDAO;
        productDAO.createProductTable();
    }
    private final AtomicLong incrementCounter = new AtomicLong(1); // ID를 관리할 변수

    public List<Product> getProducts() {
        return List.copyOf(productDAO.selectAllProducts());
    }

    public Product getProductById(Long id) {
        return productDAO.selectProductById(id);
    }

    public Product createProduct(Product product) {
        Long id = incrementCounter.getAndIncrement(); // 1씩 증가하는 id
        Product newProduct = new Product(id, product.name(), product.price(), product.imageUrl());
        productDAO.insertProduct(newProduct);

        return newProduct;
    }

    public Product updateProduct(Long id, Product product) {
        Product newProduct = new Product(product.id(), product.name(), product.price(), product.imageUrl());
        productDAO.updateProduct(newProduct);
        return newProduct;
    }

    public boolean deleteProduct(Long id) {
        return productDAO.deleteProductById(id) != null;
    }
}
