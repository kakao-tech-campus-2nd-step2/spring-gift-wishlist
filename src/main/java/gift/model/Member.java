package gift.model;

public class Member {
    private Long id;
    private String email;
    private String password;
    private boolean tokenIssued;

    // 기본 생성자 추가
    public Member() {}


    public String getEmail() {
        return email;
    }


    public String getPassword() {
        return password;
    }

}