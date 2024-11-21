package com.nhom3_221404.util;

import java.util.List;

public class Page<T> {
    private List<T> data;
    private int total;

    public Page() {
    }

    public Page(List<T> data, int total) {
        this.data = data;
        this.total = total;
    }

    public List<T> getData() {
        return this.data;
    }

    public void setData(List<T> data) {
        this.data = data;
    }

    public int getTotal() {
        return this.total;
    }

    public void setTotal(int total) {
        this.total = total;
    }
}
