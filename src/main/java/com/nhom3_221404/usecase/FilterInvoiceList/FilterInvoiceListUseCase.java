package com.nhom3_221404.usecase.FilterInvoiceList;


import com.nhom3_221404.dto.PageRequest;
import com.google.inject.Inject;
import com.nhom3_221404.dto.FilterInvoiceListRequest;
import com.nhom3_221404.dto.FilterInvoiceListResponse;
import com.nhom3_221404.entity.Invoice;
import com.nhom3_221404.util.Page;
import com.nhom3_221404.util.Pageable;

public class FilterInvoiceListUseCase implements FilterInvoiceListInputBoundary {
    private final FilterInvoiceListDatabaseBoundary filterILDatabaseB;
    private final FilterInvoiceListOutputBoundary filterILOutputB;

    @Inject
    public FilterInvoiceListUseCase(FilterInvoiceListDatabaseBoundary filterILDatabaseB, FilterInvoiceListOutputBoundary filterILOutputB) {
        this.filterILDatabaseB = filterILDatabaseB;
        this.filterILOutputB = filterILOutputB;
    }

    @Override
    public void execute(FilterInvoiceListRequest request) {
        PageRequest pageRequest = request.getPageRequest();
        Pageable pageable = new Pageable(
            pageRequest.getPage(), 
            pageRequest.getPageSize());
        
        Page<Invoice> filterResult = filterILDatabaseB.getFilteredInvoices(
            request.getPattern(), pageable);
        if(filterResult == null) {
            return;
        }

        FilterInvoiceListResponse response = new FilterInvoiceListResponse(
            filterResult.getData(), filterResult.getTotal());
        filterILOutputB.present(response);
    }

}
