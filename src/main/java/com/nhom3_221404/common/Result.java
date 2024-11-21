package com.nhom3_221404.common;

public class Result<T> {
    private T value;
    private Exception error;

    public Result() {
    }

    public Result(T value, Exception error) {
        this.value = value;
        this.error = error;
    }

    public void setSuccess(T value) {
        setValue(value);
        setError(null);
    }

    public void setFailure(Exception error) {
        setValue(null);
        setError(error);
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
