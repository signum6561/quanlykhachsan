package com.nhom3_221404.ui.controller;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.nhom3_221404.dto.InvoiceOutputDTO;
import com.nhom3_221404.dto.ManageInvoiceRequest;
import com.nhom3_221404.dto.PageRequest;
import com.nhom3_221404.module.MyModule;
import com.nhom3_221404.ui.presenter.ManageInvoicePresenter;
import com.nhom3_221404.ui.util.TableUtil;
import com.nhom3_221404.ui.viewModel.ManageInvoiceViewModel;
import com.nhom3_221404.usecase.ManageInvoice.ManageInvoiceInputBoundary;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Pagination;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

public class ManageInvoiceController {

    @FXML
    private Button btn_add;

    @FXML
    private Button btn_delete;

    @FXML
    private Button btn_edit;

    @FXML
    private Button btn_refresh;

    @FXML
    private TableColumn<InvoiceOutputDTO, String> col_billedDate;

    @FXML
    private TableColumn<InvoiceOutputDTO, String> col_customerName;

    @FXML
    private TableColumn<InvoiceOutputDTO, String> col_donGia;

    @FXML
    private TableColumn<InvoiceOutputDTO, String> col_id;

    @FXML
    private TableColumn<InvoiceOutputDTO, String> col_invoiceType;

    @FXML
    private TableColumn<InvoiceOutputDTO, String> col_roomId;

    @FXML
    private Pagination pg_invoice;

    @FXML
    private ComboBox<Integer> sl_pageSize;

    @FXML
    private TableView<InvoiceOutputDTO> tb_invoice;

    @FXML
    private TextField txt_searchBar;

    
    public static final Integer[] PAGE_SIZE_OPTIONS = { 10, 15, 20 };
    private static final Integer DEFAULT_PAGE_SIZE = PAGE_SIZE_OPTIONS[0];
    private static final Integer DEFAULT_CURRENT_PAGE = 0;

    ManageInvoiceViewModel manageInvoiceViewModel;

    @FXML
    public void initialize() {
        manageInvoiceViewModel = new ManageInvoiceViewModel();
        Injector injector = Guice.createInjector(new MyModule());
        ManageInvoiceInputBoundary manageInvoiceInputB = injector.getInstance(ManageInvoiceInputBoundary.class);
        ManageInvoiceViewModel manageInvoiceViewModel = injector.getInstance(ManageInvoiceViewModel.class);

        sl_pageSize.getItems().addAll(PAGE_SIZE_OPTIONS);
        sl_pageSize.setValue(DEFAULT_PAGE_SIZE);

        col_id.setCellValueFactory(new PropertyValueFactory<>("id"));
        col_billedDate.setCellValueFactory(new PropertyValueFactory<>("billedDate"));
        col_customerName.setCellValueFactory(new PropertyValueFactory<>("customerName"));
        col_donGia.setCellValueFactory(new PropertyValueFactory<>("price"));
        col_invoiceType.setCellValueFactory(new PropertyValueFactory<>("invoiceType"));
        col_roomId.setCellValueFactory(new PropertyValueFactory<>("roomId"));

        pg_invoice.pageCountProperty().bind(manageInvoiceViewModel.pageCountProperty());
        
        tb_invoice.itemsProperty().bind(manageInvoiceViewModel.invoiceListProperty());

        tb_invoice.itemsProperty().addListener((observable, oldValue, newValue) -> {
            TableUtil.autoResizeColumns(tb_invoice);
        });

        btn_delete.disableProperty().bind(tb_invoice.getSelectionModel().selectedItemProperty().isNull());
        btn_edit.disableProperty().bind(tb_invoice.getSelectionModel().selectedItemProperty().isNull());

        ManageInvoiceRequest request = new ManageInvoiceRequest();
        request.setPageRequest(new PageRequest(1, 10));
        manageInvoiceInputB.execute(request);
    }

    @FXML
    void handleAdd(ActionEvent event) {

    }

    @FXML
    void handleDelete(ActionEvent event) {

    }

    @FXML
    void handleEdit(ActionEvent event) {

    }

    @FXML
    void handleRefresh(ActionEvent event) {

    }

}
