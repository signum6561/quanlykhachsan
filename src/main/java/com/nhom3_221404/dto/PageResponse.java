package com.nhom3_221404.dto;

import java.util.ArrayList;
import java.util.List;

public class PageResponse<T> {
    
    private List<T> data;
    private Integer pageSize;
    private Integer currentPage;
    private Integer lastPage;
    private Integer total;

    public PageResponse() {
        data = new ArrayList<>();
    }

    public PageResponse(List<T> data, Integer pageSize, Integer currentPage, Integer lastPage, Integer total) {
        this.data = data;
        this.pageSize = pageSize;
        this.currentPage = currentPage;
        this.lastPage = lastPage;
        this.total = total;
    }


    public List<T> getData() {
        return this.data;
    }

    public void setData(List<T> data) {
        this.data = data;
    }

    public Integer getPageSize() {
        return this.pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Integer getCurrentPage() {
        return this.currentPage;
    }

    public void setCurrentPage(Integer currentPage) {
        this.currentPage = currentPage;
    }

    public Integer getLastPage() {
        return this.lastPage;
    }

    public void setLastPage(Integer lastPage) {
        this.lastPage = lastPage;
    }

    public Integer getTotal() {
        return this.total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }
}