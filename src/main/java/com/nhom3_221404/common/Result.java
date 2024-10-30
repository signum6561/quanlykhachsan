package com.nhom3_221404.common;

public class Result<T> {
    private T value;
    private Errors error;

    public Result() {
    }

    public Result(T value, Errors error) {
        this.value = value;
        this.error = error;
    }

    public void setSuccess(T value) {
        setValue(value);
        setError(null);
    }

    public void setFailure(Errors error) {
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
    public Errors getError() {
        return error;
    }
    public void setError(Errors error) {
        this.error = error;
    }
}
