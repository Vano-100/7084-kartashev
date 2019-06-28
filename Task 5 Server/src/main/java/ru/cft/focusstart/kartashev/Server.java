package ru.cft.focusstart.kartashev;

import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Properties;

class Server {

    private LinkedList<String> recentMessages = new LinkedList<>();
    private ServerSocket serverSocket;
    private List<Client> clients = new ArrayList<>();
    private List<Client> clientsToRemove = new ArrayList<>();
    private boolean isServerStarted;

    Server() throws IOException {

        Properties properties = new Properties();
        try (InputStream propertiesStream = Server.class.getClassLoader().getResourceAsStream("server.properties")) {
            if (propertiesStream != null) {
                properties.load(propertiesStream);
            }
        } catch (IOException e) {
            System.out.println("Не удалось считать файл настроек.");
        }
        serverSocket = new ServerSocket(Integer.valueOf(properties.getProperty("server.port")));
        addTestMessagesToList();
        startServer();
    }

    private void addTestMessagesToList() {
        recentMessages.add("Петя227 : Всем привет!");
        recentMessages.add("Татьяна : продам гараж");
        recentMessages.add("Максим : продам что-то интересное");
        recentMessages.add("Олег : Петя, привет!");
        recentMessages.add("Олег : Как жизнь?");
    }

    private void startServer() throws IOException {

        Thread messageListenerThread = new Thread(() -> {
            isServerStarted = true;
            System.out.println("Сервер запущен.");
            boolean interrupted = false;
            while (!interrupted) {
                try {
                    String message;
                    clientsToRemove.clear();
                    for (Client client : clients) {
                        if (!(client.getReader()).ready() || (message = client.getReader().readLine()) == null) {
                            continue;
                        }
                        if (message.startsWith(ServerInfo.USER_NAME)) {
                            handleClientLogin(message.replace(ServerInfo.USER_NAME, ""), client);
                            continue;
                        }

                        if (message.startsWith(ServerInfo.GET_RECENT_MESSAGES)) {
                            client.getWriter().println(ServerInfo.RECENT_MESSAGES + getRecentMessages());
                            continue;
                        }

                        if (message.startsWith(ServerInfo.COMMON_MESSAGE)) {
                            sendMessageToAll(message);
                            addRecentMessageToList(message.replace(ServerInfo.COMMON_MESSAGE, ""));
                            continue;
                        }

                        if (message.equals(ServerInfo.GET_USER_LIST)) {
                            client.getWriter().println(ServerInfo.USERS_LIST + getUsersList());
                            handleNewClientConnection(client);
                            continue;
                        }

                        if (message.equals(ServerInfo.DISCONNECT)) {
                            handleClientDisconnection(client);
                        }
                    }
                    if (clientsToRemove.size() > 0) {
                        removeDisconnectedClients(clientsToRemove);
                    }

                } catch (IOException e) {
                    System.out.println("Неизвестная ошибка: " + e.getMessage());
                }

                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    interrupted = true;
                }
            }
        });
        messageListenerThread.start();

        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            try {
                isServerStarted = false;
                messageListenerThread.interrupt();
                serverSocket.close();
                for (Client client : clients) {
                    client.getSocket().close();
                }
            } catch (IOException e) {
                System.out.println("Ошибка при закрытии соединения: " + e.getMessage());
            }
        }));


        while (isServerStarted) {
            Client client = new Client(serverSocket.accept());
            clients.add(client);
        }
    }

    private boolean tryToLoginClient(Client client, String userName) {
        if (userNameIsFree(userName)) {
            client.setUserName(userName);
            client.getWriter().println(ServerInfo.USER_NAME_IS_FREE);
            return true;
        } else {
            client.getWriter().println(ServerInfo.ERROR + "Пользователь с таким именем уже подключен");
            return false;
        }
    }

    private void handleNewClientConnection(Client client) {
        sendMessageToAll(ServerInfo.NEW_USER_CONNECTED + client.getUserName());
        addRecentMessageToList("Подключился пользователь "
                + client.getUserName() + ". Поприветствуем!");
    }

    private void handleClientLogin(String userName, Client client) throws IOException {
        if (!tryToLoginClient(client, userName)) {
            clientsToRemove.add(client);
            client.getSocket().close();
        }
    }

    private void handleClientDisconnection(Client client) throws IOException {
        sendMessageToAll(ServerInfo.USER_DISCONNECTED + client.getUserName());
        recentMessages.add(client.getUserName() + " отключился.");
        clientsToRemove.add(client);
        client.getSocket().close();
    }

    private void removeDisconnectedClients(List<Client> clientsToRemove) {
        for (Client client : clientsToRemove) {
            clients.remove(client);
            sendMessageToAll(ServerInfo.COMMON_MESSAGE + "Пользователь с ником " +
                    client.getUserName() + " отключился");
        }
        clientsToRemove.clear();
    }

    private String getUsersList() {
        StringBuilder usersList = new StringBuilder();
        for (Client client : clients) {
            usersList.append(client.getUserName()).append(";");
        }
        return usersList.toString();
    }

    private void sendMessageToAll(String message) {
        for (Client client : clients) {
            client.getWriter().println(message);
        }
    }

    private boolean userNameIsFree(String nickName) {
        for (Client client : clients) {
            if (client.getUserName() != null && client.getUserName().equals(nickName)) {
                return false;
            }
        }
        return true;
    }

    private void addRecentMessageToList(String message) {
        recentMessages.addLast(message + ";");
        if (recentMessages.size() > 20) {
            recentMessages.removeFirst();
        }
    }

    private String getRecentMessages() {
        StringBuilder recentMessagesAsString = new StringBuilder();
        for (String message : recentMessages) {
            recentMessagesAsString.append(message).append(";");
        }
        return recentMessagesAsString.toString();
    }
}
