package com.nhom3_221404.ui.presenter;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.nhom3_221404.dto.ManageInvoiceResponse;
import com.nhom3_221404.ui.viewModel.ManageInvoiceViewModel;
import com.nhom3_221404.usecase.ManageInvoice.ManageInvoiceOutputBoundary;

@Singleton
public class ManageInvoicePresenter implements ManageInvoiceOutputBoundary {

    ManageInvoiceViewModel manageInvoiceViewModel;

    @Inject
    public ManageInvoicePresenter(ManageInvoiceViewModel manageInvoiceViewModel) {
        this.manageInvoiceViewModel = manageInvoiceViewModel;
    }

    @Override
    public void present(ManageInvoiceResponse response) {
        manageInvoiceViewModel.setPageCount(response.getLastPage());
        manageInvoiceViewModel.setInvoiceList(response.getInvoiceList());
    }
    
}
