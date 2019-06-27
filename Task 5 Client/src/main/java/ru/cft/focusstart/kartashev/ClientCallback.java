package ru.cft.focusstart.kartashev;

public interface ClientCallback {
    void onConnectionProblems(String message);

    void onSuccessfulConnect();

    void onNewMessageReceived(String message);
}
