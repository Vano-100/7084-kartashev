package ru.cft.focusstart.kartashev;

import java.io.*;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

class Client {

    private String userName;
    private Socket socket;
    private BufferedReader reader;
    private PrintWriter writer;

    Client(Socket socket) throws IOException {

        this.socket = socket;
        reader = new BufferedReader(new InputStreamReader(socket.getInputStream(), StandardCharsets.UTF_8));
        writer = new PrintWriter(new OutputStreamWriter(
                socket.getOutputStream(), StandardCharsets.UTF_8), true);
    }

    String getUserName() {
        return userName;
    }

    void setUserName(String userName) {
        this.userName = userName;
    }

    Socket getSocket() {
        return socket;
    }

    BufferedReader getReader() {
        return reader;
    }

    PrintWriter getWriter() {
        return writer;
    }


}
