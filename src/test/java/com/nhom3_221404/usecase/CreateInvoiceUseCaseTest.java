package com.nhom3_221404.usecase;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;

import java.time.LocalDate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.github.javafaker.Faker;
import com.nhom3_221404.constant.StringConst;
import com.nhom3_221404.dto.CreateInvoiceInputDTO;
import com.nhom3_221404.entity.Invoice;
import com.nhom3_221404.entity.InvoiceDaily;
import com.nhom3_221404.entity.InvoiceHourly;
import com.nhom3_221404.exceptions.DateOutOfRangeException;
import com.nhom3_221404.exceptions.RentalHoursOutOfRangeException;
import com.nhom3_221404.usecase.CreateInvoice.CreateInvoiceDatabaseBoundary;
import com.nhom3_221404.usecase.CreateInvoice.CreateInvoiceInputBoundary;
import com.nhom3_221404.usecase.CreateInvoice.CreateInvoiceOutputBoundary;
import com.nhom3_221404.usecase.CreateInvoice.CreateInvoiceUseCase;
import com.nhom3_221404.usecase.GenerateId.GenerateIdInputBoundary;
import com.nhom3_221404.usecase.GenerateId.GenerateIdOutputBoundary;
import com.nhom3_221404.util.InvoiceFactory;

@ExtendWith(MockitoExtension.class)
public class CreateInvoiceUseCaseTest {
    InvoiceFactory invoiceFactory;

    @Mock
    CreateInvoiceOutputBoundary presenter;

    CreateInvoiceInputBoundary createInvoiceUC;

    @Mock
    CreateInvoiceDatabaseBoundary database;

    @Mock
    GenerateIdInputBoundary generateIdInput;

    @Mock
    GenerateIdOutputBoundary generateIdOutput;

    @BeforeEach
    void setUp() {
        invoiceFactory = new InvoiceFactory(new Faker());
        createInvoiceUC = new CreateInvoiceUseCase(presenter, database, generateIdInput, generateIdOutput);
    }

    private CreateInvoiceInputDTO convertToMockRequest(Invoice i) {
        CreateInvoiceInputDTO request = new CreateInvoiceInputDTO();
        request.setRoomId(i.getRoomId());
        request.setPrice(i.getPrice());
        request.setCustomerName(i.getCustomerName());
        request.setBilledDate(i.getBilledDate());
        String invoiceType = i.getInvoiceType().getName();
        switch (invoiceType.toLowerCase()) {
            case "daily":
                request.setRentalDays(((InvoiceDaily)i).getRentalDays());
                break;
            case "hourly":
                request.setRentalHours(((InvoiceHourly)i).getRentalHours());
                break;
            default:
                return null;
        }
        request.setInvoiceType(invoiceType);
        return request;
    }

    @Test
    void testCreateInvoice_valid() throws Exception {
        Invoice mockI = invoiceFactory.seedRandomInvoice();
        mockI.setBilledDate(LocalDate.now());
        CreateInvoiceInputDTO request = convertToMockRequest(mockI);

        createInvoiceUC.execute(request);

        verify(presenter).presentSuccess(StringConst.SUCCESS_CREATE_INVOICE);
    }

    @Test
    void testCreateInvoice_invoiceMoreThan30RentalHours() {
        Invoice mockI = invoiceFactory.seedRandomInvoice();
        mockI.setBilledDate(LocalDate.now());
        CreateInvoiceInputDTO request = convertToMockRequest(mockI);
        request.setInvoiceType("hourly");
        request.setRentalHours(31);

        createInvoiceUC.execute(request);

        verify(presenter).presentError(any(RentalHoursOutOfRangeException.class));
    }

    @Test
    void testCreateInvoice_billedDateOutOfRange() {
        Invoice mockI = invoiceFactory.seedRandomInvoice();
        CreateInvoiceInputDTO request = convertToMockRequest(mockI);
        request.setBilledDate(LocalDate.of(2022, 3, 1));

        createInvoiceUC.execute(request);
        
        verify(presenter).presentError(any(DateOutOfRangeException.class));
    }
}
