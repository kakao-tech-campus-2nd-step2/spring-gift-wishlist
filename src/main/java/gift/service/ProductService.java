package gift.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import gift.DTO.SaveProductDTO;
import gift.entity.Option;
import gift.entity.Product;
import gift.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
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
        SaveProductDTO saveProductDTO = new SaveProductDTO(product.getId(), product.getName(),product.getPrice(),product.getImageUrl());

        if(!productRepository.isExistProduct(saveProductDTO))
            productRepository.saveProduct(saveProductDTO);
        List<String> optionList = Arrays.stream(product.getOption().split(",")).toList();
        for(String str : optionList){
            Option option = new Option(product.getId(), str);
            if(!productRepository.isExistOption(option))
                productRepository.saveOption(new Option(option.getId(),str));
        }
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

    public void modifyProduct(Product product) {
        deleteProduct(product.getId());
        saveProduct(product);
        //saveOptions(new Option(product.getId(), product.getOption()));
    }
}
