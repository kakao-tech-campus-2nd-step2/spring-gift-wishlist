package gift.repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import gift.entity.Member;

// JPA 학습 부족으로 그냥 JDBC 에 직접 연결하는 식으로 구현
public class MemberRepository {
    // h2-console JDBC 연결 정보
    private static final String URL = "jdbc:h2:mem:testdb";
    private static final String USERNAME = "sa";
    private static final String PASSWORD = "";

    // SQL 쿼리
    private static final String SQL_INSERT_MEMBER = "INSERT INTO member (email, password) VALUES (?, ?)";
    private static final String SQL_FIND_BY_EMAIL = "SELECT * FROM member WHERE id = ?";

    // 새로운 회원 저장
    public Member save(Member member) {
        try (Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
             PreparedStatement stmt = conn.prepareStatement(SQL_INSERT_MEMBER)) {

            stmt.setString(1, member.getEmail());
            stmt.setString(2, member.getPassword());

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
    public Member findByEmail(String email) {
        Member member = null;
        try (Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
             PreparedStatement stmt = conn.prepareStatement(SQL_FIND_BY_EMAIL)) {

            stmt.setString(1, email);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                member = new Member();
                member.setEmail(rs.getString("email"));
                member.setPassword(rs.getString("password"));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return member;
    }
}