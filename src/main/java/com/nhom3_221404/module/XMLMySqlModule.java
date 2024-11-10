package com.nhom3_221404.module;

import org.mybatis.guice.XMLMyBatisModule;

import com.nhom3_221404.database.repository.InvoiceRepository;
import com.nhom3_221404.database.repository.InvoiceRepositoryImpl;
import com.nhom3_221404.util.IBatisUtil;


public class XMLMySqlModule extends XMLMyBatisModule {
    
    @Override
    protected void initialize() {
        setEnvironmentId("development");
        addProperties(IBatisUtil.getProperties());

        bind(InvoiceRepository.class).to(InvoiceRepositoryImpl.class);
    }
}
