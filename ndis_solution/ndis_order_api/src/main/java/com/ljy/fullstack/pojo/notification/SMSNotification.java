package com.ljy.fullstack.pojo.notification;

public class SMSNotification implements Notification {
    @Override
    public void send(String recipient, String message) {
        // 发送电子邮件通知的逻辑
        System.out.println("SMS notification sent to: " + recipient + "：" + message);
    }
}
