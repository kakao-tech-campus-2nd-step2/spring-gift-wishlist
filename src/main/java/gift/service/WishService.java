package gift.service;

import gift.dto.WishDto;
import gift.repository.WishRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Objects;

@Service
public class WishService {
    private WishRepository wishRepository;
    public WishService (WishRepository wishRepository) {
        this.wishRepository =wishRepository;
    }

    public ResponseEntity<Map<String, Object>> insert(WishDto.Request request) {
        return wishRepository.save(request.getProductId(),request.getToken());
    }
}
