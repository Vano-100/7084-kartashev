package ru.cft.focusstart.kartashev;

import java.io.*;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

class Client {

    private String userName;
    private String host;
    private int port;
    private ClientCallback callback;
    private Socket socket;
    private PrintWriter writer;
    private BufferedReader reader;
    private Thread messageListenerThread;

    Client(String userName, String serverAddress, ClientCallback callback) throws ApplicationException {
        this.userName = userName;
        this.host = serverAddress.split(":")[0];
        try {
            this.port = Integer.parseInt(serverAddress.split(":")[1]);
        } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
            throw new ApplicationException("Введите данные в виде \"server:port\"");
        }
        this.callback = callback;
        connectToServer();
    }

    private void connectToServer() throws ApplicationException {
        try {
            socket = new Socket(host, port);
            writer = new PrintWriter(new OutputStreamWriter(
                    socket.getOutputStream(), StandardCharsets.UTF_8), true);
            reader = new BufferedReader(new InputStreamReader(socket.getInputStream(), StandardCharsets.UTF_8));
        } catch (IOException e) {
            throw new ApplicationException("Не удалось подключиться. Проверьте адрес сервера.");
        }
        if (socket.isClosed()) {
            callback.onConnectionProblems("Не удалось подключиться. Проверьте адрес сервера.");
        }
    }

    private void startListeningThread() {
        messageListenerThread = new Thread(() -> {
            boolean interrupted = false;
            String message;
            while (!interrupted) {
                try {
                    if (reader.ready()) {
                        if ((message = reader.readLine()).startsWith(ServerInfo.COMMON_MESSAGE)) {
                            callback.onNewMessageReceived(message.replace(ServerInfo.COMMON_MESSAGE, ""));
                        }

                        if (message.startsWith(ServerInfo.NEW_USER_CONNECTED)) {
                            callback.onNewUserConnected(message.replace(ServerInfo.NEW_USER_CONNECTED, ""));
                        }

                        if (message.startsWith(ServerInfo.USER_DISCONNECTED)) {
                            callback.onUserDisconnected(message.replace(ServerInfo.USER_DISCONNECTED, ""));
                        }

                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }

                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    interrupted = true;
                }
            }
        });
        messageListenerThread.start();
    }

    void sendMessage(String message) {

        writer.println(ServerInfo.COMMON_MESSAGE + userName + " : " + message);
    }


    void tryLogin() throws ApplicationException {

        writer.println(ServerInfo.USER_NAME + userName);
        try {
            if ((reader.readLine()).equals(ServerInfo.USER_NAME_IS_FREE)) {
                callback.onSuccessfulConnect();
                startListeningThread();
            } else {
                throw new ApplicationException("Пользователь с таким именем уже подключен");
            }
        } catch (IOException e) {
            throw new ApplicationException("Проблемы с подключением");
        }
    }

    String[] getRecentMessages() throws IOException {
        String recentMessages;
        writer.println(ServerInfo.GET_RECENT_MESSAGES);
        if ((recentMessages = reader.readLine()).startsWith(ServerInfo.RECENT_MESSAGES)) {
            recentMessages = recentMessages.replace(ServerInfo.RECENT_MESSAGES, "");
            return recentMessages.split(";");
        }
        return null;
    }

    String[] getUserList() throws IOException {
        String userList;
        writer.println(ServerInfo.GET_USER_LIST);
        if ((userList = reader.readLine()).startsWith(ServerInfo.USERS_LIST)) {
            return (userList.replace(ServerInfo.USERS_LIST, "").split(";"));
        }
        return null;
    }

    void disconnect() throws IOException {
        writer.println(ServerInfo.DISCONNECT);
        messageListenerThread.interrupt();
        socket.close();
    }

    String getUserName() {
        return userName;
    }


}
