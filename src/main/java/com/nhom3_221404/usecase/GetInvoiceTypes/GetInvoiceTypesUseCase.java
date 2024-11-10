
package com.nhom3_221404.usecase.GetInvoiceTypes;

import java.util.ArrayList;
import java.util.List;

import com.nhom3_221404.common.Errors;
import com.nhom3_221404.dto.GetInvoiceTypesResponse;
import com.nhom3_221404.dto.InvoiceTypesDTO;
import com.nhom3_221404.entity.InvoiceType;

public class GetInvoiceTypesUseCase implements GetInvoiceTypesInputBoundary {
    private GetInvoiceTypesDatabaseBoundary getInvoiceTypesDatabaseB;
    private GetInvoiceTypesOutputBoundary getInvoiceTypesOutputB;

    public GetInvoiceTypesUseCase(GetInvoiceTypesDatabaseBoundary getInvoiceTypesDatabaseB,
            GetInvoiceTypesOutputBoundary getInvoiceTypesOutputB) {
        this.getInvoiceTypesDatabaseB = getInvoiceTypesDatabaseB;
        this.getInvoiceTypesOutputB = getInvoiceTypesOutputB;
    }

    @Override
    public void execute() {
        List<InvoiceType> invoiceTypes = getInvoiceTypesDatabaseB.getInvoiceTypes();
        if(invoiceTypes == null) {
            getInvoiceTypesOutputB.present(Errors.InternalDataAccess);
            return;
        }
        List<InvoiceTypesDTO> outputDTOs = new ArrayList<>();

        invoiceTypes.forEach(invoiceType -> {
            InvoiceTypesDTO invoiceTypesDTO = new InvoiceTypesDTO(invoiceType.getCode(), invoiceType.getName());
            outputDTOs.add(invoiceTypesDTO);
        });

        getInvoiceTypesOutputB.present(new GetInvoiceTypesResponse(outputDTOs));
    }
}
