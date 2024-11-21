package com.nhom3_221404.ui.util;

public class Pair<T, K> {
    private final T key;
    private final K value;

    public Pair(T key, K value) {
        this.key = key;
        this.value = value;
    }

    public T getKey() {
        return this.key;
    }

    public K getValue() {
        return this.value;
    }
    
    @Override
    public String toString() {
        return key.toString();
    }
}
