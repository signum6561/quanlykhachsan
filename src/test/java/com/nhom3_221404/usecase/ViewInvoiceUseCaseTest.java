package com.nhom3_221404.usecase;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.time.LocalDate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.nhom3_221404.dto.InvoiceOutputDTO;
import com.nhom3_221404.entity.InvoiceDaily;
import com.nhom3_221404.entity.InvoiceType;
import com.nhom3_221404.usecase.GetInvoice.GetInvoiceInputBoundary;
import com.nhom3_221404.usecase.GetInvoice.GetInvoiceOutputBoundary;
import com.nhom3_221404.usecase.ViewInvoice.ViewInvoiceOutputBoundary;
import com.nhom3_221404.usecase.ViewInvoice.ViewInvoiceUseCase;
import com.nhom3_221404.dto.ViewInvoiceResponse;

public class ViewInvoiceUseCaseTest {

    private ViewInvoiceUseCase viewInvoiceUseCase;
    private FakeViewInvoiceOutputBoundary viewInvoiceOutputBoundary;
    private FakeGetInvoiceInputBoundary getInvoiceInputBoundary;
    private FakeGetInvoiceOutputBoundary getInvoiceOutputBoundary;

    @BeforeEach
    void setUp() {
        viewInvoiceOutputBoundary = new FakeViewInvoiceOutputBoundary();
        getInvoiceInputBoundary = new FakeGetInvoiceInputBoundary();
        getInvoiceOutputBoundary = new FakeGetInvoiceOutputBoundary();
        viewInvoiceUseCase = new ViewInvoiceUseCase(viewInvoiceOutputBoundary, getInvoiceInputBoundary,
                getInvoiceOutputBoundary);
    }

    @Test
    void testViewInvoice() {
        String invoiceId = "1";
        String roomId = "A101";
        double price = 100.0;
        String customeName = "John Doe";
        int rentalDays = 5;
        InvoiceDaily invoiceDaily = new InvoiceDaily(invoiceId, roomId, price, customeName, LocalDate.now(),
                rentalDays);
        invoiceDaily.setInvoiceType(new InvoiceType("dl", "Daily"));

        InvoiceOutputDTO outputDTO = new InvoiceOutputDTO(invoiceId, "Daily", roomId, price, customeName,
                LocalDate.now(), 105.0, rentalDays, null);
        getInvoiceOutputBoundary.setInvoice(outputDTO);

        viewInvoiceUseCase.execute(invoiceId);
        ViewInvoiceResponse response = viewInvoiceOutputBoundary.getResponse();

        InvoiceOutputDTO responseInvoice = response.getInvoiceOutputDTO();
        assertEquals(invoiceId, responseInvoice.getId());
        assertEquals(roomId, responseInvoice.getRoomId());
        assertEquals(price, responseInvoice.getPrice());
        assertEquals(customeName, responseInvoice.getCustomerName());
        assertEquals(rentalDays, responseInvoice.getRentalDays());
        assertNull(responseInvoice.getRentalHours());
    }

    private static class FakeGetInvoiceInputBoundary implements GetInvoiceInputBoundary {
        @Override
        public void execute(String invoiceId) {
        }
    }

    private static class FakeGetInvoiceOutputBoundary implements GetInvoiceOutputBoundary {
        private InvoiceOutputDTO invoice;

        @Override
        public InvoiceOutputDTO getInvoice() {
            return invoice;
        }

        @Override
        public void present(InvoiceOutputDTO getInvoiceOutputDTO) {
        }

        public void setInvoice(InvoiceOutputDTO invoice) {
            this.invoice = invoice;
        }
    }

    private static class FakeViewInvoiceOutputBoundary implements ViewInvoiceOutputBoundary {
        private ViewInvoiceResponse response;

        @Override
        public void present(ViewInvoiceResponse response) {
            this.response = response;
        }

        public ViewInvoiceResponse getResponse() {
            return response;
        }
    }
}