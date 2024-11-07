package com.nhom3_221404.usecase.CountInvoicesByRoom;

import com.nhom3_221404.dto.CountInvoicesByRoomInputDTO;
import com.nhom3_221404.dto.CountInvoicesByRoomOutputDTO;
import com.nhom3_221404.common.Errors;

public class CountInvoicesByRoomUseCase implements CountInvoicesByRoomInputBoundary {
    private CountInvoicesByRoomOutputBoundary outputBoundary;
    private CountInvoicesByRoomDatabaseBoundary databaseBoundary;

    public CountInvoicesByRoomUseCase(CountInvoicesByRoomOutputBoundary outputBoundary,
                                       CountInvoicesByRoomDatabaseBoundary databaseBoundary) {
        this.outputBoundary = outputBoundary;
        this.databaseBoundary = databaseBoundary;
    }

    @Override
    public void execute(CountInvoicesByRoomInputDTO inputDTO) {
        String roomId = inputDTO.getRoomId ();
        int invoiceCount = databaseBoundary.countInvoicesByRoom(roomId);

        if (invoiceCount < 0) {
            outputBoundary.presentError(Errors.InternalDataAccess);
            return;
        }

        CountInvoicesByRoomOutputDTO outputDTO = new CountInvoicesByRoomOutputDTO(roomId, invoiceCount);
        outputBoundary.presentResult(outputDTO);
    }
}