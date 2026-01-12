package com.ljy.fullstack.pojo.notification;

public class NotificationFactory {
    public static Notification createNotification(String type) {
        if (type.equalsIgnoreCase("EMAIL")) {
            return new EmailNotification();
        } else if (type.equalsIgnoreCase("SMS")) {
            return new SMSNotification();
        } else {
            throw new IllegalArgumentException("Invalid notification type: " + type);
        }
    }
}
