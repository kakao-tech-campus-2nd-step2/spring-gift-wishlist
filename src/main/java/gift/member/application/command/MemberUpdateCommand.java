package gift.member.application.command;

import gift.member.domain.Member;

public record MemberUpdateCommand(
        String email,
        String password
) {
    public Member toMember() {
        return new Member(email, password);
    }
}
