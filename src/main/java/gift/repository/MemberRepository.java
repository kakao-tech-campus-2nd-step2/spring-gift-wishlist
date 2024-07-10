package gift.repository;

import gift.model.Member;

public interface MemberRepository {
    Member save(Member member);
    Member findByEmail(String email);
}
