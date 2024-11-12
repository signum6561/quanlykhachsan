package com.nhom3_221404.ui.presenter;

import java.util.List;

import com.nhom3_221404.dto.DisplayCreateFrmResponse;
import com.nhom3_221404.dto.GetInvoiceTypesOutputDTO;
import com.nhom3_221404.usecase.DisplayCreateFrm.DisplayCreateFrmOutputBoundary;

public class DisplayCreateFrmPresenter implements DisplayCreateFrmOutputBoundary {
    private List<GetInvoiceTypesOutputDTO> invoiceTypes;

    @Override
    public void present(DisplayCreateFrmResponse response) {
        invoiceTypes = response.getInvoiceTypes();
    }

}
