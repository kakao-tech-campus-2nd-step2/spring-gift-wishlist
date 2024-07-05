package gift.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import gift.entity.Option;
import gift.entity.Product;
import gift.exception.Exception400;
import gift.exception.Exception401;
import gift.exception.Exception404;
import gift.repository.ProductRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.Arrays;
import java.util.List;

@Service
@Validated
public class ProductService {
    @Autowired
    ProductRepository productRepository;

    public List<Product> getAllProducts() {
        List<Product> products = productRepository.getAllProduct();
        return products;
    }

    public String getJsonAllProducts(){
        ObjectMapper objectMapper = new ObjectMapper();
        List<Product> products = productRepository.getAllProduct();
        String jsonProduct="";
        try {
             jsonProduct = objectMapper.writeValueAsString(products);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return jsonProduct;
    }

    public void saveProduct(Product product) {
        if(product.getOption() == null)
            throw new Exception400("하나의 옵션은 필요합니다.");

        if(isValidProduct(product)){
            productRepository.saveProduct(product);
        }

        List<String> optionList = Arrays.stream(product.getOption().split(",")).toList();
        for(String str : optionList){
            Option option = new Option(product.getId(), str);

            if(isValidOption(option))
                productRepository.saveOption(new Option(option.getId(),str));

        }
    }
    private boolean isValidProduct(@Valid Product product){
        if(product.getName().contentEquals("카카오"))
            throw new Exception401("MD와 상담해주세요.");
        return !productRepository.isExistProduct(product);
    }

    private boolean isValidOption(@Valid Option option){
        if(productRepository.isExistOption(option))
            throw new Exception400("이미 존재하는 옵션입니다.");
        return true;
    }

    public void deleteProduct(int id) {
        if(productRepository.findProductByID(id).isEmpty())
            throw new Exception404("존재하지 않는 id입니다.");
        productRepository.deleteProductByID(id);
        productRepository.deleteOptionsByID(id);
    }


    public String getProductByID(int id) {
        List<Product> products = productRepository.findProductByID(id);
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonProduct="";
        try {
            jsonProduct = objectMapper.writeValueAsString(products);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        if(jsonProduct==null)
            throw new Exception404("해당 물건이 없습니다.");
        return jsonProduct;
    }

    public void modifyProduct( Product product) {
        deleteProduct(product.getId());
        saveProduct(product);

    }

}
