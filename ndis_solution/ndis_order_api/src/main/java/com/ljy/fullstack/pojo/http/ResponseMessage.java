package com.ljy.fullstack.pojo.http;

public class ResponseMessage<T> {
    private int code;
    private String message;
    private T data;

    public ResponseMessage(int code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public static <T> ResponseMessage<T> success(T data) {
        return new ResponseMessage<T>(200, "success", data);
    }

    public static <T> ResponseMessage<T> created(T data) {
        return new ResponseMessage<T>(201, "created", data);
    }

    public static <T> ResponseMessage<T> error(int code, String message) {
        return new ResponseMessage<T>(code, message, null);
    }

    public static <T> ResponseMessage<T> error(int code, String message, T data) {
        return new ResponseMessage<T>(code, message, data);
    }
}
