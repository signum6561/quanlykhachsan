package com.nhom3_221404.usecase;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.time.LocalDate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.nhom3_221404.dto.GetInvoiceOutputDTO;
import com.nhom3_221404.entity.Invoice;
import com.nhom3_221404.entity.InvoiceDaily;
import com.nhom3_221404.entity.InvoiceType;
import com.nhom3_221404.exceptions.InvoiceNotFoundException;
import com.nhom3_221404.ui.presenter.GetInvoicePresent;
import com.nhom3_221404.usecase.GetInvoice.GetInvoiceDatabaseBoundary;
import com.nhom3_221404.usecase.GetInvoice.GetInvoiceUseCase;

class GetInvoiceUseCaseTest {

    private GetInvoiceUseCase getInvoiceUseCase;
    private TestGetInvoiceDatabaseB databaseBoundary;
    private GetInvoicePresent getInvoicePresent;

    @BeforeEach
    public void setUp() {
        databaseBoundary = new TestGetInvoiceDatabaseB();
        getInvoicePresent = new GetInvoicePresent();
        getInvoiceUseCase = new GetInvoiceUseCase(databaseBoundary, getInvoicePresent);
    }

    @Test
    public void testGetInvoice() throws InvoiceNotFoundException {
        InvoiceDaily invoiceDaily = new InvoiceDaily("1", "A101", 100.0, "John Doe", LocalDate.now(), 5);
        invoiceDaily.setInvoiceType(new InvoiceType("dl", "Daily"));
        databaseBoundary.setInvoice(invoiceDaily);
        getInvoiceUseCase.execute("1");
        GetInvoiceOutputDTO outputDTO = getInvoicePresent.getOutputDTO();
        assertEquals("1", outputDTO.getId());
        assertEquals("A101", outputDTO.getRoomId());
        assertEquals(100.0, outputDTO.getPrice());
        assertEquals("John Doe", outputDTO.getCustomerName());
        assertEquals(5, outputDTO.getRentalDays());
        assertNull(outputDTO.getRentalHours());
    }

    private static class TestGetInvoiceDatabaseB implements GetInvoiceDatabaseBoundary {

        private Invoice invoice;

        public void setInvoice(Invoice invoice) {
            this.invoice = invoice;
        }

        @Override
        public Invoice getInvoice(String getInvoice) {
            return invoice;
        }

    }
}