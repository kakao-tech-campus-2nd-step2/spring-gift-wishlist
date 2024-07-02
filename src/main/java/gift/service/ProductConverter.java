package gift.service;

import gift.dto.ProductDTO;
import gift.entity.Product;
import gift.exception.CustomException;
import java.util.List;
import java.util.stream.Collectors;


public class ProductConverter {
    public static List<ProductDTO> convertToProductDTO(List<Product> productList) throws CustomException {
        return productList.stream().map(product -> {
            return new ProductDTO(product.getId(), product.getName(), product.getPrice(), product.getImageUrl());
        }).collect(Collectors.toList());
    }
}
