package com.nhom3_221404.usecase;

import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.nhom3_221404.dto.CountInvoicesByTypeInputDTO;
import com.nhom3_221404.usecase.CountInvoicesByType.CountInvoicesByTypeDatabaseBoundary;
import com.nhom3_221404.usecase.CountInvoicesByType.CountInvoicesByTypeOutputBoundary;
import com.nhom3_221404.usecase.CountInvoicesByType.CountInvoicesByTypeUseCase;

@ExtendWith(MockitoExtension.class)
public class CountInvoicesByTypeUseCaseTest {

    @Mock
    private CountInvoicesByTypeDatabaseBoundary database;

    @Mock
    private CountInvoicesByTypeOutputBoundary outputBoundary;

    private CountInvoicesByTypeUseCase useCase;

    @BeforeEach
    public void setUp() {
        useCase = new CountInvoicesByTypeUseCase(outputBoundary, database);
    }

    @Test
    public void testCountInvoicesByType_Success() {
        String invoiceType = "TypeA";
        when(database.countInvoicesByType(invoiceType)).thenReturn(5);

        CountInvoicesByTypeInputDTO inputDTO = new CountInvoicesByTypeInputDTO(invoiceType);
        useCase.execute(inputDTO);

        verify(outputBoundary).presentResult(argThat(dto -> dto.getInvoiceType().equals(invoiceType) && dto.getInvoiceCount() == 5));
    }

}