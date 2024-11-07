package com.nhom3_221404.dto;

public class CountInvoicesByRoomOutputDTO {
    private String roomId;
    private int invoiceCount;

    public CountInvoicesByRoomOutputDTO() {
    }

    public CountInvoicesByRoomOutputDTO(String roomId, int invoiceCount) {
        this.roomId = roomId;
        this.invoiceCount = invoiceCount;
    }

    public String getRoomId() {
        return roomId;
    }

    public void setRoomId(String roomId) {
        this.roomId = roomId;
    }

    public int getInvoiceCount() {
        return invoiceCount;
    }

    public void setInvoiceCount(int invoiceCount) {
        this.invoiceCount = invoiceCount;
    }
}