package com.nhom3_221404.exceptions;

import com.nhom3_221404.constant.StringConst;

public class InvoiceNotFoundException extends Exception {
    public InvoiceNotFoundException(String id) {
        super(StringConst.INVOICE_NOT_FOUND(id));
    }
}
