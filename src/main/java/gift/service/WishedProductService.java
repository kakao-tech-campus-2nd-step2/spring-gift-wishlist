package gift.service;

import gift.dto.WishedProductDTO;
import gift.repository.WishedProductDAO;
import java.util.Collection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WishedProductService {

    private final WishedProductDAO wishedProductDAO;

    @Autowired
    public WishedProductService(WishedProductDAO wishedProductDAO) {
        this.wishedProductDAO = wishedProductDAO;
    }

    public Collection<WishedProductDTO> getWishedProducts(String memberEmail) {
        return wishedProductDAO.getWishedProducts(memberEmail);
    }
}
