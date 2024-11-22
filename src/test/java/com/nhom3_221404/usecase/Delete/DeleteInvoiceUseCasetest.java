package com.nhom3_221404.usecase.Delete;

import static org.mockito.Mockito.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import com.nhom3_221404.dto.DeleteInvoiceInputDTO;
import com.nhom3_221404.usecase.DeleteInvoice.DeleteInvoiceDatabaseBoundary;
import com.nhom3_221404.usecase.DeleteInvoice.DeleteInvoiceOutputBoundary;
import com.nhom3_221404.usecase.DeleteInvoice.DeleteInvoiceUseCase;

public class DeleteInvoiceUseCasetest {
    @Mock
    private DeleteInvoiceDatabaseBoundary databaseBoundary;
    @Mock
    private DeleteInvoiceOutputBoundary outputBoundary;
    private DeleteInvoiceUseCase deleteInvoiceUseCase;
    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        deleteInvoiceUseCase = new DeleteInvoiceUseCase(databaseBoundary, outputBoundary);
    }
    @Test
    void execute_SuccessfulDeletion() {
        String invoiceId = "testId001";
        DeleteInvoiceInputDTO input = new DeleteInvoiceInputDTO(invoiceId);
        when(databaseBoundary.deleteInvoice(invoiceId)).thenReturn(true);
        deleteInvoiceUseCase.execute(input);
        verify(databaseBoundary).deleteInvoice(invoiceId);
    }
    @Test
    void execute_FailedDeletion() {
        String invoiceId = "testId002";
        DeleteInvoiceInputDTO input = new DeleteInvoiceInputDTO(invoiceId);
        when(databaseBoundary.deleteInvoice(invoiceId)).thenReturn(false);
        deleteInvoiceUseCase.execute(input);
        verify(databaseBoundary).deleteInvoice(invoiceId);
    }
}