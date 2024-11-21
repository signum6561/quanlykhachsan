
package com.nhom3_221404.usecase.GetInvoiceTypes;

import java.util.ArrayList;
import java.util.List;

import com.nhom3_221404.dto.InvoiceTypeOutputDTO;
import com.nhom3_221404.dto.GetInvoiceTypesResponse;
import com.nhom3_221404.entity.InvoiceType;

public class GetInvoiceTypesUseCase implements GetInvoiceTypesInputBoundary {
    private final GetInvoiceTypesDatabaseBoundary getInvoiceTypesDatabaseB;
    private final GetInvoiceTypesOutputBoundary getInvoiceTypesOutputB;

    public GetInvoiceTypesUseCase(GetInvoiceTypesDatabaseBoundary getInvoiceTypesDatabaseB,
            GetInvoiceTypesOutputBoundary getInvoiceTypesOutputB) {
        this.getInvoiceTypesDatabaseB = getInvoiceTypesDatabaseB;
        this.getInvoiceTypesOutputB = getInvoiceTypesOutputB;
    }

    @Override
    public void execute() {
        List<InvoiceType> invoiceTypes = getInvoiceTypesDatabaseB.getInvoiceTypes();
        if(invoiceTypes == null) {
            return;
        }
        
        List<InvoiceTypeOutputDTO> outputDTOs = new ArrayList<>();
        invoiceTypes.forEach(invoiceType -> {
            InvoiceTypeOutputDTO invoiceTypesDTO = new InvoiceTypeOutputDTO(invoiceType.getCode(), invoiceType.getName());
            outputDTOs.add(invoiceTypesDTO);
        });

        getInvoiceTypesOutputB.present(new GetInvoiceTypesResponse(outputDTOs));
    }
}
