package com.nhom3_221404.usecase.UpdateInvoice;

import com.nhom3_221404.dto.UpdateInvoiceInputDTO;
import com.nhom3_221404.dto.UpdateInvoiceOutputDTO;
import com.nhom3_221404.entity.Invoice;
import com.nhom3_221404.exceptions.InternalDataAccessException;

public class UpdateInvoiceUseCase implements UpdateInvoiceInputBoundary {

    private final UpdateInvoiceOutputBoundary outputBoundary;
    private final UpdateInvoiceDatabaseBoundary database;

    public UpdateInvoiceUseCase(UpdateInvoiceOutputBoundary outputBoundary, UpdateInvoiceDatabaseBoundary database) {
        this.outputBoundary = outputBoundary;
        this.database = database;
    }

    @Override
    public void execute(UpdateInvoiceInputDTO inputDTO) {
        try {
            Invoice invoice = database.findInvoiceById(inputDTO.getId());
    
            if (invoice == null) {
                outputBoundary.presentError(new InternalDataAccessException("Invoice not found"));
                return;
            }
  
            invoice.setRoomId(inputDTO.getRoomId());
            invoice.setPrice(inputDTO.getPrice());
            invoice.setCustomerName(inputDTO.getCustomerName());
            invoice.setBilledDate(inputDTO.getBilledDate());
    
    
            Invoice updatedInvoice = database.updateInvoice(invoice);
    
            if (updatedInvoice == null) {
                outputBoundary.presentError(new InternalDataAccessException("Failed to update invoice"));
                return;
            }
    
     
            UpdateInvoiceOutputDTO outputDTO = new UpdateInvoiceOutputDTO(
                updatedInvoice.getId(),
                updatedInvoice.getRoomId(),
                updatedInvoice.getPrice(),
                updatedInvoice.getCustomerName(),
                updatedInvoice.getBilledDate(),
                updatedInvoice.getTotal()
            );
    

            outputBoundary.presentSuccess(outputDTO);
        } catch (RuntimeException e) {
            outputBoundary.presentError(new InternalDataAccessException("Database error: " + e.getMessage()));
        }
    }

}