package com.nhom3_221404.usecase.PaginateInvoiceList;

import java.util.ArrayList;
import java.util.List;

import com.google.inject.Inject;
import com.nhom3_221404.dto.FilterInvoiceListRequest;
import com.nhom3_221404.dto.FilterInvoiceListResponse;
import com.nhom3_221404.dto.InvoiceOutputDTO;
import com.nhom3_221404.dto.PageRequest;
import com.nhom3_221404.dto.PageResponse;
import com.nhom3_221404.dto.PaginateInvoiceListRequest;
import com.nhom3_221404.entity.Invoice;
import com.nhom3_221404.usecase.FilterInvoiceList.FilterInvoiceListInputBoundary;
import com.nhom3_221404.usecase.FilterInvoiceList.FilterInvoiceListOutputBoundary;
import com.nhom3_221404.util.Page;
import com.nhom3_221404.util.Pageable;

public class PaginateInvoiceListUseCase implements PaginateInvoiceListInputBoundary {

    private final PaginateInvoiceListDatabaseBoundary paginateILDatabaseB;
    private final PaginateInvoiceListOutputBoundary paginateILOutputB;
    private final FilterInvoiceListInputBoundary filterILInputB;
    private final FilterInvoiceListOutputBoundary filterILOutputB;

    @Inject
    public PaginateInvoiceListUseCase(PaginateInvoiceListDatabaseBoundary paginateILDatabaseB, PaginateInvoiceListOutputBoundary paginateILOutputB,
        FilterInvoiceListInputBoundary filterILInputB, FilterInvoiceListOutputBoundary filterILOutputB) {
        this.paginateILDatabaseB = paginateILDatabaseB;
        this.paginateILOutputB = paginateILOutputB;
        this.filterILInputB = filterILInputB;
        this.filterILOutputB = filterILOutputB;
    }

    @Override
    public void execute(PaginateInvoiceListRequest request) {
        PageRequest pageRequest = request.getPageRequest();
        Integer page = pageRequest.getPage();
        Integer pageSize = pageRequest.getPageSize();
        int totalItem;
        List<Invoice> invoiceList;
        if(request.getPattern() != null) {
            FilterInvoiceListRequest filterRequest = 
                new FilterInvoiceListRequest(pageRequest, request.getPattern());
            filterILInputB.execute(filterRequest);
            FilterInvoiceListResponse filterResponse = filterILOutputB.getFilteredInvoices();
            invoiceList = filterResponse.getData();
            totalItem = filterResponse.getTotal();
        } else {
            Pageable pageable = new Pageable(page, pageSize);
            Page<Invoice> paginateResult = paginateILDatabaseB.getPaginatedInvoices(pageable);
            invoiceList = paginateResult.getData();
            totalItem = paginateResult.getTotal();
        }

        List<InvoiceOutputDTO> outputDTOList = new ArrayList<>();
        invoiceList.forEach(invoice -> {
            InvoiceOutputDTO outputDTO = new InvoiceOutputDTO();
            outputDTO.setId(invoice.getId());
            outputDTO.setCustomerName(invoice.getCustomerName());
            outputDTO.setBilledDate(invoice.getBilledDate());
            outputDTO.setInvoiceType(invoice.getInvoiceType().getName());
            outputDTO.setRoomId(invoice.getRoomId());
            outputDTO.setPrice(invoice.getPrice());
            outputDTO.setTotal(invoice.getTotal());

            outputDTOList.add(outputDTO);
        });

        PageResponse<InvoiceOutputDTO> response = new PageResponse<>();
        response.setData(outputDTOList);
        response.setCurrentPage(page);
        response.setPageSize(pageSize);
        response.setTotal(totalItem);
        response.setLastPage(totalItem / pageSize + 1);

        paginateILOutputB.present(response);
    }
}
