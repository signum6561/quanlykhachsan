package com.nhom3_221404.module;

import org.mybatis.guice.XMLMyBatisModule;

import com.nhom3_221404.database.DeleteInvoiceDAOMySql;
import com.nhom3_221404.database.FilterInvoiceListDAOMySql;
import com.nhom3_221404.database.GetInvoiceDAOMySQL;
import com.nhom3_221404.database.GetInvoiceTypesDAOMySql;
import com.nhom3_221404.database.PaginateInvoiceListDAOMySql;
import com.nhom3_221404.database.UpdateInvoiceDAOMySQL;
import com.nhom3_221404.database.repository.InvoiceArchiver;
import com.nhom3_221404.database.repository.InvoiceArchiverImpl;
import com.nhom3_221404.database.repository.InvoiceRepository;
import com.nhom3_221404.database.repository.InvoiceRepositoryImpl;
import com.nhom3_221404.usecase.DeleteInvoice.DeleteInvoiceDatabaseBoundary;
import com.nhom3_221404.usecase.FilterInvoiceList.FilterInvoiceListDatabaseBoundary;
import com.nhom3_221404.usecase.GetInvoice.GetInvoiceDatabaseBoundary;
import com.nhom3_221404.usecase.GetInvoiceTypes.GetInvoiceTypesDatabaseBoundary;
import com.nhom3_221404.usecase.PaginateInvoiceList.PaginateInvoiceListDatabaseBoundary;
import com.nhom3_221404.usecase.UpdateInvoice.UpdateInvoiceDatabaseBoundary;
import com.nhom3_221404.util.IBatisUtil;


public class MySqlModule extends XMLMyBatisModule {
    
    @Override
    protected void initialize() {
        setEnvironmentId("development");
        addProperties(IBatisUtil.getProperties());

        bind(InvoiceRepository.class).to(InvoiceRepositoryImpl.class);
        bind(InvoiceArchiver.class).to(InvoiceArchiverImpl.class);
        bind(GetInvoiceDatabaseBoundary.class).to(GetInvoiceDAOMySQL.class);
        bind(DeleteInvoiceDatabaseBoundary.class).to(DeleteInvoiceDAOMySql.class);
        bind(FilterInvoiceListDatabaseBoundary.class).to(FilterInvoiceListDAOMySql.class);
        bind(PaginateInvoiceListDatabaseBoundary.class).to(PaginateInvoiceListDAOMySql.class);
        bind(GetInvoiceTypesDatabaseBoundary.class).to(GetInvoiceTypesDAOMySql.class);
        bind(UpdateInvoiceDatabaseBoundary.class).to(UpdateInvoiceDAOMySQL.class);
    }
}
