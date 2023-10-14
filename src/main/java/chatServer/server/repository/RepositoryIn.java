package chatServer.server.repository;

public interface RepositoryIn<T> {
    void saveInLog(T text);
    T readLog();
}
