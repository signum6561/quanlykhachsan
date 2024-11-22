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

import com.nhom3_221404.dto.InvoiceTypeOutputDTO;
import com.nhom3_221404.usecase.DisplayCreateFrm.DisplayCreateFrmInputBoundary;
import com.nhom3_221404.usecase.DisplayCreateFrm.DisplayCreateFrmOutputBoundary;
import com.nhom3_221404.usecase.DisplayCreateFrm.DisplayCreateFrmUseCase;
import com.nhom3_221404.usecase.GetInvoiceTypes.GetInvoiceTypesInputBoundary;
import com.nhom3_221404.usecase.GetInvoiceTypes.GetInvoiceTypesOutputBoundary;

@ExtendWith(MockitoExtension.class)
public class DisplayCreateFrmTest {
    private DisplayCreateFrmInputBoundary displayCreateFrmUC;
    
    @Mock
    private DisplayCreateFrmOutputBoundary displayCreateFrmOutputB;

    @Mock
    private GetInvoiceTypesInputBoundary getInvoiceTypesInputB;

    @Mock
    private GetInvoiceTypesOutputBoundary getInvoiceTypesOutputB;

    @BeforeEach
    void setUp() {
        displayCreateFrmUC = new DisplayCreateFrmUseCase(displayCreateFrmOutputB, getInvoiceTypesInputB, getInvoiceTypesOutputB);
    }

    @Test
    void testDisplayCreateFrm_valid() {
        List<InvoiceTypeOutputDTO> expected = new ArrayList<>();
        expected.add(new InvoiceTypeOutputDTO("dl", "Daily"));
        expected.add(new InvoiceTypeOutputDTO("hl", "Hourly"));
        
        when(getInvoiceTypesOutputB.getInvoiceTypes()).thenReturn(expected);
        
        displayCreateFrmUC.execute();

        verify(displayCreateFrmOutputB).present(assertArg(response -> {
            assertEquals(response.getInvoiceTypes().size(), 2);
            assertEquals(response.getInvoiceTypes().get(0).getCode(), "dl");
        }));
    }
}
