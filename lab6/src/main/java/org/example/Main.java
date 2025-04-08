package org.example;

import org.example.di.RepositoryFactory;
import org.example.interfaces.ConnectionManager;
import org.example.models.User;
import org.example.repositories.UserRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        ConnectionManager connectionManager = new SqliteConnectionManager("mydatabase.db");
        RepositoryFactory repositoryFactory = new RepositoryFactory(connectionManager);
        UserRepository userRepository = repositoryFactory.getRepository();
        initializeDatabase(connectionManager);

        // Create a new user
        User denys = new User(0, "Denys", "denys@denys.com");
        User savedUser = userRepository.save(denys);
        System.out.println("Saved user: " + savedUser);

        User tom = new User(0, "tom", "tom@tom.com");
        savedUser = userRepository.save(tom);
        System.out.println("Saved user: " + savedUser);

        User bob = new User(0, "bob", "bob@bob.com");
        savedUser = userRepository.save(bob);
        System.out.println("Saved user: " + savedUser);

        // Find user by ID
        User foundUser = userRepository.findById(savedUser.getId());
        System.out.println("Found user by ID: " + foundUser);

        // Update user
        foundUser.setEmail("denys_updated@denys.com");
        User updatedUser = userRepository.update(foundUser);
        System.out.println("Updated user: " + updatedUser);

        // Find all users
        List<User> allUsers = userRepository.findAll();
        System.out.println("All users:");
        allUsers.forEach(System.out::println);

        // Delete user
        userRepository.deleteById(savedUser.getId());
    }

    private static void initializeDatabase(ConnectionManager connectionManager) {
        String createTableSql = "CREATE TABLE IF NOT EXISTS users (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "username TEXT NOT NULL, " +
                "email TEXT NOT NULL)";

        try (Connection conn = connectionManager.getConnection();
             PreparedStatement createTableStmt = conn.prepareStatement(createTableSql)) {
            createTableStmt.execute();
            System.out.println("Database tables created successfully");
        } catch (SQLException e) {
            throw new RuntimeException("Error initializing database", e);
        }
    }
}