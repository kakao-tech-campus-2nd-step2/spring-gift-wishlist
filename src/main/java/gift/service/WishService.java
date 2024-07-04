package gift.service;

import gift.common.exception.DuplicateWishException;
import gift.common.exception.EntityNotFoundException;
import gift.controller.dto.request.WishInsertRequest;
import gift.controller.dto.request.WishPatchRequest;
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

    public void update(WishPatchRequest request, Long memberId) {
        if(!checkWishExist(request.productId(), memberId)) {
            throw new EntityNotFoundException("Product with id " + request.productId() + " does not exist in wish");
        }
        if (request.productCount() == 0) {
            deleteByProductId(request.productId(), memberId);
            return;
        }
        wishDao.update(request, memberId);
    }


    public void save(WishInsertRequest request, Long memberId) {
        if(!checkProductExist(request.productId())) {
            throw new EntityNotFoundException("Product with id " + request.productId() + " does not exist");
        }
        if(checkWishExist(request.productId(), memberId)) {
            throw new DuplicateWishException("Product with id " + request.productId() + " already exists in wish");
        }
        wishDao.save(request, memberId);
    }

    public List<WishResponse> findAllByMemberId(Long memberId) {
        return wishDao.findAllByMemberId(memberId).stream()
                .map(WishResponse::from)
                .toList();
    }

    public void deleteByProductId(Long productId, Long memberId) {
        wishDao.deleteByProductId(productId, memberId);
    }

    private boolean checkProductExist(Long productId) {
        return productDao.existsById(productId);
    }

    private boolean checkWishExist(Long productId, Long memberId) {
        return wishDao.existsByProductIdAndMemberId(productId, memberId);
    }
}
