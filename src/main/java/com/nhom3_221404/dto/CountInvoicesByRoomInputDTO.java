package com.nhom3_221404.dto;

public class CountInvoicesByRoomInputDTO {
    private String roomId;

    public CountInvoicesByRoomInputDTO() {
    }

    public CountInvoicesByRoomInputDTO(String roomId) {
        this.roomId = roomId;
    }

    public String getRoomId() {
        return roomId;
    }

    public void setRoomId(String roomId) {
        this.roomId = roomId;
    }
}