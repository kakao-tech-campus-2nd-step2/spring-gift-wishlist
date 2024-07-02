package gift.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import gift.domain.Product;
import gift.dto.ProductRequestDto;
import gift.exception.product.DuplicateProductIdException;
import gift.exception.product.ProductNotFoundException;
import gift.repository.ProductRepository;

import java.util.List;

@Service
public class ProductService {

	@Autowired
	private ProductRepository productRepository;

	public void saveProduct(ProductRequestDto productRequestDto) {
		if (productRepository.findById(productRequestDto.id()).isPresent()) {
			throw new DuplicateProductIdException(productRequestDto.id());
		}
		productRepository.save(productRequestDto.toEntity());
	}

	public Product getProductById(Long id) {
		return productRepository.findById(id)
			.orElseThrow(() -> new ProductNotFoundException(id));
	}

	public List<Product> getAllProducts() {
		return productRepository.findAll();
	}

	public void updateProduct(Long id, ProductRequestDto productRequestDto) {
		Product existingProduct = productRepository.findById(id)
			.orElseThrow(() -> new ProductNotFoundException(id));
		existingProduct.setName(productRequestDto.name());
		existingProduct.setPrice(productRequestDto.price());
		existingProduct.setImageUrl(productRequestDto.imageUrl());
		productRepository.update(existingProduct);
	}

	public void deleteProduct(Long id) {
		Product existingProduct = productRepository.findById(id)
			.orElseThrow(() -> new ProductNotFoundException(id));
		productRepository.deleteById(id);
	}
}