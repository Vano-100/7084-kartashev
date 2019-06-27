package ru.cft.focusstart.kartashev;

public interface UICallback {

    void onConnectionInfoEntered(String name, String server);

    void onApplicationExit();

    void onMessageEntered(String message);

}
