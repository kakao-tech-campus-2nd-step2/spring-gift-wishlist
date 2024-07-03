package gift.controller;

import gift.ExceptionService;
import gift.NameException;
import gift.Product;
import gift.ProductRepository;
import gift.ProductDto;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProductController {
    private final ProductRepository productRepository;
    private final ExceptionService exceptionService;
    public ProductController(ProductRepository productRepository, ExceptionService exceptionService) {
        this.productRepository = productRepository;
        this.exceptionService = exceptionService;
    }

    @GetMapping("/api/products")
    public ResponseEntity<List<Product>> readAll(){
        return new ResponseEntity<>(productRepository.findAll(), HttpStatus.OK);
    }


    @GetMapping("/api/products/{id}")
    public ResponseEntity<Product> read(@PathVariable("id") Long id){
        return new ResponseEntity<>(productRepository.findById(id), HttpStatus.OK);
    }

    @PostMapping("/api/products")
    public ResponseEntity<Product> create(@RequestBody ProductDto productDto){
        exceptionService.findNameException(productDto.getName());
        productRepository.save(productDto);
        return new ResponseEntity<>(productRepository.findByName(productDto.getName()),HttpStatus.CREATED);
    }

    @PutMapping("/api/products/{id}")
    public ResponseEntity<Product> update(@PathVariable("id") Long id, @RequestBody ProductDto productDto){
        exceptionService.findNameException(productDto.getName());
        productRepository.update(id, productDto);

        return new ResponseEntity<>(productRepository.findById(id), HttpStatus.OK);
    }

    @DeleteMapping("/api/products/{id}")
    public ResponseEntity<Product> delete(@PathVariable("id") Long id){
        productRepository.delete(id);
        return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
    }

    @ExceptionHandler(value = NameException.class)
    public ResponseEntity<String> handleNameException(Exception e){
        return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }
}
