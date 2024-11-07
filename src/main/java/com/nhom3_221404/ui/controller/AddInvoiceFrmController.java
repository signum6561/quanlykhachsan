package com.nhom3_221404.ui.controller;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.nhom3_221404.common.InvoiceType;
import com.nhom3_221404.database.CreateInvoiceDAOMySql;
import com.nhom3_221404.database.repository.InvoiceRepository;
import com.nhom3_221404.dto.CreateInvoiceInputDTO;
import com.nhom3_221404.ui.presenter.CreateInvoicePresenter;
import com.nhom3_221404.ui.util.Pair;
import com.nhom3_221404.usecase.CreateInvoice.CreateInvoiceInputBoundary;
import com.nhom3_221404.usecase.CreateInvoice.CreateInvoiceUseCase;
import com.nhom3_221404.util.InvoiceIdGenerator;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class AddInvoiceFrmController {

    @FXML
    private Button btn_reset;

    @FXML
    private Button btn_submit;

    @FXML
    private DatePicker dpk_ngayThanhToan;

    @FXML
    private HBox f_soGioThue;

    @FXML
    private HBox f_soNgayThue;

    @FXML
    private TextField txt_donGia;

    @FXML
    private TextField txt_hoTenKH;
    
    @FXML
    private ComboBox<Pair<String, InvoiceType>> sl_loaiHD;

    @FXML
    private TextField txt_maPhong;

    @FXML
    private TextField txt_soGioThue;

    @FXML
    private TextField txt_soNgayThue;

    CreateInvoiceInputBoundary createInvoiceInputB;
    CreateInvoicePresenter createInvoicePresenter;
    CreateInvoiceDAOMySql createInvoiceDB;

    private InvoiceType selectedInvoiceType;
    private Stage currentStage;


    @FXML
    public void initialize() {
        List<Pair<String, InvoiceType>> invoiceTypeSelectItems = new ArrayList<>();
        invoiceTypeSelectItems.add(new Pair<>("Theo ngày", InvoiceType.Daily));
        invoiceTypeSelectItems.add(new Pair<>("Theo giờ", InvoiceType.Hourly));

        sl_loaiHD.setItems(FXCollections.observableArrayList(invoiceTypeSelectItems));
        sl_loaiHD.getSelectionModel().selectFirst();;

        dpk_ngayThanhToan.setValue(LocalDate.now());
        handleReset();
    }

    public void initDB(InvoiceRepository invoiceRepository) {
        createInvoicePresenter = new CreateInvoicePresenter();
        createInvoiceDB = new CreateInvoiceDAOMySql(invoiceRepository);
        createInvoiceInputB = new CreateInvoiceUseCase(createInvoicePresenter, createInvoiceDB, new InvoiceIdGenerator());
    }

    @FXML
    void handleInvoiceTypeSelect() {
        selectedInvoiceType = sl_loaiHD.getValue().getValue();
        boolean isDailyType = selectedInvoiceType == InvoiceType.Daily;
        f_soGioThue.setVisible(!isDailyType);
        f_soNgayThue.setVisible(isDailyType);
    }

    @FXML
    void handleReset() {
        txt_maPhong.clear();
        txt_hoTenKH.clear();
        txt_donGia.clear();
        dpk_ngayThanhToan.setValue(LocalDate.now());
        txt_soGioThue.clear();
        txt_soNgayThue.clear();
        sl_loaiHD.getSelectionModel().selectFirst();
    }

    @FXML
    void handleSubmit() {
        try {
            CreateInvoiceInputDTO inputDTO = new CreateInvoiceInputDTO();
            inputDTO.setRoomId(txt_maPhong.getText());
            inputDTO.setCustomerName(txt_hoTenKH.getText());
            inputDTO.setInvoiceType(sl_loaiHD.getValue().getValue());
            inputDTO.setBilledDate(dpk_ngayThanhToan.getValue());
            inputDTO.setPrice(Double.parseDouble(txt_donGia.getText()));
            String soNgayThueStr = txt_soNgayThue.getText().trim();
            String soGioThueStr = txt_soGioThue.getText().trim();
            if(!soGioThueStr.isEmpty()) {
                inputDTO.setRentalHours(Integer.parseInt(txt_soGioThue.getText()));
            }
            if(!soNgayThueStr.isEmpty()) {
                inputDTO.setRentalDays(Integer.parseInt(txt_soNgayThue.getText()));
            }
            createInvoiceInputB.execute(inputDTO);

            currentStage = (Stage) btn_submit.getScene().getWindow();
            currentStage.close();
        } catch(Exception e) {
            e.printStackTrace();
        } 
    }

}
