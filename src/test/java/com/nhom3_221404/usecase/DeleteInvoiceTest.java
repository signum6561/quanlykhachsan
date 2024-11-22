package com.nhom3_221404.usecase;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import com.nhom3_221404.constant.StringConst;
import com.nhom3_221404.usecase.DeleteInvoice.DeleteInvoiceDatabaseBoundary;
import com.nhom3_221404.usecase.DeleteInvoice.DeleteInvoiceOutputBoundary;
import com.nhom3_221404.usecase.DeleteInvoice.DeleteInvoiceUseCase;

@ExtendWith(MockitoExtension.class)
public class DeleteInvoiceTest {
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
        deleteInvoiceUseCase.execute(invoiceId);
        verify(databaseBoundary, times(1)).deleteInvoice(invoiceId);
        verify(outputBoundary, times(1)).presentSuccess(StringConst.SUCCESS_DELETE_INVOICE);
    }
    @Test
    void execute_FailedDeletion() {
        String invoiceId = "testId002";
        doThrow(new RuntimeException()).when(databaseBoundary).deleteInvoice(any());
        
        deleteInvoiceUseCase.execute(invoiceId);
        verify(databaseBoundary, times(1)).deleteInvoice(invoiceId);
        verify(outputBoundary, times(1)).presentFailure(StringConst.FAILED_DELETE_INVOICE);
    }
}
