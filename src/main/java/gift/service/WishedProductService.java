package gift.service;

import gift.dto.WishedProductDTO;
import gift.DAO.WishedProductDAO;
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

    public WishedProductDTO addWishedProduct(String memberEmail, WishedProductDTO wishedProductDTO) {
        WishedProductDTO addedWishedProductDTO = new WishedProductDTO(memberEmail, wishedProductDTO.productId(), wishedProductDTO.amount());
        wishedProductDAO.addWishedProduct(addedWishedProductDTO);
        return addedWishedProductDTO;
    }

    public WishedProductDTO deleteWishedProduct(String memberEmail, WishedProductDTO wishedProductDTO) {
        WishedProductDTO deletedWishedProductDTO = new WishedProductDTO(memberEmail, wishedProductDTO.productId(), wishedProductDTO.amount());
        wishedProductDAO.deleteWishedProduct(deletedWishedProductDTO);
        return deletedWishedProductDTO;
    }
}
