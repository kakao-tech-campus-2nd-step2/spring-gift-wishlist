package gift.domain;

import lombok.Getter;

@Getter
public enum MemberRole {
    MEMBER("MEMBER"),
    MANAGER("MANAGER");

    private String value;

    MemberRole(String value) {
        this.value = value;
    }
}
