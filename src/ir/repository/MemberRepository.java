package ir.repository;

import ir.exception.MemberNotFoundException;
import ir.model.Member;
import ir.util.DatabaseConfig;

import java.sql.*;

public class MemberRepository {

    private DatabaseConfig databaseConfig = new DatabaseConfig();


    public int registerMember(Member member) {
        String insertMember = "INSERT INTO members (full_name , phone) VALUES (?, ?)";
        try (Connection connection = this.databaseConfig.getConnection();
             PreparedStatement ps = connection.prepareStatement(insertMember)) {
            ps.setString(1, member.getFullName());
            ps.setString(2, member.getPhone());
            return ps.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("INSERT MEMBER ERROR: " + e.getMessage());
        }
    }

    public Member findMember(int id) {
        String memberQuery = "SELECT * FROM members WHERE id = ?";
        try (Connection connection = this.databaseConfig.getConnection();
             PreparedStatement ps = connection.prepareStatement(memberQuery)) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return new Member(
                        rs.getInt("id"),
                        rs.getString("full_name"),
                        rs.getString("phone")
                );
            }
            throw new MemberNotFoundException("MEMBER NOT FOUND");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public int deleteMember(int id) {
        String deleteMemberQuery = "DELETE FROM members WHERE id = ?";
        try (Connection connection = this.databaseConfig.getConnection();
             PreparedStatement ps = connection.prepareStatement(deleteMemberQuery)) {
            ps.setInt(1, id);
            int  record = ps.executeUpdate();
            if (record == 0) {
                throw new MemberNotFoundException("MEMBER NOT FOUND");
            }
            return record;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}