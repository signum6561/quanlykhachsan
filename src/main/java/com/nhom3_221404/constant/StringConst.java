package com.nhom3_221404.constant;

public class StringConst {
    public static final String INTERNAL_DATA_ACCESS_ERROR = "Something is wrong...";
    public static final String PARAMETERIZED_INVOICE_NOT_FOUND = "Cannot found invoice '@id'";
    public static final String BILLED_DATE_OUT_OF_RANGE = "Billed date is out of 12 months from now";

    public static String INVOICE_NOT_FOUND(String id) {
        return PARAMETERIZED_INVOICE_NOT_FOUND.replaceAll("@id", id);
    }
}
