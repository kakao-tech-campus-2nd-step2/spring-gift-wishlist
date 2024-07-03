package gift.product.controller;

import org.junit.jupiter.api.BeforeEach;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.ui.Model;
import org.springframework.validation.support.BindingAwareModelMap;

public class ProductControllerTest {

    @InjectMocks
    private ProductController productController;

    private Model model;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        model = new BindingAwareModelMap();
    }
}
