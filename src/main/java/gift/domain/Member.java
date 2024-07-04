package gift.domain;

import lombok.Builder;
import lombok.Getter;

@Getter
public class Member {

    private Long id;
    private String name;
    private String email;
    private String password;
    private MemberRole role;

    @Builder
    public Member(Long id, String name, String email, String password, MemberRole role) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.role = role;
    }

}
