package ru.cft.focusstart.kartashev;

import ru.cft.focusstart.kartashev.gui.ChatFrame;
import ru.cft.focusstart.kartashev.gui.EnterNameFrame;
import ru.cft.focusstart.kartashev.gui.ErrorFrame;

import java.io.IOException;

public class Application implements UICallback, ClientCallback {
    private EnterNameFrame enterNameFrame;
    private ChatFrame chatFrame;
    private Client client;

    public static void main(String[] args) {
        new Application();
    }


    private Application(){
        enterNameFrame = new EnterNameFrame(this);
    }

    @Override
    public void onConnectionInfoEntered(String name, String server) {
        try {
            this.client = new Client(name, server, this);
            client.tryLogin();
        } catch (ApplicationException e) {
            System.out.println(e.getMessage());
            new ErrorFrame(e.getMessage());
        }
    }

    @Override
    public void onConnectionProblems(String message) {
        new ErrorFrame(message);
    }

    @Override
    public void onSuccessfulConnect()  {
        enterNameFrame.dispose();
        chatFrame = new ChatFrame(this);
        try {
            chatFrame.setRecentMessages(client.getRecentMessages());
            chatFrame.setUsersList(client.getUserList());
        } catch (IOException e) {
            new ErrorFrame("Неизвестная ошибка");
        }
    }

    @Override
    public void onApplicationExit() {
        try {
            client.disconnect();
        } catch (IOException e) {
            new ErrorFrame("Неизвестная ошибка");
        }
    }

    @Override
    public void onMessageEntered(String message) {
            client.sendMessage(message);
    }

    @Override
    public void onNewMessageReceived(String message) {
        chatFrame.addMessage(message);
    }

    @Override
    public void onNewUserConnected(String userName) {
        if (!userName.equals(client.getUserName())) {
            chatFrame.addUserToList(userName);
        }
        chatFrame.addMessage("Подключился пользователь " + userName + ". Поприветствуем!");
    }

    @Override
    public void onUserDisconnected(String userName) {
        chatFrame.removeUserFromList(userName);
    }

}
