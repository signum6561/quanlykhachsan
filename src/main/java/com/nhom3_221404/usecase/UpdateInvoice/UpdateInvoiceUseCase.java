package com.nhom3_221404.usecase.UpdateInvoice;

import java.time.LocalDate;

import com.nhom3_221404.common.Errors;
import com.nhom3_221404.common.InvoiceType;
import com.nhom3_221404.dto.UpdateInvoiceInputDTO;
import com.nhom3_221404.dto.UpdateInvoiceOutputDTO;
import com.nhom3_221404.entity.Invoice;
import com.nhom3_221404.entity.InvoiceDaily;
import com.nhom3_221404.entity.InvoiceHourly;

public class UpdateInvoiceUseCase implements UpdateInvoiceInputBoundary {
    private UpdateInvoiceOutputBoundary updateOutputBoundary;
    private UpdateInvoiceDatabaseBoundary updateDatabaseBoundary;

    public UpdateInvoiceUseCase(UpdateInvoiceOutputBoundary updateOutputBoundary,
            UpdateInvoiceDatabaseBoundary updateDatabaseBoundary) {
        this.updateOutputBoundary = updateOutputBoundary;
        this.updateDatabaseBoundary = updateDatabaseBoundary;
    }

    @Override
    public void execute(UpdateInvoiceInputDTO updateInvoiceInputDTO) {
        String id = updateInvoiceInputDTO.getId();
        Invoice existingInvoice = updateDatabaseBoundary.getInvoiceById(id);

        if (existingInvoice == null) {
            updateOutputBoundary.presentError(Errors.InvoiceNotFound);
            return;
        }

        LocalDate billedDate = updateInvoiceInputDTO.getBilledDate();

        if (!isWithinTwelveMonths(billedDate)) {
            updateOutputBoundary.presentError(Errors.DateOutOfRange);
            return;
        }

        if (updateInvoiceInputDTO.getInvoiceType() == InvoiceType.Hourly) {
            int rentalHours = updateInvoiceInputDTO.getRentalHours();
            if (rentalHours > 30) {
                updateOutputBoundary.presentError(Errors.RentalHoursOutOfRange);
                return;
            }
        }

        updateInvoiceDetails(existingInvoice, updateInvoiceInputDTO);

        Invoice updatedInvoice = updateDatabaseBoundary.updateInvoice(existingInvoice);
        if (updatedInvoice == null) {
            updateOutputBoundary.presentError(Errors.InternalDataAccess);
            return;
        }
        UpdateInvoiceOutputDTO response = UpdateOutputDto(updatedInvoice);
        updateOutputBoundary.presentResult(response);
    }

    private void updateInvoiceDetails(Invoice existingInvoice, UpdateInvoiceInputDTO updateInvoiceInputDTO) {
        existingInvoice.setRoomId(updateInvoiceInputDTO.getRoomId());
        existingInvoice.setPrice(updateInvoiceInputDTO.getPrice());
        existingInvoice.setCustomerName(updateInvoiceInputDTO.getCustomerName());
        existingInvoice.setBilledDate(updateInvoiceInputDTO.getBilledDate());

        if (existingInvoice instanceof InvoiceHourly) {
            ((InvoiceHourly) existingInvoice).setRentalHours(updateInvoiceInputDTO.getRentalHours());
        } else if (existingInvoice instanceof InvoiceDaily) {
            ((InvoiceDaily) existingInvoice).setRentalDays(updateInvoiceInputDTO.getRentalDays());
        }
    }

    private UpdateInvoiceOutputDTO UpdateOutputDto(Invoice invoice) {
        UpdateInvoiceOutputDTO outputDto = new UpdateInvoiceOutputDTO();
        outputDto.setId(invoice.getId());
        outputDto.setRoomId(invoice.getRoomId());
        outputDto.setCustomerName(invoice.getCustomerName());
        outputDto.setPrice(invoice.getPrice());
        outputDto.setBilledDate(invoice.getBilledDate());
        outputDto.setTotal(invoice.getTotal());
        outputDto.setInvoiceType(invoice.getInvoiceType());
        return outputDto;
    }

    private boolean isWithinTwelveMonths(LocalDate date) {
        LocalDate now = LocalDate.now();
        LocalDate twelveMonthsAgo = now.minusMonths(12);
        return !date.isBefore(twelveMonthsAgo) && !date.isAfter(now);
    }
}