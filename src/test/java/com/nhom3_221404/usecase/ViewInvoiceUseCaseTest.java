package com.nhom3_221404.usecase;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.time.LocalDateTime;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.nhom3_221404.common.InvoiceType;
import com.nhom3_221404.dto.ViewInvoiceInputDTO;
import com.nhom3_221404.dto.ViewInvoiceOutputDTO;
import com.nhom3_221404.entity.Invoice;
import com.nhom3_221404.entity.InvoiceDaily;
import com.nhom3_221404.usecase.ViewInvoice.ViewInvoiceDatabaseBoundary;
import com.nhom3_221404.usecase.ViewInvoice.ViewInvoiceOutputBoundary;
import com.nhom3_221404.usecase.ViewInvoice.ViewInvoiceUseCase;

class ViewInvoiceUseCaseTest {
    private ViewInvoiceDatabaseBoundary databaseBoundary;
    private TestViewInvoiceOutputBoundary outputBoundary;
    private ViewInvoiceUseCase viewInvoiceUseCase;

    @BeforeEach
    public void setUp() {
        databaseBoundary = mock(ViewInvoiceDatabaseBoundary.class);
        outputBoundary = new TestViewInvoiceOutputBoundary();
        viewInvoiceUseCase = new ViewInvoiceUseCase(databaseBoundary, outputBoundary);
    }

    @Test
    public void testViewInvoice() {

        LocalDateTime billedDate = LocalDateTime.now();
        ViewInvoiceInputDTO inputDTO = new ViewInvoiceInputDTO("12345", "A101", 150.0, "John Doe", billedDate,
                InvoiceType.Daily);
        Invoice invoice = new InvoiceDaily("12345", "A101", 150.0, "John Doe", LocalDate.now(), 5);

        when(databaseBoundary.viewInvoice("12345")).thenReturn(invoice);

        viewInvoiceUseCase.execute(inputDTO);

        ViewInvoiceOutputDTO outputDTO = outputBoundary.getViewInVoice();
        assertEquals("12345", outputDTO.getId());
        assertEquals(InvoiceType.Daily, outputDTO.getInvoiceType());
        assertEquals("A101", outputDTO.getRoomId());
        assertEquals(150.0, outputDTO.getPrice());
        assertEquals("John Doe", outputDTO.getCustomerName());
        assertEquals(LocalDate.now(), outputDTO.getBilledDate());
        assertEquals(5 * 150.0, outputDTO.getTotal());
    }

    private static class TestViewInvoiceOutputBoundary implements ViewInvoiceOutputBoundary {
        private ViewInvoiceOutputDTO viewInvoiceOutputDTO;

        @Override
        public void presentData(ViewInvoiceOutputDTO viewInvoiceOutputDTO) {
            this.viewInvoiceOutputDTO = viewInvoiceOutputDTO;
        }

        public ViewInvoiceOutputDTO getViewInVoice() {
            return viewInvoiceOutputDTO;
        }
    }

}
