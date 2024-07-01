package gift.util;

import gift.model.ProductDAO;
import gift.model.ProductDTO;

public class ProductUtility {
    public static ProductDAO productDTOToDAO(ProductDAO productDAO, ProductDTO productDTO) {
        productDAO.setName(productDTO.getName());
        productDAO.setPrice(productDTO.getPrice());
        productDAO.setImageUrl(productDTO.getImageUrl());
        return productDAO;
    }
}
