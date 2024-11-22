package com.nhom3_221404.module;


import com.google.inject.AbstractModule;
import com.google.inject.Scopes;
import com.google.inject.Singleton;
import com.nhom3_221404.ui.presenter.DeleteInvoicePresenter;
import com.nhom3_221404.ui.presenter.DisplayCreateFrmPresenter;
import com.nhom3_221404.ui.presenter.DisplayEditFrmPresenter;
import com.nhom3_221404.ui.presenter.FilterInvoiceListPresenter;
import com.nhom3_221404.ui.presenter.GetInvoicePresenter;
import com.nhom3_221404.ui.presenter.GetInvoiceTypesPresenter;
import com.nhom3_221404.ui.presenter.ManageInvoicePresenter;
import com.nhom3_221404.ui.presenter.PaginateInvoiceListPresenter;
import com.nhom3_221404.ui.presenter.UpdateInvoicePresenter;
import com.nhom3_221404.ui.presenter.ViewInvoicePresenter;
import com.nhom3_221404.ui.viewModel.ManageInvoiceViewModel;
import com.nhom3_221404.usecase.DeleteInvoice.DeleteInvoiceInputBoundary;
import com.nhom3_221404.usecase.DeleteInvoice.DeleteInvoiceOutputBoundary;
import com.nhom3_221404.usecase.DeleteInvoice.DeleteInvoiceUseCase;
import com.nhom3_221404.usecase.DisplayCreateFrm.DisplayCreateFrmInputBoundary;
import com.nhom3_221404.usecase.DisplayCreateFrm.DisplayCreateFrmOutputBoundary;
import com.nhom3_221404.usecase.DisplayCreateFrm.DisplayCreateFrmUseCase;
import com.nhom3_221404.usecase.DisplayEditFrm.DisplayEditFrmInputBoundary;
import com.nhom3_221404.usecase.DisplayEditFrm.DisplayEditFrmOutputBoundary;
import com.nhom3_221404.usecase.DisplayEditFrm.DisplayEditFrmUseCase;
import com.nhom3_221404.usecase.FilterInvoiceList.FilterInvoiceListInputBoundary;
import com.nhom3_221404.usecase.FilterInvoiceList.FilterInvoiceListOutputBoundary;
import com.nhom3_221404.usecase.FilterInvoiceList.FilterInvoiceListUseCase;
import com.nhom3_221404.usecase.GetInvoice.GetInvoiceInputBoundary;
import com.nhom3_221404.usecase.GetInvoice.GetInvoiceOutputBoundary;
import com.nhom3_221404.usecase.GetInvoice.GetInvoiceUseCase;
import com.nhom3_221404.usecase.GetInvoiceTypes.GetInvoiceTypesInputBoundary;
import com.nhom3_221404.usecase.GetInvoiceTypes.GetInvoiceTypesOutputBoundary;
import com.nhom3_221404.usecase.GetInvoiceTypes.GetInvoiceTypesUseCase;
import com.nhom3_221404.usecase.ManageInvoice.ManageInvoiceInputBoundary;
import com.nhom3_221404.usecase.ManageInvoice.ManageInvoiceOutputBoundary;
import com.nhom3_221404.usecase.ManageInvoice.ManageInvoiceUseCase;
import com.nhom3_221404.usecase.PaginateInvoiceList.PaginateInvoiceListInputBoundary;
import com.nhom3_221404.usecase.PaginateInvoiceList.PaginateInvoiceListOutputBoundary;
import com.nhom3_221404.usecase.PaginateInvoiceList.PaginateInvoiceListUseCase;
import com.nhom3_221404.usecase.UpdateInvoice.UpdateInvoiceInputBoundary;
import com.nhom3_221404.usecase.UpdateInvoice.UpdateInvoiceOutputBoundary;
import com.nhom3_221404.usecase.UpdateInvoice.UpdateInvoiceUseCase;
import com.nhom3_221404.usecase.ViewInvoice.ViewInvoiceInputBoundary;
import com.nhom3_221404.usecase.ViewInvoice.ViewInvoiceOutputBoundary;
import com.nhom3_221404.usecase.ViewInvoice.ViewInvoiceUseCase;

public class MyModule extends AbstractModule {
    @Override
    protected void configure() {
        install(new MySqlModule());


        bind(ManageInvoiceInputBoundary.class).to(ManageInvoiceUseCase.class);
        bind(ManageInvoiceOutputBoundary.class).to(ManageInvoicePresenter.class).in(Scopes.SINGLETON);
        bind(ManageInvoiceViewModel.class).in(Scopes.SINGLETON);

        bind(ViewInvoiceInputBoundary.class).to(ViewInvoiceUseCase.class);
        bind(ViewInvoiceOutputBoundary.class).to(ViewInvoicePresenter.class);

        bind(GetInvoiceInputBoundary.class).to(GetInvoiceUseCase.class);
        bind(GetInvoiceOutputBoundary.class).to(GetInvoicePresenter.class);

        bind(GetInvoiceTypesInputBoundary.class).to(GetInvoiceTypesUseCase.class);
        bind(GetInvoiceTypesOutputBoundary.class).to(GetInvoiceTypesPresenter.class);
        
        bind(DisplayCreateFrmInputBoundary.class).to(DisplayCreateFrmUseCase.class);
        bind(DisplayCreateFrmOutputBoundary.class).to(DisplayCreateFrmPresenter.class);

        bind(DisplayEditFrmInputBoundary.class).to(DisplayEditFrmUseCase.class);
        bind(DisplayEditFrmOutputBoundary.class).to(DisplayEditFrmPresenter.class);

        bind(PaginateInvoiceListInputBoundary.class).to(PaginateInvoiceListUseCase.class);
        bind(PaginateInvoiceListOutputBoundary.class).to(PaginateInvoiceListPresenter.class).in(Singleton.class);;

        bind(UpdateInvoiceInputBoundary.class).to(UpdateInvoiceUseCase.class);
        bind(UpdateInvoiceOutputBoundary.class).to(UpdateInvoicePresenter.class);

        bind(DeleteInvoiceInputBoundary.class).to(DeleteInvoiceUseCase.class);
        bind(DeleteInvoiceOutputBoundary.class).to(DeleteInvoicePresenter.class);

        bind(FilterInvoiceListInputBoundary.class).to(FilterInvoiceListUseCase.class);
        bind(FilterInvoiceListOutputBoundary.class).to(FilterInvoiceListPresenter.class);

    }
}
