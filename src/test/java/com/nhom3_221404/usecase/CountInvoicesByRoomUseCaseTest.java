package com.nhom3_221404.usecase;

import com.nhom3_221404.dto.CountInvoicesByRoomInputDTO;
import com.nhom3_221404.dto.CountInvoicesByRoomOutputDTO;
import com.nhom3_221404.usecase.CountInvoicesByRoom.CountInvoicesByRoomDatabaseBoundary;
import com.nhom3_221404.usecase.CountInvoicesByRoom.CountInvoicesByRoomOutputBoundary;
import com.nhom3_221404.usecase.CountInvoicesByRoom.CountInvoicesByRoomUseCase;
import com.nhom3_221404.common.Errors;
import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.*;

public class CountInvoicesByRoomUseCaseTest {
    @Test
    public void testCountInvoicesByRoom() {
        CountInvoicesByRoomOutputBoundary outputBoundary = mock(CountInvoicesByRoomOutputBoundary.class);
        CountInvoicesByRoomDatabaseBoundary databaseBoundary = mock(CountInvoicesByRoomDatabaseBoundary.class);
        CountInvoicesByRoomUseCase useCase = new CountInvoicesByRoomUseCase(outputBoundary, databaseBoundary);
        
        String roomId = "room1";
        CountInvoicesByRoomInputDTO inputDTO = new CountInvoicesByRoomInputDTO(roomId);
        when(databaseBoundary.countInvoicesByRoom(roomId)).thenReturn(5);


        useCase.execute(inputDTO);


        verify(outputBoundary).presentResult(new CountInvoicesByRoomOutputDTO(roomId, 5));
    }

    @Test
    public void testCountInvoicesByRoomInternalError() {

        CountInvoicesByRoomOutputBoundary outputBoundary = mock(CountInvoicesByRoomOutputBoundary.class);
        CountInvoicesByRoomDatabaseBoundary databaseBoundary = mock(CountInvoicesByRoomDatabaseBoundary.class);
        CountInvoicesByRoomUseCase useCase = new CountInvoicesByRoomUseCase(outputBoundary, databaseBoundary);
        
        String roomId = "room1";
        CountInvoicesByRoomInputDTO inputDTO = new CountInvoicesByRoomInputDTO(roomId);
        when(databaseBoundary.countInvoicesByRoom(roomId)).thenReturn(-1); 


        useCase.execute(inputDTO);

        verify(outputBoundary).presentError(Errors.InternalDataAccess);
    }
}