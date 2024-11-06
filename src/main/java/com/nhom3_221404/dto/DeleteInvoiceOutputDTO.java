package com.nhom3_221404.dto;

public class DeleteInvoiceOutputDTO {
    private boolean success;
    private String message;
    public DeleteInvoiceOutputDTO(boolean success, String message) {
        this.success = success;
        this.message = message;
    }
    public boolean isSuccess() {
        return success;
    }
    public String getMessage() {
        return message;
    }
}