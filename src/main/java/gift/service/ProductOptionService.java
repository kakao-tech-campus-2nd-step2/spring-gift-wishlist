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
        var option = createOptionWithOptionRequest(productOptionRequest);
        var savedOption = repository.save(option);
        return ProductOptionResponse.from(savedOption);
    }

    public ProductOptionResponse updateOption(Long id, ProductOptionRequest productOptionRequest) {
        var option = findOptionWithId(id);
        var updatedOption = updateProductOptionWithId(option, productOptionRequest);
        return ProductOptionResponse.from(updatedOption);
    }

    public ProductOptionResponse getOption(Long id) {
        var option = findOptionWithId(id);
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

    private ProductOption findOptionWithId(Long id) {
        return repository.findById(id);
    }

    private ProductOption createOptionWithOptionRequest(ProductOptionRequest productOptionRequest) {
        return new ProductOption(productOptionRequest.productId(), productOptionRequest.name(), productOptionRequest.additionalPrice());
    }

    private ProductOption updateProductOptionWithId(ProductOption option, ProductOptionRequest productOptionRequest) {
        option.updateOptionInfo(productOptionRequest.name(), productOptionRequest.additionalPrice());
        repository.update(option);
        return option;
    }
}
