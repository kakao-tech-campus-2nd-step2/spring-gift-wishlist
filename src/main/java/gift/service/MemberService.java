package gift.service;

import gift.exception.InvalidCredentialsException;
import gift.exception.MemberNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Base64;

@Service
public class MemberService {

    private final DataSource dataSource;

    @Autowired
    public MemberService(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public void register(String email, String password) {
        try (Connection conn = dataSource.getConnection()) {
            String sql = "INSERT INTO member(email, password) VALUES (?, ?)";
            try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                stmt.setString(1, email);
                stmt.setString(2, password);
                stmt.executeUpdate();
            }
        } catch (SQLException e) {
            throw new RuntimeException("회원 가입 중 오류가 발생했습니다.", e);
        }
    }

    public String authenticate(String email, String password) {
        String sql = "SELECT * FROM member WHERE email = ?";
        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, email);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    String storedPassword = rs.getString("password");
                    if (password.equals(storedPassword)) {
                        return generateJwtToken(email);
                    } else {
                        throw new InvalidCredentialsException("잘못된 비밀번호입니다.");
                    }
                } else {
                    throw new MemberNotFoundException("존재하지 않는 회원입니다.");
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("로그인 중 오류가 발생했습니다.", e);
        }
    }

    private String generateJwtToken(String email) {
        return email;
    }
}
