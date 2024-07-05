package gift.service;
import org.springframework.stereotype.Service;

import java.util.List;

import gift.dao.ProductDao;
import gift.domain.Product;
import gift.dto.ProductDto;
import gift.exception.ProductNotFoundException;

@Service
public class ProductService{

    private ProductDao productDao;

    public ProductService(ProductDao productDao){
        this.productDao = productDao;
    }

    public List<Product> findAll(){
        List<Product> productList = productDao.findAll();
        return productList;
    }

    public void findById(Long id){
        productDao.findOne(id)
            .orElseThrow(() -> new ProductNotFoundException("Product with id " + id + " not found"));
    }

    public void addProduct(ProductDto productDto){
        findById(productDto.getId());
        Product product = productDto.toEntity();
        productDao.insertProduct(product);
    }

    public void updateProduct(ProductDto productDto){
        Product product = productDto.toEntity();
        productDao.updateProduct(product);
    }

    public void deleteProduct(Long id){
        productDao.deleteProduct(id);
    }
}