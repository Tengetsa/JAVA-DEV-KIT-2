package chatServer.server;

import chatServer.client.Client;
import chatServer.server.repository.FileRepository;

import java.util.ArrayList;
import java.util.List;

public class Server {
    public static final String LOG_PATH = "src/main/java/server/log.txt";

    private List<Client> clientList;
    private boolean work;
    private ServerView serverView;
    private FileRepository repository;

    public Server(ServerView serverView, FileRepository repository) {
        this.serverView = serverView;
        this.repository = repository;
        clientList = new ArrayList<>();
    }



    public boolean connectUser(Client client){
        if (!work){
            return false;
        }
        clientList.add(client);
        return true;
    }

    public String getHistory() {
        return repository.readLog();
    }


    public void disconnectUser(Client client){
        clientList.remove(client);
        if (client != null){
            client.disconnect();
        }
    }

    public void serverStartup() {
        if (work){
            textOutputInWindow("Сервер уже был запущен");
        } else {
            work = true;
            textOutputInWindow("Сервер запущен!");
        }
    }

    public void stopServer() {
        if (!work){
            serverView.serverMessage("Сервер уже был остановлен");
        } else {

            work = false;
            for (Client client : clientList){
                client.disconnect();
            }
            //TODO поправить удаление
            serverView.serverMessage("Сервер остановлен!");
        }
    }


    private void answerAll(String text){
        for (Client client: clientList){
            client.printText(text);
        }
    }

//    public void saveDataInLog(String data) {
//        repository.saveInLog(data);
//    }

    public void message(String text){
        if (!work){
            return;
        }
        textOutputInWindow(text);
        answerAll(text);
        repository.saveInLog(text);
    }

    private void textOutputInWindow(String text) {
        serverView.serverMessage(text + "\n");
    }
}
