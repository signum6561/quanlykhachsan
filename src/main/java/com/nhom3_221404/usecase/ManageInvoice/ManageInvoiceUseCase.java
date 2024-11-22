package com.nhom3_221404.usecase.ManageInvoice;

import com.google.inject.Inject;
import com.nhom3_221404.dto.InvoiceOutputDTO;
import com.nhom3_221404.dto.ManageInvoiceRequest;
import com.nhom3_221404.dto.ManageInvoiceResponse;
import com.nhom3_221404.dto.PageResponse;
import com.nhom3_221404.dto.PaginateInvoiceListRequest;
import com.nhom3_221404.usecase.DeleteInvoice.DeleteInvoiceInputBoundary;
import com.nhom3_221404.usecase.DisplayCreateFrm.DisplayCreateFrmInputBoundary;
import com.nhom3_221404.usecase.DisplayEditFrm.DisplayEditFrmInputBoundary;
import com.nhom3_221404.usecase.PaginateInvoiceList.PaginateInvoiceListInputBoundary;
import com.nhom3_221404.usecase.PaginateInvoiceList.PaginateInvoiceListOutputBoundary;
import com.nhom3_221404.usecase.ViewInvoice.ViewInvoiceInputBoundary;

public class ManageInvoiceUseCase implements ManageInvoiceInputBoundary {

    private final DisplayCreateFrmInputBoundary displayCreateFrmInputB;
    private final DisplayEditFrmInputBoundary displayEditFrmInputB;
    private final ViewInvoiceInputBoundary viewInvoiceInputB;
    private final PaginateInvoiceListInputBoundary paginateILInputB;
    private final PaginateInvoiceListOutputBoundary paginateILOutputB;
    private final DeleteInvoiceInputBoundary deleteInvoiceInputB;

    @Inject
    public ManageInvoiceUseCase(DisplayCreateFrmInputBoundary displayCreateFrmInputB,
            DisplayEditFrmInputBoundary displayEditFrmInputB, ViewInvoiceInputBoundary viewInvoiceInputB,
            PaginateInvoiceListInputBoundary paginateILInputB, PaginateInvoiceListOutputBoundary paginateILOutputB,
            DeleteInvoiceInputBoundary deleteInvoiceInputB) {
        this.displayCreateFrmInputB = displayCreateFrmInputB;
        this.displayEditFrmInputB = displayEditFrmInputB;
        this.viewInvoiceInputB = viewInvoiceInputB;
        this.paginateILInputB = paginateILInputB;
        this.paginateILOutputB = paginateILOutputB;
        this.deleteInvoiceInputB = deleteInvoiceInputB;
    }

    @Override
    public void execute(ManageInvoiceRequest request) {
        ManageInvoiceResponse response = new ManageInvoiceResponse();
        switch (request.getOperation()) {
            default:
                PaginateInvoiceListRequest r = new PaginateInvoiceListRequest();
                r.setPageRequest(request.getPageRequest());
                r.setPattern(request.getPattern());
                paginateILInputB.execute(r);
                PageResponse<InvoiceOutputDTO> paginatedResponse = paginateILOutputB.getPaginatedInvoices();
                response.setInvoiceList(paginatedResponse.getData());
                response.setLastPage(paginatedResponse.getLastPage());
                break;
            case CREATE_INVOICE:
                displayCreateFrmInputB.execute();
                break;
            case UPDATE_INVOICE:
                displayEditFrmInputB.execute(request.getPattern());
                break;
            case DELETE_INVOICE:
                deleteInvoiceInputB.execute(request.getInvoiceId());
                break;
            case VIEW_INVOICE:
                viewInvoiceInputB.execute(request.getInvoiceId());
                break;
        }
    }

}
