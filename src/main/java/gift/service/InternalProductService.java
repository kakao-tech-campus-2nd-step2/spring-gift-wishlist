package gift.service;

import gift.domain.Product;
import gift.exception.ProductNotFoundException;
import gift.repository.ProductRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class InternalProductService {
    private final ProductRepository productRepository;

    // @Qualifier 어노테이션의 값은 빈 이름과 일치해야 한다.
    // spring이 빈을 생성할 때 클래스 이름을 사용하여 소문자로 시작한다.
    @Autowired
    public InternalProductService(@Qualifier("internalProductRepositoryImpl") ProductRepository productRepository){
        this.productRepository = productRepository;
    }

    public Product addProduct (Product product){
        return productRepository.addProduct(product);
    }

    public List<Product> findAll(){
        return productRepository.findAll();
    }

    public Product findById(Long id) throws ProductNotFoundException {
        return productRepository.findById(id);
    }

    public Product updateProduct(Long id,Product product) throws ProductNotFoundException{
        return productRepository.updateProduct(id,product);
    }

    public void deleteProduct(Long id) throws ProductNotFoundException{
        productRepository.deleteProduct(id);
    }
}
