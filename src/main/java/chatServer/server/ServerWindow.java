package chatServer.server;

import chatServer.server.repository.FileRepository;

import javax.swing.*;
import java.awt.*;

public class ServerWindow extends JFrame implements ServerView {
    public static final int WIDTH = 400;
    public static final int HEIGHT = 300;

    private final Server server;

    JButton btnStart, btnStop;
    JTextArea log;

    public ServerWindow(){
        server = new Server(this, new FileRepository());

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(WIDTH, HEIGHT);
        setResizable(false);
        setTitle("Chat server");
        setLocationRelativeTo(null);

        createPanel();

        setVisible(true);
    }

    public Server getServer() {
        return server;
    }

    private void createPanel() {
        log = new JTextArea();
        add(log);
        add(createButtons(), BorderLayout.SOUTH);
    }

    private Component createButtons() {
        JPanel panel = new JPanel(new GridLayout(1, 2));
        btnStart = new JButton("Start");
        btnStop = new JButton("Stop");

        btnStart.addActionListener(e -> server.serverStartup());
        btnStop.addActionListener(e -> server.stopServer());

        panel.add(btnStart);
        panel.add(btnStop);
        return panel;
    }

    @Override
    public void serverMessage(String message) {
        log.append(message);
    }
}
