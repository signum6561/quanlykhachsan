package com.nhom3_221404.ui.controller;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import com.nhom3_221404.common.InvoiceType;
import com.nhom3_221404.database.ViewInvoiceListDAOMySql;
import com.nhom3_221404.database.repository.InvoiceRepository;
import com.nhom3_221404.database.repository.InvoiceRepositoryImpl;
import com.nhom3_221404.dto.ViewInvoiceOutputDTO;
import com.nhom3_221404.exceptions.InternalDataAccessException;
import com.nhom3_221404.ui.model.InvoiceVM;
import com.nhom3_221404.ui.presenter.ViewInvoiceListPresenter;
import com.nhom3_221404.usecase.ViewInvoiceList.ViewInvoiceListInputBoundary;
import com.nhom3_221404.usecase.ViewInvoiceList.ViewInvoiceListUseCase;
import com.nhom3_221404.util.IBatisUtil;
import com.nhom3_221404.util.TableUtil;

import java.time.format.DateTimeFormatter;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;

public class ViewInvoiceListController implements Initializable {

    @FXML
    private TableColumn<InvoiceVM, String> col_donGia;

    @FXML
    private TableColumn<InvoiceVM, String> col_hoTenKhachHang;

    @FXML
    private TableColumn<InvoiceVM, String> col_loaiHoaDon;

    @FXML
    private TableColumn<InvoiceVM, String> col_maHD;

    @FXML
    private TableColumn<InvoiceVM, String> col_ngayThanhToan;

    @FXML
    private TableColumn<InvoiceVM, String> col_thanhTien;

    @FXML
    private TableColumn<InvoiceVM, String> col_maPhong;

    @FXML
    private TableView<InvoiceVM> tb_invoice;

    ViewInvoiceListInputBoundary viewILInputB;
    ViewInvoiceListPresenter viewILPresenter;
    ViewInvoiceListDAOMySql viewILDAOMySql;

    DateTimeFormatter formatter;
    

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        InvoiceRepository invoiceRepository = new InvoiceRepositoryImpl(IBatisUtil.buildSqlSessionFactory());
        viewILDAOMySql = new ViewInvoiceListDAOMySql(invoiceRepository);
        viewILPresenter = new ViewInvoiceListPresenter();
        viewILInputB = new ViewInvoiceListUseCase(viewILPresenter, viewILDAOMySql);
        formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");

        col_maHD.setCellValueFactory(new PropertyValueFactory<>("id"));
        col_hoTenKhachHang.setCellValueFactory(new PropertyValueFactory<>("customerName"));
        col_loaiHoaDon.setCellValueFactory(new PropertyValueFactory<>("invoiceType"));
        col_donGia.setCellValueFactory(new PropertyValueFactory<>("price"));
        col_ngayThanhToan.setCellValueFactory(new PropertyValueFactory<>("billedDate"));
        col_thanhTien.setCellValueFactory(new PropertyValueFactory<>("total"));
        col_maPhong.setCellValueFactory(new PropertyValueFactory<>("roomId"));
        fetchInvoiceTable();
    }

    private void fetchInvoiceTable() {
        try {
            viewILInputB.execute();
            List<ViewInvoiceOutputDTO> invoiceData = viewILPresenter.getOutputDTOList();
            List<InvoiceVM> invoiceVMs = convertDtoToVM(invoiceData);
            ObservableList<InvoiceVM> invoiceList = FXCollections.observableList(invoiceVMs);
            tb_invoice.setItems(invoiceList);
            TableUtil.autoResizeColumns(tb_invoice);
        } catch(InternalDataAccessException e) {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setContentText(e.getMessage());
        }
    }

    private List<InvoiceVM> convertDtoToVM(List<ViewInvoiceOutputDTO> dtoList) {
        return dtoList.stream()
                .map(viewILDto -> convertToInvoiceVM(viewILDto))
                .toList();
    }

    private InvoiceVM convertToInvoiceVM(ViewInvoiceOutputDTO dto) {
        return InvoiceVM.builder()
                    .id(dto.getId())
                    .customerName(dto.getCustomerName())
                    .price(String.format("%.2f", dto.getPrice()))
                    .invoiceType(localizeInvoiceType(dto.getInvoiceType()))
                    .billedDate(dto.getBilledDate().format(formatter))
                    .roomId(dto.getRoomId())
                    .total(String.format("%.2f", dto.getTotal()))
                    .build();
    }

    private String localizeInvoiceType(InvoiceType type) {
        switch (type) {
            case Daily:
                return "Theo ngày";
            case Hourly:    
                return "Theo giờ";
        }
        return null;
    }
}

