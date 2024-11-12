
package com.nhom3_221404.usecase.GetInvoiceTypes;

import java.util.ArrayList;
import java.util.List;

import com.nhom3_221404.common.Errors;
import com.nhom3_221404.dto.GetInvoiceTypesResponse;
import com.nhom3_221404.dto.GetInvoiceTypesOutputDTO;
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

        List<GetInvoiceTypesOutputDTO> outputDTOs = new ArrayList<>();

        if(invoiceTypes == null) {
            getInvoiceTypesOutputB.presentError(Errors.InternalDataAccess);
            return;
        }

        invoiceTypes.forEach(invoiceType -> {
            GetInvoiceTypesOutputDTO invoiceTypesDTO = new GetInvoiceTypesOutputDTO(invoiceType.getCode(), invoiceType.getName());
            outputDTOs.add(invoiceTypesDTO);
        });

        getInvoiceTypesOutputB.presentResult(new GetInvoiceTypesResponse(outputDTOs));
    }
}
