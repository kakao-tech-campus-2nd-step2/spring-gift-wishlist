package gift.service;

import gift.repository.JdbcProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductService {

    private final JdbcProductRepository repository;

    @Autowired
    public ProductService(JdbcProductRepository repository) {
        this.repository = repository;
    }


}
