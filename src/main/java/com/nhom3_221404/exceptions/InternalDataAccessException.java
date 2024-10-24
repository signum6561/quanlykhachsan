package com.nhom3_221404.exceptions;

import com.nhom3_221404.constant.StringConst;

public class InternalDataAccessException extends RuntimeException {
    public InternalDataAccessException() {
        super(StringConst.INTERNAL_DATA_ACCESS_ERROR);
    }
}
