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

import com.nhom3_221404.dto.InvoiceOutputDTO;
import com.nhom3_221404.dto.InvoiceTypeOutputDTO;
import com.nhom3_221404.usecase.DisplayEditFrm.DisplayEditFrmInputBoundary;
import com.nhom3_221404.usecase.DisplayEditFrm.DisplayEditFrmOutputBoundary;
import com.nhom3_221404.usecase.DisplayEditFrm.DisplayEditFrmUseCase;
import com.nhom3_221404.usecase.GetInvoice.GetInvoiceInputBoundary;
import com.nhom3_221404.usecase.GetInvoice.GetInvoiceOutputBoundary;
import com.nhom3_221404.usecase.GetInvoiceTypes.GetInvoiceTypesInputBoundary;
import com.nhom3_221404.usecase.GetInvoiceTypes.GetInvoiceTypesOutputBoundary;

@ExtendWith(MockitoExtension.class)
public class DisplayEditFrmTest {    

    private DisplayEditFrmInputBoundary displayEditFrmUC;
    @Mock
    private DisplayEditFrmOutputBoundary displayEditFrmOutputB;

    @Mock
    private GetInvoiceTypesInputBoundary getInvoiceTypesInputB;

    @Mock
    private GetInvoiceTypesOutputBoundary getInvoiceTypesOutputB;

    @Mock
    private GetInvoiceInputBoundary getInvoiceInputB;

    @Mock
    private GetInvoiceOutputBoundary getInvoiceOutputB;

    @BeforeEach
    void setUp() {
        displayEditFrmUC= new DisplayEditFrmUseCase(
            displayEditFrmOutputB, getInvoiceTypesInputB, 
            getInvoiceTypesOutputB, getInvoiceInputB, getInvoiceOutputB);
    }

    @Test
    void testDisplayEditFrm_valid() {
        List<InvoiceTypeOutputDTO> expected = new ArrayList<>();
        expected.add(new InvoiceTypeOutputDTO("dl", "Daily"));
        expected.add(new InvoiceTypeOutputDTO("hl", "Hourly"));
        
        InvoiceOutputDTO expectedDetail = new InvoiceOutputDTO();
        expectedDetail.setPrice(100.00);
        expectedDetail.setCustomerName("John Doe");
        when(getInvoiceOutputB.getInvoice()).thenReturn(expectedDetail);
        when(getInvoiceTypesOutputB.getInvoiceTypes()).thenReturn(expected);
        
        displayEditFrmUC.execute("test");

        verify(displayEditFrmOutputB).present(assertArg(response -> {
            assertEquals(response.getInvoiceTypes().size(), 2);
            assertEquals(response.getInvoiceTypes().get(0).getCode(), "dl");
            assertEquals(response.getInvoiceDetails().getPrice(), 100.00);
            assertEquals(response.getInvoiceDetails().getCustomerName(), "John Doe");
        }));
    }
}
