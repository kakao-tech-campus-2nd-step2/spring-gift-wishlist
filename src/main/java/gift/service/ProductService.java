package gift.service;

import gift.domain.Product;
import gift.dto.request.ProductRequestDto;
import gift.dto.response.ProductResponseDto;
import gift.exception.KakaoInNameException;
import gift.exception.EntityNotFoundException;
import gift.repository.product.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public Long addProduct(ProductRequestDto productDto){
        checkNameInKakao(productDto);

        Product product = Product.toEntity(productDto);

        Product savedProduct = productRepository.save(product);

        return savedProduct.getId();
    }



    public ProductResponseDto findProductById(Long id){
        Product product = productRepository.findById(id)
                .orElseThrow(EntityNotFoundException::new);
        return ProductResponseDto.from(product);
    }

    public List<ProductResponseDto> findAllProducts(){
        return productRepository.findAll().stream()
                .map(ProductResponseDto::from)
                .collect(Collectors.toList());
    }

    public Long updateProduct(Long id, ProductRequestDto productRequestDto){
        checkNameInKakao(productRequestDto);
        Product product = Product.toEntity(productRequestDto);

        Long updatedRow = productRepository.update(id, product);

        if(updatedRow == 0){
            throw new EntityNotFoundException();
        }

        return updatedRow;
    }

    public Long deleteProduct(Long id){
        Long deletedRow = productRepository.delete(id);

        if(deletedRow == 0){
            throw new EntityNotFoundException();
        }

        return deletedRow;
    }

    private void checkNameInKakao(ProductRequestDto productDto) {
        if(productDto.name().contains("카카오")){
            throw new KakaoInNameException();
        }
    }
}