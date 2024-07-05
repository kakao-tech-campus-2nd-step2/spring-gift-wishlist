package gift.dto;

import gift.domain.Member;

public record MemberDto(String email, String password) {
    public static MemberDto of(Member member) {
        return new MemberDto(member.email(), member.password());
    }
}
