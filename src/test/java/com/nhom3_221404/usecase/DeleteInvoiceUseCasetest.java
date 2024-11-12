package com.nhom3_221404.usecase;


import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import com.nhom3_221404.dto.DeleteInvoiceInputDTO;
import com.nhom3_221404.dto.DeleteInvoiceOutputDTO;
import com.nhom3_221404.usecase.DeleteInvoice.DeleteInvoiceDatabaseBoundary;
import com.nhom3_221404.usecase.DeleteInvoice.DeleteInvoiceInputBoundary;
import com.nhom3_221404.usecase.DeleteInvoice.DeleteInvoiceOutputBoundary;
import com.nhom3_221404.usecase.DeleteInvoice.DeleteInvoiceUseCase;

public class DeleteInvoiceUseCaseTest {
    @Mock
    private DeleteInvoiceDatabaseBoundary database;

    @Mock
    private DeleteInvoiceOutputBoundary presenter;

    private DeleteInvoiceInputBoundary deleteInvoiceUseCase;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        deleteInvoiceUseCase = new DeleteInvoiceUseCase(database, presenter);
    }
    @Test
    void execute_SuccessfulDeletion() {
        String invoiceId = "testId001";
        DeleteInvoiceInputDTO input = new DeleteInvoiceInputDTO(invoiceId);
        when(database.deleteInvoice(invoiceId)).thenReturn(true);

        deleteInvoiceUseCase.execute(input);

        verify(database).deleteInvoice(invoiceId);
        verify(presenter).presentDeleteResult(any(DeleteInvoiceOutputDTO.class));
    }
    @Test
    void execute_FailedDeletion() {
        String invoiceId = "testId002";
        DeleteInvoiceInputDTO input = new DeleteInvoiceInputDTO(invoiceId);
        when(database.deleteInvoice(invoiceId)).thenReturn(false);

        deleteInvoiceUseCase.execute(input);
        
        verify(database).deleteInvoice(invoiceId);
        verify(presenter).presentDeleteResult(any(DeleteInvoiceOutputDTO.class));
    }
}