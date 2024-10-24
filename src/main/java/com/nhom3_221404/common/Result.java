package com.nhom3_221404.common;

public class Result<T> {
    private T value;
    private Exception error;

    public Result(T value, Exception error) {
        this.value = value;
        this.error = error;
    }

    public static <T> Result<T> success(T value) {
        return new Result<>(value, null);
    }

    public static <T> Result<T> failure(Exception error) {
        return new Result<>(null, error);
    }

    public boolean isSuccess() {
        return error == null;
    }

    public T getValue() {
        return value;
    }
    public void setValue(T value) {
        this.value = value;
    }
    public Exception getError() {
        return error;
    }
    public void setError(Exception error) {
        this.error = error;
    }
}
