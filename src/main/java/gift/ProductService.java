package gift;

import gift.domain.product.Product;
import gift.domain.product.ProductRepository;
import gift.dto.ProductRequestDto;
import gift.dto.ProductResponseDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {
    private final ProductRepository repository;

    public ProductService(ProductRepository productRepository) {
        this.repository = productRepository;
    }

    public void addProduct(ProductRequestDto requestDto){
        if (isNotValid(requestDto.getName())){
            throw new IllegalArgumentException("카카오가 포함된 이름은 담당 MD와 협의가 필요합니다.");
        }
        Product product = new Product(requestDto.getName(), requestDto.getPrice(), requestDto.getImgUrl());
        repository.save(product);
    }

    public List<ProductResponseDto> findAll(){
        return repository.findAll()
                .stream()
                .map(ProductResponseDto::new)
                .toList();
    }

    public ProductResponseDto findProduct(Long id){
        return new ProductResponseDto(repository.findById(id));
    }

    public ProductResponseDto editProduct(Long id, ProductRequestDto request){
        int result = repository.update(id,request);
        if (result == 0){
            throw new IllegalArgumentException("잘못된 요청입니다.");
        }
        return new ProductResponseDto(repository.findById(id));
    }

    public void deleteProduct(Long id){
        repository.deleteById(id);
    }
    private Boolean isNotValid(String name){
        return name.contains("카카오");
    }
}
