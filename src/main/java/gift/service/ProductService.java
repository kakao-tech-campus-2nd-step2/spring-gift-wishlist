package gift.service;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import gift.dao.ProductDao;
import gift.domain.Product;
import gift.dto.ProductDto;
import gift.exception.InvalidProductException;

@Service
public class ProductService{

    private ProductDao productDao;

    public ProductService(ProductDao productDao){
        this.productDao = productDao;
    }

    public List<ProductDto> findAll(){
        List<Product> productList = productDao.findAll();
        return productList.stream()
        .map(ProductDto::fromEntity)
        .collect(Collectors.toList());
    }

    public ProductDto findById(Long id){
        Product product = productDao.findOne(id)
            .orElseThrow(() -> new InvalidProductException("Product with id " + id + " not found"));
        return product.toDto(product);
    }

    public void addProduct(ProductDto productDto){

        if(productDao.findOne(productDto.getId()).isEmpty()){
            Product product = productDto.toEntity();
            productDao.insertProduct(product); 
        }else{
            throw new InvalidProductException("Product with id " + productDto.getId() + "exists");
        }
        
    }

    public void updateProduct(ProductDto productDto){
        productDao.findOne(productDto.getId())
            .orElseThrow(() -> new InvalidProductException("Product with id " + productDto.getId() + " not found"));
        Product product = productDto.toEntity();
        productDao.updateProduct(product);
    }

    public void deleteProduct(Long id){
        productDao.deleteProduct(id);
    }
}