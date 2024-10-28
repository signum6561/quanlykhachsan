package com.nhom3_221404.common;

public class Result<T> {
    private T value;
    private RuntimeException error;

    public Result() {
    }

    public Result(T value, RuntimeException error) {
        this.value = value;
        this.error = error;
    }

    public void setSuccess(T value) {
        setValue(value);
        setError(null);
    }

    public void setFailure(RuntimeException error) {
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
    public RuntimeException getError() {
        return error;
    }
    public void setError(RuntimeException error) {
        this.error = error;
    }
}
