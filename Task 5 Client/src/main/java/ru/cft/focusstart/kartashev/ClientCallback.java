package ru.cft.focusstart.kartashev;

public interface ClientCallback {
    void onConnectionProblems(String message);

    void onSuccessfulConnect();

    void onNewMessageReceived(String message);

    void onNewUserConnected(String userName);

    void onUserDisconnected(String userName);
}
