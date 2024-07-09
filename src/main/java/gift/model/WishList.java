package gift.model;

import javax.persistence.*;
import java.util.List;

@Entity
public class WishList {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String username;

    @ManyToMany
    private List<Product> products;

    public WishList() {
    }

    public WishList(String username) {
        this.username = username;
    }

    // Getters and setters
}
