package gift.controller;

import gift.dto.Product;
import gift.model.ProductRepository;
import gift.exception.ProductNotFoundException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    private ProductRepository productRepository;

    @Autowired
    public ProductController(ProductRepository productRepository){
        this.productRepository = productRepository;
    }

    @PostMapping
    @ResponseBody
    private ResponseEntity<Product> addProduct(@RequestBody Product product){
        productRepository.addProduct(product);
        return new ResponseEntity<>(product,HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Product>> getAllProducts(){
        List<Product> products = productRepository.findAll();
        return new ResponseEntity<>(products,HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable Long id){
        try{
            Product product = productRepository.findById(id);
            return new ResponseEntity<>(product,HttpStatus.OK);
        }catch(ProductNotFoundException e){
            return new ResponseEntity<>(null,HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable Long id, @RequestBody Product updateProduct){
        try {
            Product product = productRepository.updateProduct(id,updateProduct);
            return new ResponseEntity<>(product,HttpStatus.OK);
        }catch (ProductNotFoundException e){
            return new ResponseEntity<>(null,HttpStatus.NOT_FOUND);
        }

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id){
        try{
            productRepository.deleteProduct(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }catch(ProductNotFoundException e){
            return new ResponseEntity<>(null,HttpStatus.NOT_FOUND);
        }
    }


}