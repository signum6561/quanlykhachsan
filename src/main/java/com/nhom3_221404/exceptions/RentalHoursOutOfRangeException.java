package com.nhom3_221404.exceptions;

import com.nhom3_221404.constant.StringConst;

public class RentalHoursOutOfRangeException extends Exception {
    public RentalHoursOutOfRangeException() {
        super(StringConst.RENTAL_HOURS_OUT_OF_RANGE);
    }
}
