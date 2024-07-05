package gift.controller;

import gift.argumentresolver.LoginMember;
import gift.dto.MemberDTO;
import gift.dto.WishedProductDTO;
import gift.service.WishedProductService;
import java.util.Collection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/wishes")
public class WishedProductController {

    private final WishedProductService wishedProductService;

    @Autowired
    public WishedProductController(WishedProductService wishedProductService) {
        this.wishedProductService = wishedProductService;
    }

    @GetMapping
    public ResponseEntity<Collection<WishedProductDTO>> getWishedProducts(@LoginMember MemberDTO memberDTO) {
        return ResponseEntity.ok().body(wishedProductService.getWishedProducts(memberDTO.email()));
    }

    @PostMapping
    public ResponseEntity<WishedProductDTO> addWishedProduct(@LoginMember MemberDTO memberDTO, @RequestBody WishedProductDTO wishedProductDTO) {
        return ResponseEntity.ok().body(wishedProductService.addWishedProduct(memberDTO.email(), wishedProductDTO));
    }
}
