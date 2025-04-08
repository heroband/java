package org.example.di;
import org.example.interfaces.ConnectionManager;
import org.example.repositories.UserRepository;

public class RepositoryFactory {
    private final ConnectionManager connectionManager;

    public RepositoryFactory(ConnectionManager connectionManager) {
        this.connectionManager = connectionManager;
    }

    public UserRepository getRepository(){
        return new UserRepository(connectionManager);
    }
}
