package gift.service;

import gift.dto.ProductOptionRequest;
import gift.dto.ProductOptionResponse;
import gift.model.ProductOption;
import gift.repository.ProductOptionRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductOptionService {

    private final ProductOptionRepository repository;

    public ProductOptionService(ProductOptionRepository repository) {
        this.repository = repository;
    }

    public ProductOptionResponse addOption(ProductOptionRequest productOptionRequest) {
        var option = repository.save(ProductOption.from(productOptionRequest));
        return ProductOptionResponse.from(option);
    }

    public ProductOptionResponse updateOption(Long id, ProductOptionRequest productOptionRequest) {
        var option = updateProductOptionWithId(id, productOptionRequest);
        return ProductOptionResponse.from(option);
    }

    public ProductOptionResponse getOption(Long id) {
        var option = repository.findById(id);
        return ProductOptionResponse.from(option);
    }

    public List<ProductOptionResponse> getOptions(Long productId) {
        return repository.findAll(productId)
                .stream()
                .map(ProductOptionResponse::from)
                .toList();
    }

    public void deleteOption(Long id) {
        repository.deleteById(id);
    }

    private ProductOption updateProductOptionWithId(Long id, ProductOptionRequest productOptionRequest) {
        var option = repository.findById(id);
        option.updateFrom(productOptionRequest);
        repository.update(option);
        return option;
    }
}
