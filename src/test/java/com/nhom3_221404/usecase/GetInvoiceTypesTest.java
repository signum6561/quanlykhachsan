package com.nhom3_221404.usecase;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.assertArg;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.nhom3_221404.common.Errors;
import com.nhom3_221404.entity.InvoiceType;
import com.nhom3_221404.usecase.GetInvoiceTypes.GetInvoiceTypesDatabaseBoundary;
import com.nhom3_221404.usecase.GetInvoiceTypes.GetInvoiceTypesInputBoundary;
import com.nhom3_221404.usecase.GetInvoiceTypes.GetInvoiceTypesOutputBoundary;
import com.nhom3_221404.usecase.GetInvoiceTypes.GetInvoiceTypesUseCase;

@ExtendWith(MockitoExtension.class)
public class GetInvoiceTypesTest {
    @Mock
    private GetInvoiceTypesDatabaseBoundary database;

    private GetInvoiceTypesInputBoundary getInvoiceTypesUC;

    @Mock
    private GetInvoiceTypesOutputBoundary presenter;
    
    @BeforeEach
    void setUp() {
        getInvoiceTypesUC = new GetInvoiceTypesUseCase(database, presenter);
    }

    @Test
    void testGetInvoiceTypes_valid() {
        List<InvoiceType> expected = new ArrayList<>();
        expected.add(new InvoiceType("daily", "Daily"));
        expected.add(new InvoiceType("hourly", "Hourly"));
        
        when(database.getInvoiceTypes()).thenReturn(expected);
        
        getInvoiceTypesUC.execute();

        verify(presenter).presentResult(assertArg(response -> {
            assertEquals(response.getInvoiceTypes().size(), 2);
        }));
    }

    @Test
    void testGetInvoiceTypes_dataAccessError() {
        when(database.getInvoiceTypes()).thenReturn(null);

        getInvoiceTypesUC.execute();

        verify(presenter).presentError(Errors.InternalDataAccess);
    }
}
