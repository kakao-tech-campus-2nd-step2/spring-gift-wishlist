package gift.repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import gift.entity.Member;
import org.springframework.data.repository.CrudRepository;

// JPA 학습 부족으로 그냥 JDBC 에 직접 연결하는 식으로 구현
public interface MemberRepository extends CrudRepository<Member, Long> {
    // h2-console JDBC 연결 정보
    String URL = "jdbc:h2:mem:testdb";
    String USERNAME = "sa";
    String PASSWORD = "";

    // SQL 쿼리
    String SQL_INSERT_MEMBER = "INSERT INTO member (email, password) VALUES (?, ?)";
    String SQL_FIND_BY_EMAIL = "SELECT * FROM member WHERE id = ?";

    // 새로운 회원 저장
    default Member save(Member member) {
        try (Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
             PreparedStatement stmt = conn.prepareStatement(SQL_INSERT_MEMBER)) {

            // Member 클래스의 이메일과 비밀번호 필드를 public 접근 제어자로 바꿔도 될까요?
            stmt.setString(1, member.email());
            stmt.setString(2, member.password());

            int rowsInserted = stmt.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("새로운 회원 저장 성공");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return member;
    }

    // 이메일로 회원 찾기
    default Member findByEmail(String email) {
        Member member = null;
        try (Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
             PreparedStatement stmt = conn.prepareStatement(SQL_FIND_BY_EMAIL)) {

            stmt.setString(1, email);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                member = new Member();
                member.email = rs.getString("email"); // email 필드 직접 접근
                member.password = rs.getString("password"); // password 필드 직접 접근
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return member;
    }

}