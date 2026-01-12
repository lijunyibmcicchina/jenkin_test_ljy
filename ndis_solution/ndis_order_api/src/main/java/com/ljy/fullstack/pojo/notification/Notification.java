package com.ljy.fullstack.pojo.notification;

public interface Notification {
    void send(String recipient, String message);
}
