package gift.product.model;

public class Member {
    private Long memberId;
    private String email;
    private String password;

    public Member() {

    }

    public Member(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public Member(Long memberId, String email, String password) {
        this.memberId = memberId;
        this.email = email;
        this.password = password;
    }

    public Long getMemberId() {
        return memberId;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }
}
