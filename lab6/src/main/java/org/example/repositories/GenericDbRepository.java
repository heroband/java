package org.example.repositories;
import org.example.interfaces.ConnectionManager;
import org.example.interfaces.DbRepository;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public abstract class GenericDbRepository<ENTITY, ID> implements DbRepository<ENTITY, ID> {
    protected final ConnectionManager connectionManager;

    public GenericDbRepository(ConnectionManager connectionManager) {
        this.connectionManager = connectionManager;
    }

    protected abstract String getTableName();
    protected abstract String getIdColumn();
    protected abstract ENTITY mapRow(ResultSet rs) throws SQLException;
    protected abstract void bindId(PreparedStatement stmt, ID id) throws SQLException;
    protected abstract PreparedStatement createInsertStatement(Connection conn, ENTITY entity) throws SQLException;
    protected abstract PreparedStatement createUpdateStatement(Connection conn, ENTITY entity) throws SQLException;
    protected abstract void assignGeneratedId(ENTITY entity, ResultSet rs) throws SQLException;


    @Override
    public ENTITY findById(ID id) {
        String sql = "SELECT * FROM " + getTableName() + " WHERE " + getIdColumn() + " = ?";
        try (Connection conn = connectionManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            bindId(stmt, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) return mapRow(rs);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error finding entity by ID: " + id, e);
        }
        return null;
    }

    @Override
    public List<ENTITY> findAll() {
        List<ENTITY> entities = new ArrayList<>();
        String sql = "SELECT * FROM " + getTableName();

        try (Connection conn = connectionManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                entities.add(mapRow(rs));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error finding all entities", e);
        }

        return entities;
    }

    @Override
    public ENTITY save(ENTITY entity) {
        try (Connection conn = connectionManager.getConnection();
             PreparedStatement stmt = createInsertStatement(conn, entity)) {

            stmt.executeUpdate();

            try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    assignGeneratedId(entity, generatedKeys); // <-- цей метод треба реалізувати в UserRepository
                }
            }

            return entity;
        } catch (SQLException e) {
            throw new RuntimeException("Error saving entity", e);
        }
    }

    @Override
    public ENTITY update(ENTITY entity) {
        try (Connection conn = connectionManager.getConnection();
             PreparedStatement stmt = createUpdateStatement(conn, entity)) {

            stmt.executeUpdate();
            return entity;
        } catch (SQLException e) {
            throw new RuntimeException("Error updating entity", e);
        }
    }

    @Override
    public void deleteById(ID id) {
        String sql = "DELETE FROM " + getTableName() + " WHERE " + getIdColumn() + " = ?";

        try (Connection conn = connectionManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            bindId(stmt, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Error deleting entity with ID: " + id, e);
        }
        System.out.println("User deleted");
    }
}
