package chatServer.client;

public interface ClientView {
    void showMessage(String text);
    void disconnectFromServer();
}
