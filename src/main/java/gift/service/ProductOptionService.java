package gift.service;

import gift.dto.ProductOptionRequest;
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

    public ProductOption addOption(ProductOptionRequest productOptionRequest) {
        return repository.save(ProductOption.from(productOptionRequest));
    }

    public ProductOption updateOption(Long id, ProductOptionRequest productOptionRequest) {
        var productOption = repository.findById(id);
        productOption.updateFrom(productOptionRequest);
        repository.update(productOption);
        return productOption;
    }

    public ProductOption getOption(Long id) {
        return repository.findById(id);
    }

    public List<ProductOption> getOptions(Long productId) {
        return repository.findAll(productId);
    }

    public void deleteOption(Long id) {
        repository.deleteById(id);
    }
}
