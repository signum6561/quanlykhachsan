package com.nhom3_221404.exceptions;

import com.nhom3_221404.constant.StringConst;

public class DateOutOfRangeException extends RuntimeException {
    public DateOutOfRangeException() {
        super(StringConst.BILLED_DATE_OUT_OF_RANGE);
    }
}
