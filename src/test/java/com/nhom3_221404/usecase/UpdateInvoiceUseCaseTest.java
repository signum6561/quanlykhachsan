package com.nhom3_221404.usecase;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import java.time.LocalDate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.github.javafaker.Faker;
import com.nhom3_221404.constant.StringConst;
import com.nhom3_221404.dto.UpdateInvoiceInputDTO;
import com.nhom3_221404.entity.Invoice;
import com.nhom3_221404.entity.InvoiceDaily;
import com.nhom3_221404.entity.InvoiceHourly;
import com.nhom3_221404.exceptions.DateOutOfRangeException;
import com.nhom3_221404.exceptions.RentalHoursOutOfRangeException;
import com.nhom3_221404.usecase.UpdateInvoice.UpdateInvoiceDatabaseBoundary;
import com.nhom3_221404.usecase.UpdateInvoice.UpdateInvoiceInputBoundary;
import com.nhom3_221404.usecase.UpdateInvoice.UpdateInvoiceOutputBoundary;
import com.nhom3_221404.usecase.UpdateInvoice.UpdateInvoiceUseCase;
import com.nhom3_221404.util.InvoiceFactory;

@ExtendWith(MockitoExtension.class)
public class UpdateInvoiceUseCaseTest {

    @Mock
    UpdateInvoiceOutputBoundary presenter;

    UpdateInvoiceInputBoundary updateInvoiceUC;

    InvoiceFactory invoiceFactory;

    @Mock
    UpdateInvoiceDatabaseBoundary database;

    @BeforeEach
    void setUp() {
        invoiceFactory = new InvoiceFactory(new Faker());
        updateInvoiceUC = new UpdateInvoiceUseCase(presenter, database);
    }

    private UpdateInvoiceInputDTO convertToMockRequest(Invoice i) {
        UpdateInvoiceInputDTO request = new UpdateInvoiceInputDTO();
        request.setRoomId(i.getRoomId());
        request.setPrice(i.getPrice());
        request.setCustomerName(i.getCustomerName());
        request.setBilledDate(i.getBilledDate());
        request.setInvoiceType(i.getInvoiceType().getName());
        switch (i.getType()) {
            case DAILY :
                request.setRentalDays(((InvoiceDaily)i).getRentalDays());
                break;
            case HOURLY:
                request.setRentalHours(((InvoiceHourly)i).getRentalHours());
                break;
            default:
                return null;
        }
        return request;
    }

    @Test
    void testUpdateInvoice_valid() {
        Invoice mockI = invoiceFactory.seedRandomInvoice();
        UpdateInvoiceInputDTO request = convertToMockRequest(mockI);
        updateInvoiceUC.execute(request);
        verify(presenter).presentResult(StringConst.SUCCESS_UPDATE_INVOICE);

    }

    @Test
    void testUpdateInvoice_invoiceMoreThan30RentalHours() {
        InvoiceHourly mockI = invoiceFactory.seedInvoiceHourly();
        UpdateInvoiceInputDTO request = convertToMockRequest(mockI);
        request.setInvoiceType("hourly");
        request.setRentalHours(31);

        updateInvoiceUC.execute(request);
        
        verify(presenter).presentError(any(RentalHoursOutOfRangeException.class));
    }

    
    @Test
    void testUpdateInvoice_billedDateOutOfRange() {
        Invoice mockI = invoiceFactory.seedRandomInvoice();
        UpdateInvoiceInputDTO request = convertToMockRequest(mockI);
        request.setBilledDate(LocalDate.of(2004, 3, 1));

        updateInvoiceUC.execute(request);
        
        verify(presenter).presentError(any(DateOutOfRangeException.class));
    }
} 