package com.nhom3_221404.usecase;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.time.LocalDate;
import java.time.LocalDateTime;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.nhom3_221404.common.InvoiceType;
import com.nhom3_221404.dto.GetInvoiceInputDTO;
import com.nhom3_221404.dto.GetInvoiceOutputDTO;
import com.nhom3_221404.entity.Invoice;
import com.nhom3_221404.ui.presenter.GetInvoicePresent;
import com.nhom3_221404.usecase.GetInvoice.GetInvoiceDatabaseBoundary;
import com.nhom3_221404.usecase.GetInvoice.GetInvoiceUseCase;

class GetInvoiceUseCaseTest {

    private GetInvoiceUseCase getInvoiceUseCase;
    private TestGetInvoiceDatabaseB databaseBoundary;
    private GetInvoicePresent outputBoundary;

    @BeforeEach
    public void setUp() {
        databaseBoundary = new TestGetInvoiceDatabaseB();
        outputBoundary = new GetInvoicePresent();
        getInvoiceUseCase = new GetInvoiceUseCase(databaseBoundary, outputBoundary);
    }

    @Test
    public void testGetInvoice() throws Exception {
        String invoiceId = "12345";

        GetInvoiceInputDTO inputDTO = new GetInvoiceInputDTO(invoiceId, "Room101",
                200.0, "John Doe",
                LocalDateTime.now(), InvoiceType.Daily, 3);

        getInvoiceUseCase.execute(inputDTO);

        GetInvoiceOutputDTO expectedOutputDTO = new GetInvoiceOutputDTO(invoiceId, InvoiceType.Daily, "Room101",
                200.0,
                "John Doe", LocalDate.now(), 600.0, 3, null);
        GetInvoiceOutputDTO invoiceOutputDTO = outputBoundary.getInvoice();
        assertNotNull(invoiceOutputDTO);
        assertEquals(expectedOutputDTO.getId(), invoiceOutputDTO.getId());
        assertEquals(expectedOutputDTO.getCustomerName(), invoiceOutputDTO.getCustomerName());
        assertEquals(expectedOutputDTO.getTotal(), invoiceOutputDTO.getTotal());

    }

    private static class TestGetInvoiceDatabaseB implements GetInvoiceDatabaseBoundary {

        @Override
        public Invoice getInvoice(String getInvoice) {
            Invoice invoice = new Invoice("12345", "Room101", 200.0, "John Doe", LocalDate.now()) {

                @Override
                public Double getTotal() {
                    return 600.0;
                }

                @Override
                public InvoiceType getInvoiceType() {
                    return InvoiceType.Daily;
                }
            };
            return invoice;
        }
    }
}
