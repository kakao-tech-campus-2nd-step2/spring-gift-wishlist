package gift.service;

import gift.DB.ProductDB;
import gift.DTO.ProductDTO;
import gift.domain.Product;
import gift.domain.Product.ProductSimple;
import gift.mapper.ProductMapper;
import gift.repository.ProductRepository;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class ProductService {

    @Autowired
    private ProductDB productDB;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private ProductMapper productMapper;

    public List<ProductDTO> getProductList() {
        return productRepository.getList();
    }

    public List<ProductSimple> getSimpleProductList() {
        List<ProductSimple> list = new ArrayList<>();

        for (ProductDTO p : productRepository.getList()) {
            list.add(new ProductSimple(p.getId(), p.getName()));
        }

        return list;
    }

    public ProductDTO getProduct(Long id) {
        if (!productRepository.validateId(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "아이디가 존재하지 않습니다.");
        }
        return productRepository.getProduct(id);
    }

    public void createProduct(Product.CreateProduct create) {
        productRepository.setProduct(create);
    }

    public void updateProduct(Product.UpdateProduct update, Long id) {
        if (!productRepository.validateId(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "아이디가 존재하지 않습니다.");
        }
        productRepository.updateProduct(id,update);
    }

    public void deleteProduct(Long id) {
        if (!productRepository.validateId(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "아이디가 존재하지 않습니다.");
        }
        productRepository.removeProduct(id);
    }

}
