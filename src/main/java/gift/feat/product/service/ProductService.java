package gift.feat.product.service;

import org.springframework.stereotype.Service;
import gift.feat.product.domain.Product;
import gift.feat.product.dto.ProductRequestDto;
import gift.core.exception.product.DuplicateProductIdException;
import gift.core.exception.product.ProductNotFoundException;
import gift.feat.product.repository.ProductRepository;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {

	private final ProductRepository productRepository;

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