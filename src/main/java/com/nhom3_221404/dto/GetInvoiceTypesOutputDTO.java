package com.nhom3_221404.dto;

public class GetInvoiceTypesOutputDTO {
    private String code;
    private String name;

    public GetInvoiceTypesOutputDTO() {
    }

    public GetInvoiceTypesOutputDTO(String code, String name) {
        this.code = code;
        this.name = name;
    }

    public String getCode() {
        return code;
    }
    public void setCode(String code) {
        this.code = code;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
}
