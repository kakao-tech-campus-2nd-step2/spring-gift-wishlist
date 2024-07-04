package gift.util;

import gift.domain.Member;
import gift.dto.MemberDto;

public class MemberMapper {

    public static Member toEntity(MemberDto memberDto) {
        return new Member(
                memberDto.email(),
                memberDto.password()
        );
    }

}
