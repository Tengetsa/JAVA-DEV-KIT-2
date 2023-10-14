package chatServer.client;

import chatServer.server.Server;

public class Client {
    private String name;
    private final ClientView clientView;
    private final Server server;
    private boolean connected;

    public Client(ClientView clientView, Server server) {
        this.clientView = clientView;
        this.server = server;
    }

    public boolean connectToServer(String name) {
        this.name = name;
        if (server.connectUser(this)){
            printText("Вы успешно подключились!\n");
            connected = true;
            String log = server.getHistory();
            if (log != null){
                printText(log);
            }
            return true;
        } else {
            printText("Подключение не удалось");
            return false;
        }
    }

    // мы посылаем
    public void sendMessage(String message) {
        if (connected) {
            if (!message.isEmpty()) {
                server.message(name +": " + message);
            }
        } else {
            printText("Нет подключения к серверу");
        }
    }

    // нам посылают
    public void serverAnswar(String answar) {
        printText(answar);
    }

    public void disconnect() {
        if (connected) {
            connected = false;
            clientView.disconnectFromServer();
            server.disconnectUser(this);
            printText("Вы были отключены от сервера!");
        }
    }

    public String getName() {
        return name;
    }

    public void printText(String text) {
        clientView.showMessage(text);
    }
}
