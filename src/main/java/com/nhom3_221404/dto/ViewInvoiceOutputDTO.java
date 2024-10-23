package com.nhom3_221404.dto;

import java.time.LocalDateTime;

public class ViewInvoiceOutputDTO {
    protected String id;
    protected String roomId;
    protected Double price;
    protected String customerName;
    protected LocalDateTime billedDate;
    protected Double total;

    // #region builder
    public static class Builder {
        private String id;
        private String roomId;
        private Double price;
        private String customerName;
        private LocalDateTime billedDate;
        private Double total;

        public Builder() {
        }

        public Builder id(String id) {
            this.id = id;
            return this;
        }

        public Builder roomId(String roomId) {
            this.roomId = roomId;
            return this;
        }

        public Builder price(Double price) {
            this.price = price;
            return this;
        }

        public Builder customerName(String customerName) {
            this.customerName = customerName;
            return this;
        }

        public Builder billedDate(LocalDateTime billedDate) {
            this.billedDate = billedDate;
            return this;
        }

        public Builder total(Double total) {
            this.total = total;
            return this;
        }

        public ViewInvoiceOutputDTO build() {
            return new ViewInvoiceOutputDTO(this);
        }
    }

    private ViewInvoiceOutputDTO(Builder builder) {
        this.id = builder.id;
        this.roomId = builder.roomId;
        this.price = builder.price;
        this.customerName = builder.customerName;
        this.billedDate = builder.billedDate;
        this.total = builder.total;
    }

    public static Builder builder() {
        return new Builder();
    }

    public ViewInvoiceOutputDTO() {
    }
    // #endregion

    // #region getters and setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getRoomId() {
        return roomId;
    }

    public void setRoomId(String roomId) {
        this.roomId = roomId;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public LocalDateTime getBilledDate() {
        return billedDate;
    }

    public void setBilledDate(LocalDateTime billedDate) {
        this.billedDate = billedDate;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }
    // #endregion
}
