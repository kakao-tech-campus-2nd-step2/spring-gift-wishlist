package gift.model.member;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "members")
public record Member(
        @Id
        String email,
        String password,
        String role
) {}