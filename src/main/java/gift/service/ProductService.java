package gift.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import gift.DTO.SaveProductDTO;
import gift.entity.Option;
import gift.entity.Product;
import gift.repository.ProductRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestParam;

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

    public void saveProduct(int id,String name,int price,String imageUrl,String options) {
        if(options == null)
            throw new IllegalArgumentException("하나의 옵션은 필요합니다.");

        SaveProductDTO saveProductDTO = new SaveProductDTO(id, name, price,imageUrl);
        if(isValidProduct(saveProductDTO)){
            productRepository.saveProduct(saveProductDTO);
        }

        List<String> optionList = Arrays.stream(options.split(",")).toList();
        for(String str : optionList){
            Option option = new Option(id, str);
            if(isValidOption(option))
                productRepository.saveOption(new Option(option.getId(),str));
        }
    }
    private boolean isValidProduct(@Valid SaveProductDTO saveProductDTO){
        if(saveProductDTO.getName().contentEquals("카카오"))
            throw new IllegalArgumentException("MD와 상담해주세요.");
        return !productRepository.isExistProduct(saveProductDTO);
    }
    private boolean isValidOption(@Valid Option option){
        return !productRepository.isExistOption(option);
    }
    public void deleteProduct(int id) {
        if(!productRepository.findProductByID(id).isEmpty())
            productRepository.deleteProductByID(id);
        if(!productRepository.findOptionByID(id).isEmpty())
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
        return jsonProduct;
    }

    public void modifyProduct( int id,  String name,
                               int price,  String imageUrl,
                               String options) {
        //Product product = new Product(id, name, price, imageUrl, options);
        deleteProduct(id);
        saveProduct(id,name, price, imageUrl, options);
        //saveOptions(new Option(product.getId(), product.getOption()));
    }

}
