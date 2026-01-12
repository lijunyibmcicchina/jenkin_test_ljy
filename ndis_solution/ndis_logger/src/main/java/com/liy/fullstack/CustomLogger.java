package com.liy.fullstack;

public class CustomLogger {
    private static volatile CustomLogger instance = null;

    private CustomLogger() {
        //init some data config
    }

    public static CustomLogger getInstance() {
        if (instance == null) {
            synchronized (CustomLogger.class) {
                if (instance == null) {
                    instance = new CustomLogger();
                }
            }
        }
        return instance;
    }

    public void info(String message) {
        System.out.println("[INFO] " + message);
    }

    public void debug(String message) {
        System.out.println("[DEBUG] " + message);
    }

    public void error(String message) {
        System.out.println("[ERROR] " + message);
    }
}
