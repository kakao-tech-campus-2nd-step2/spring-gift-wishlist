package gift.member.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Embedded;
import org.springframework.data.relational.core.mapping.Table;

@Table("members")
public class Member {
    @Id
    private Long id;
    @Embedded(onEmpty = Embedded.OnEmpty.USE_NULL)
    private Email email;
    @Embedded(onEmpty = Embedded.OnEmpty.USE_NULL)
    private Password password;
    @Embedded(onEmpty = Embedded.OnEmpty.USE_NULL)
    private NickName nickName;

    // JDBC 에서 엔티티 클래스를 인스턴스화할 때 반드시 기본 생성자와 파라미터 생성자가 필요하다
    public Member() {}

    public Member(Long id, Email email, Password password, NickName nickName) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.nickName = nickName;
    }

    public long getId() {
        return id;
    }

    public Email getEmail() {
        return email;
    }

    public Password getPassword() {
        return password;
    }

    public NickName getNickName() {
        return nickName;
    }

    public boolean checkNew() {
        return id == null;
    }
}
