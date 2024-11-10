package com.nhom3_221404.usecase;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

import java.time.LocalDate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.github.javafaker.Faker;
import com.nhom3_221404.common.Errors;
import com.nhom3_221404.database.CreateInvoiceDAOMySql;
import com.nhom3_221404.dto.CreateInvoiceInputDTO;
import com.nhom3_221404.entity.Invoice;
import com.nhom3_221404.entity.InvoiceDaily;
import com.nhom3_221404.entity.InvoiceHourly;
import com.nhom3_221404.ui.presenter.CreateInvoicePresenter;
import com.nhom3_221404.usecase.CreateInvoice.CreateInvoiceInputBoundary;
import com.nhom3_221404.usecase.CreateInvoice.CreateInvoiceUseCase;
import com.nhom3_221404.util.InvoiceFactory;
import com.nhom3_221404.util.InvoiceIdGenerator;

@ExtendWith(MockitoExtension.class)
public class CreateInvoiceUseCaseTest {
    InvoiceFactory invoiceFactory;
    CreateInvoicePresenter presenter;
    CreateInvoiceInputBoundary createInvoiceUC;

    @Mock
    CreateInvoiceDAOMySql database;

    @BeforeEach
    void setUp() {
        invoiceFactory = new InvoiceFactory(new Faker());
        presenter = new CreateInvoicePresenter();
        createInvoiceUC = new CreateInvoiceUseCase(presenter, database, new InvoiceIdGenerator());
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

        when(database.createInvoice(ArgumentMatchers.any(Invoice.class)))
            .thenReturn(true);

        createInvoiceUC.execute(request);
        assertTrue(presenter.isSuccessCreateInvoice());
    }

    @Test
    void testCreateInvoice_invoiceMoreThan30RentalHours() {
        Invoice mockI = invoiceFactory.seedRandomInvoice();
        mockI.setBilledDate(LocalDate.now());
        CreateInvoiceInputDTO request = convertToMockRequest(mockI);
        request.setInvoiceType("hourly");
        request.setRentalHours(31);

        createInvoiceUC.execute(request);
        assertFalse(presenter.isSuccessCreateInvoice());
        assertEquals(presenter.getError(), Errors.RentalHoursOutOfRange);
    }

    @Test
    void testCreateInvoice_billedDateOutOfRange() {
        Invoice mockI = invoiceFactory.seedRandomInvoice();
        CreateInvoiceInputDTO request = convertToMockRequest(mockI);
        request.setBilledDate(LocalDate.of(2022, 3, 1));

        createInvoiceUC.execute(request);
        assertFalse(presenter.isSuccessCreateInvoice());
        assertEquals(presenter.getError(), Errors.DateOutOfRange);         
    }

    @Test
    void testCreateInvoice_dataAccessError() {
        Invoice mockI = invoiceFactory.seedRandomInvoice();
        mockI.setBilledDate(LocalDate.now());
        CreateInvoiceInputDTO request = convertToMockRequest(mockI);

        when(database.createInvoice(ArgumentMatchers.any(Invoice.class)))
            .thenReturn(false);

        createInvoiceUC.execute(request);
        assertFalse(presenter.isSuccessCreateInvoice());
        assertEquals(presenter.getError(), Errors.InternalDataAccess);   
    }
}
