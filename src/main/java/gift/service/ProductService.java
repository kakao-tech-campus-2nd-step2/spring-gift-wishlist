package gift.service;

import gift.dto.ProductDto;
import gift.entity.Member;
import gift.entity.Product;
import gift.dao.ProductDao;
import gift.vo.ProductName;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductService {
    private final ProductDao productDao;

    @Autowired
    public ProductService(ProductDao productDao) {
        this.productDao = productDao;
    }

    public void save(ProductDto productDto) {
        ProductName productName = new ProductName(productDto.name());
        productName.validate();

        Product product = new Product(
                productDto.name(),
                productDto.price(),
                productDto.imgUrl()
        );
        productDao.save(product);
    }

    // 상품 전체 목록 조회
    public List<ProductDto> findAll() {
        return productDao.findAll().stream()
                .map(product -> new ProductDto(product.id(), product.name(), product.price(), product.imgUrl()))
                .collect(Collectors.toList());
    }

    // id로 상품 조회
    public ProductDto findById(Long id) {
        Product product = productDao.findById(id);
        return new ProductDto(product.id(), product.name(), product.price(), product.imgUrl());
    }

    // 상품 수정
    public void update(Long id, ProductDto productDto) {
        ProductName productName = new ProductName(productDto.name());
        productName.validate(); // 상품명 유효성 검사 수행

        Product product = productDao.findById(id);
        product.update(productDto.id(), productDto.name(), productDto.price(), productDto.imgUrl());
        productDao.update(product);
    }

    // 상품 삭제
    public void deleteById(Long id) {
        productDao.deleteById(id);
    }

    // JWT 토큰 생성
    public String generateToken(Member member) {
        String token = Jwts.builder()
                .setSubject(member.email()) // 이메일을 토큰 주제로 설정
                .signWith(SignatureAlgorithm.HS256, "myToken-key") // 알고리즘과 키 설정
                .compact();

        return token;
    }
}