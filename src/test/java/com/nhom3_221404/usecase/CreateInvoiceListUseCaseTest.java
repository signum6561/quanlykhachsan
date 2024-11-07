package com.nhom3_221404.usecase;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import java.time.LocalDate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.github.javafaker.Faker;
import com.nhom3_221404.common.InvoiceType;
import com.nhom3_221404.database.CreateInvoiceDAOMySql;
import com.nhom3_221404.dto.CreateInvoiceInputDTO;
import com.nhom3_221404.dto.CreateInvoiceOutputDTO;
import com.nhom3_221404.entity.Invoice;
import com.nhom3_221404.entity.InvoiceDaily;
import com.nhom3_221404.entity.InvoiceHourly;
import com.nhom3_221404.exceptions.DateOutOfRangeException;
import com.nhom3_221404.exceptions.InternalDataAccessException;
import com.nhom3_221404.exceptions.RentalHoursOutOfRangeException;
import com.nhom3_221404.ui.presenter.CreateInvoicePresenter;
import com.nhom3_221404.usecase.CreateInvoice.CreateInvoiceInputBoundary;
import com.nhom3_221404.usecase.CreateInvoice.CreateInvoiceUseCase;
import com.nhom3_221404.util.InvoiceFactory;
import com.nhom3_221404.util.InvoiceIdGenerator;

@ExtendWith(MockitoExtension.class)
public class CreateInvoiceListUseCaseTest {
    InvoiceFactory invoiceFactory;
    CreateInvoicePresenter presenter;
    CreateInvoiceInputBoundary createInvoiceUC;
    Faker faker;

    @Mock
    CreateInvoiceDAOMySql database;

    @BeforeEach
    void setUp() {
        faker = new Faker();
        invoiceFactory = new InvoiceFactory(faker);
        presenter = new CreateInvoicePresenter();
        createInvoiceUC = new CreateInvoiceUseCase(presenter, database, new InvoiceIdGenerator());
    }

    private CreateInvoiceInputDTO convertToMockRequest(Invoice i) {
        CreateInvoiceInputDTO request = new CreateInvoiceInputDTO();
        request.setRoomId(i.getRoomId());
        request.setInvoiceType(i.getInvoiceType());
        request.setPrice(i.getPrice());
        request.setCustomerName(i.getCustomerName());
        request.setBilledDate(i.getBilledDate());
        switch (i.getInvoiceType()) {
            case Daily:
                request.setRentalDays(((InvoiceDaily)i).getRentalDays());
                break;
            case Hourly:
                request.setRentalHours(((InvoiceHourly)i).getRentalHours());
                break;
        }
        return request;
    }

    @Test
    void testCreateInvoice_valid() throws Exception {
        Invoice mockI = invoiceFactory.seedRandomInvoice();
        mockI.setBilledDate(LocalDate.now());
        CreateInvoiceInputDTO request = convertToMockRequest(mockI);

        when(database.addInvoice(ArgumentMatchers.any(Invoice.class)))
            .thenReturn(mockI);

        createInvoiceUC.execute(request);
        
        CreateInvoiceOutputDTO result = presenter.getInsertedInvoice();
        assertNotNull(result.getId());
        assertEquals(request.getCustomerName(), result.getCustomerName());
    }

    @Test
    void testCreateInvoice_invoiceMoreThan30RentalHours() {
        assertThrows(RentalHoursOutOfRangeException.class, () -> {
            Invoice mockI = invoiceFactory.seedRandomInvoice();
            mockI.setBilledDate(LocalDate.now());
            CreateInvoiceInputDTO request = convertToMockRequest(mockI);
            request.setInvoiceType(InvoiceType.Hourly);
            request.setRentalHours(31);
    
            createInvoiceUC.execute(request);
            presenter.getInsertedInvoice();
        });
    }

    @Test
    void testCreateInvoice_billedDateOutOfRange() {
        assertThrows(DateOutOfRangeException.class, () -> {
            Invoice mockI = invoiceFactory.seedRandomInvoice();
            CreateInvoiceInputDTO request = convertToMockRequest(mockI);
            request.setBilledDate(LocalDate.of(2022, 3, 1));

            createInvoiceUC.execute(request);
            presenter.getInsertedInvoice();            
        });
    }

    @Test
    void testCreateInvoice_dataAccessError() {
        assertThrows(InternalDataAccessException.class, () -> {
            Invoice mockI = invoiceFactory.seedRandomInvoice();
            mockI.setBilledDate(LocalDate.now());
            CreateInvoiceInputDTO request = convertToMockRequest(mockI);

            when(database.addInvoice(ArgumentMatchers.any(Invoice.class)))
                .thenReturn(null);

            createInvoiceUC.execute(request);
            presenter.getInsertedInvoice();
        });
    }
}
