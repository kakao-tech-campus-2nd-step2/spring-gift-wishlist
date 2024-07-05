package gift.repository;

import gift.model.Member;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberRepository {
    public void save(Member member);

    public Member findByEmail(String email);

    public Member findById(Long id);
}
