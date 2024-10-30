package com.nhom3_221404.usecase.CreateInvoice;

import java.time.LocalDate;

import com.nhom3_221404.common.Errors;
import com.nhom3_221404.common.InvoiceType;
import com.nhom3_221404.dto.CreateInvoiceInputDTO;
import com.nhom3_221404.dto.CreateInvoiceOutputDTO;
import com.nhom3_221404.entity.Invoice;
import com.nhom3_221404.entity.InvoiceDaily;
import com.nhom3_221404.entity.InvoiceHourly;

public class CreateInvoiceUseCase implements CreateInvoiceInputBoundary {
    private CreateInvoiceOutputBoundary createIOutputB;
    private CreateInvoiceDatabaseBoundary createIDatabaseB;
    private IdGeneratorBoundary idGeneratorB;

    public CreateInvoiceUseCase(CreateInvoiceOutputBoundary createIOutputB,
            CreateInvoiceDatabaseBoundary createIDatabaseB, IdGeneratorBoundary idGeneratorB) {
        this.createIOutputB = createIOutputB;
        this.createIDatabaseB = createIDatabaseB;
        this.idGeneratorB = idGeneratorB;
    }

    @Override
    public void execute(CreateInvoiceInputDTO inputDTO) {
        InvoiceType invoiceType = inputDTO.getInvoiceType();
        LocalDate billedDate = inputDTO.getBilledDate();

        // Validate billed date
        if(!isWithinTwelveMonths(billedDate)) {
            createIOutputB.presentError(Errors.DateOutOfRange);
            return;
        }

        // Validate invoice type
        if (invoiceType == InvoiceType.Hourly) {
            int rentalHours = inputDTO.getRentalHours();
            if(rentalHours > 30) {
                createIOutputB.presentError(Errors.RentalHoursOutOfRange);
                return;
            }
        }

        Invoice invoice = convertToEntity(inputDTO);
        invoice.setId(idGeneratorB.generate());
        Invoice newInvoice = createIDatabaseB.addInvoice(invoice);

        if(newInvoice == null) {
            createIOutputB.presentError(Errors.InternalDataAccess);
            return;
        }

        CreateInvoiceOutputDTO outputDto = convertToOutputDto(newInvoice);
        createIOutputB.presentResult(outputDto);
    }

    private Invoice convertToEntity(CreateInvoiceInputDTO inputDto) {
        Invoice invoice = null;
        switch (inputDto.getInvoiceType()) {
            case Daily:
                invoice = new InvoiceDaily(inputDto.getRentalDays());
                break;
            case Hourly:
                invoice = new InvoiceHourly(inputDto.getRentalHours());
                break;
            default:
                return null;
        }
        invoice.setRoomId(inputDto.getRoomId());
        invoice.setPrice(inputDto.getPrice());
        invoice.setCustomerName(inputDto.getCustomerName());
        invoice.setBilledDate(inputDto.getBilledDate());
        return invoice;
    }

    private CreateInvoiceOutputDTO convertToOutputDto(Invoice invoice) {
        CreateInvoiceOutputDTO outputDto = new CreateInvoiceOutputDTO();
        switch (invoice.getInvoiceType()) {
            case Daily:
                outputDto.setRentalDays(((InvoiceDaily)invoice).getRentalDays());
                break;
            case Hourly:
                outputDto.setRentalHours(((InvoiceHourly)invoice).getRentalHours());
                break;
            default:
                return null;
        }
        outputDto.setId(invoice.getId());
        outputDto.setRoomId(invoice.getRoomId());
        outputDto.setCustomerName(invoice.getCustomerName());
        outputDto.setPrice(invoice.getPrice());
        outputDto.setBilledDate(invoice.getBilledDate());
        outputDto.setTotal(invoice.getTotal());
        outputDto.setInvoiceType(invoice.getInvoiceType());
        return outputDto;
    }

    public boolean isWithinTwelveMonths(LocalDate date) {
        LocalDate today = LocalDate.now();
        LocalDate twelveMonthsAgo = today.minusMonths(12);
        LocalDate nextTwelveMonth = today.plusMonths(12);

        return date.isAfter(twelveMonthsAgo) && date.isBefore(nextTwelveMonth);
    }
}
