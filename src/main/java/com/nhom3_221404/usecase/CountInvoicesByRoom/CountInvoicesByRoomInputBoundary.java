package com.nhom3_221404.usecase.CountInvoicesByRoom;

import com.nhom3_221404.dto.CountInvoicesByRoomInputDTO;

public interface CountInvoicesByRoomInputBoundary {
    void execute(CountInvoicesByRoomInputDTO inputDTO);
}