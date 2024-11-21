package com.nhom3_221404.module;

import com.google.inject.AbstractModule;

public class MyModule extends AbstractModule {
    @Override
    protected void configure() {
        install(new MySqlModule());

    }
}
