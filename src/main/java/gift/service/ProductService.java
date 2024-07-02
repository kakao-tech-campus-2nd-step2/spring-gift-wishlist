package gift.service;

import gift.domain.Product;
import gift.dto.ProductRequestDto;
import gift.dto.ProductResponseDto;
import gift.exception.ProductNotFoundException;
import gift.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.regex.Pattern;


@Service
public class ProductService {
    private final ProductRepository productRepository;
    private static final String KAKAO = "카카오";

    private final String NOT_FOUND_PRODUCT_MESSAGE = "ID가 %d 인 상품이 존재하지 않습니다.";

    @Autowired
    public ProductService(ProductRepository productRepository){
        this.productRepository = productRepository;
    }

    @Transactional
    public ProductResponseDto save(ProductRequestDto productDto){
        Long id = productRepository.save(Product.dtoToEntity(productDto));
        return new ProductResponseDto(id,productDto.getName(),productDto.getPrice(),productDto.getImageUrl());
    }

    private boolean isApprovedByMD() {
        return true;
    }

    @Transactional(readOnly = true)
    public ProductResponseDto findById(Long id){
         Product product = findProductByIdOrThrow(id);
         return ProductResponseDto.from(product);
    }

    @Transactional(readOnly = true)
    public List<ProductResponseDto> findAll(){
        return productRepository.findAll()
                .stream()
                .map(ProductResponseDto::from)
                .toList();
    }

    @Transactional
    public Long deleteById(Long id){
        findProductByIdOrThrow(id);
        return productRepository.delete(id);
    }

    @Transactional
    public ProductResponseDto updateById(Long id, ProductRequestDto productDto){
        findProductByIdOrThrow(id);
        Product editProduct = productRepository.update(id, Product.dtoToEntity(productDto));
        ProductResponseDto productResponseDto = ProductResponseDto.from(editProduct);
        productResponseDto.setId(id);
        return productResponseDto;
    }

    private Product findProductByIdOrThrow(Long id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException(NOT_FOUND_PRODUCT_MESSAGE.formatted(id)));
    }
}
