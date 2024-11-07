package com.nhom3_221404.usecase.CountInvoicesByRoom;

import com.nhom3_221404.dto.CountInvoicesByRoomOutputDTO;
import com.nhom3_221404.common.Errors;

public interface CountInvoicesByRoomOutputBoundary {
    void presentError(Errors error);
    void presentResult(CountInvoicesByRoomOutputDTO dto);
}