package gift.application;

import gift.domain.Product;
import gift.infra.ProductRepository;
import gift.presentation.EmployeeManageController.CreateEmployeeRequestDTO;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;


    public void addProduct(CreateEmployeeRequestDTO createEmployeeRequestDTO) {
        //TODO: Validation
        Product employee = new Product(createEmployeeRequestDTO.name(), createEmployeeRequestDTO.price(),
            createEmployeeRequestDTO.imageUrl());
        productRepository.addProduct(employee);
    }

    public void deleteProduct(Long id) {
        productRepository.deleteProduct(id);
    }

    public void updateProduct(Long id, String name, Double price, String imageUrl) {
        Product employee = new Product(name, price, imageUrl);
        productRepository.updateProduct(id, employee);
    }

    public Product getProductByName(Long id) {
        return productRepository.getProductById(id);
    }

    public List<Product> getProduct() {
        return productRepository.getProducts();
    }

}
