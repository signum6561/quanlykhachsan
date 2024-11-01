package com.nhom3_221404.usecase;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.time.LocalDate;
import java.time.LocalDateTime;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.nhom3_221404.common.InvoiceType;
import com.nhom3_221404.dto.ViewInvoiceInputDTO;
import com.nhom3_221404.dto.ViewInvoiceOutputDTO;
import com.nhom3_221404.entity.Invoice;
import com.nhom3_221404.ui.presenter.ViewInvoicePresent;
import com.nhom3_221404.usecase.ViewInvoice.ViewInvoiceDatabaseBoundary;
import com.nhom3_221404.usecase.ViewInvoice.ViewInvoiceUseCase;

class ViewInvoiceUseCaseTest {

    private ViewInvoiceUseCase viewInvoiceUseCase;
    private TestViewInvoiceDatabaseB databaseBoundary;
    private ViewInvoicePresent outputBoundary;

    @BeforeEach
    public void setUp() {
        databaseBoundary = new TestViewInvoiceDatabaseB();
        outputBoundary = new ViewInvoicePresent();
        viewInvoiceUseCase = new ViewInvoiceUseCase(databaseBoundary, outputBoundary);
    }

    @Test
    public void testViewInvoice() throws Exception {
        String invoiceId = "12345";

        ViewInvoiceInputDTO inputDTO = new ViewInvoiceInputDTO(invoiceId, "Room101",
                200.0, "John Doe",
                LocalDateTime.now(), InvoiceType.Daily, 3);

        viewInvoiceUseCase.execute(inputDTO);

        ViewInvoiceOutputDTO expectedOutputDTO = new ViewInvoiceOutputDTO(invoiceId, InvoiceType.Daily, "Room101",
                200.0,
                "John Doe", LocalDate.now(), 600.0, 3, null);
        ViewInvoiceOutputDTO invoiceOutputDTO = outputBoundary.getViewInvoice();
        assertNotNull(invoiceOutputDTO);
        assertEquals(expectedOutputDTO.getId(), invoiceOutputDTO.getId());
        assertEquals(expectedOutputDTO.getCustomerName(), invoiceOutputDTO.getCustomerName());
        assertEquals(expectedOutputDTO.getTotal(), invoiceOutputDTO.getTotal());

    }

    private static class TestViewInvoiceDatabaseB implements ViewInvoiceDatabaseBoundary {
        @Override
        public Invoice viewInvoice(String viewInvoice) {
            Invoice invoice = new Invoice("12345", "Room101", InvoiceType.Daily, 200.0, "John Doe",
                    LocalDate.now()) {
                @Override
                public Double getTotal() {
                    return 600.0;
                }
            };
            return invoice;
        }
    }

}
