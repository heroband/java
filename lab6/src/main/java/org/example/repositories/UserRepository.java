package org.example.repositories;

import org.example.interfaces.ConnectionManager;
import org.example.models.User;

import java.sql.*;

public class UserRepository extends GenericDbRepository<User, Integer> {

    public UserRepository(ConnectionManager connectionManager) {
        super(connectionManager);
    }

    @Override
    protected String getTableName() {
        return "users";
    }

    @Override
    protected String getIdColumn() {
        return "id";
    }

    @Override
    protected User mapRow(ResultSet rs) throws SQLException {
        return new User(rs.getInt("id"), rs.getString("username"), rs.getString("email"));
    }

    @Override
    protected void bindId(PreparedStatement stmt, Integer id) throws SQLException {
        stmt.setInt(1, id);
    }

    @Override
    protected PreparedStatement createInsertStatement(Connection conn, User user) throws SQLException {
        String sql = "INSERT INTO users (username, email) VALUES (?, ?)";
        PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        stmt.setString(1, user.getUsername());
        stmt.setString(2, user.getEmail());
        return stmt;
    }

    @Override
    protected PreparedStatement createUpdateStatement(Connection conn, User user) throws SQLException {
        String sql = "UPDATE users SET username = ?, email = ? WHERE id = ?";
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setString(1, user.getUsername());
        stmt.setString(2, user.getEmail());
        stmt.setInt(3, user.getId());
        return stmt;
    }

    @Override
    protected void assignGeneratedId(User user, ResultSet rs) throws SQLException {
        user.setId(rs.getInt(1));
    }
}
