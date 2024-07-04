package gift.service;

import gift.common.exception.EntityNotFoundException;
import gift.controller.dto.response.WishResponse;
import gift.model.ProductDao;
import gift.model.WishDao;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WishService {
    private final WishDao wishDao;
    private final ProductDao productDao;

    public WishService(WishDao wishDao, ProductDao productDao) {
        this.wishDao = wishDao;
        this.productDao = productDao;
    }

    public void save(Long productId, Long memberId) {
        if(!productDao.existsById(productId)) {
            throw new EntityNotFoundException("Product with id " + productId + " does not exist");
        }
        wishDao.save(productId, memberId);
    }

}
