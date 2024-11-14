// package com.nhom3_221404.ui.controller;

// import java.net.URL;
// import java.time.format.DateTimeFormatter;
// import java.util.List;
// import java.util.ResourceBundle;

// import com.google.inject.Guice;
// import com.google.inject.Injector;
// import com.nhom3_221404.database.SearchInvoiceDAOMySql;
// import com.nhom3_221404.database.ViewInvoiceListDAOMySql;
// import com.nhom3_221404.database.repository.InvoiceRepository;
// import com.nhom3_221404.database.repository.InvoiceRepositoryImpl;
// import com.nhom3_221404.dto.ViewInvoiceOutputDTO;
// import com.nhom3_221404.exceptions.InternalDataAccessException;
// import com.nhom3_221404.module.MyModule;
// import com.nhom3_221404.ui.model.InvoiceVM;
// import com.nhom3_221404.ui.presenter.SearchInvoicePresenter;
// import com.nhom3_221404.ui.presenter.GetInvoiceListPresenter;
// import com.nhom3_221404.ui.util.TableUtil;
// import com.nhom3_221404.usecase.GetInvoiceList.GetInvoiceListInputBoundary;
// import com.nhom3_221404.usecase.GetInvoiceList.GetInvoiceListUseCase;
// import com.nhom3_221404.usecase.SearchInvoice.SearchInvoiceInputBoundary;
// import com.nhom3_221404.usecase.SearchInvoice.SearchInvoiceUseCase;
// import com.nhom3_221404.util.IBatisUtil;

// import javafx.application.Platform;
// import javafx.collections.FXCollections;
// import javafx.collections.ObservableList;
// import javafx.event.ActionEvent;
// import javafx.fxml.FXML;
// import javafx.fxml.Initializable;
// import javafx.scene.control.Alert;
// import javafx.scene.control.TableColumn;
// import javafx.scene.control.TableView;
// import javafx.scene.control.TextField;
// import javafx.scene.control.Alert.AlertType;
// import javafx.scene.control.ButtonType;
// import javafx.scene.control.cell.PropertyValueFactory;
// import javafx.stage.Stage;

// public class MainController implements Initializable {

//     @FXML
//     private TextField searchBar;

//     @FXML
//     private TableColumn<InvoiceVM, String> col_donGia;

//     @FXML
//     private TableColumn<InvoiceVM, String> col_hoTenKhachHang;

//     @FXML
//     private TableColumn<InvoiceVM, String> col_loaiHoaDon;

//     @FXML
//     private TableColumn<InvoiceVM, String> col_maHD;

//     @FXML
//     private TableColumn<InvoiceVM, String> col_ngayThanhToan;

//     @FXML
//     private TableColumn<InvoiceVM, String> col_thanhTien;

//     @FXML
//     private TableColumn<InvoiceVM, String> col_maPhong;

//     @FXML
//     private TableView<InvoiceVM> tb_invoice;

//     GetInvoiceListInputBoundary viewILInputB;
//     GetInvoiceListPresenter viewILPresenter;
//     ViewInvoiceListDAOMySql viewILDAO;

//     SearchInvoiceInputBoundary searchInvoiceInputB;
//     SearchInvoicePresenter searchInvoicePresenterB;
//     SearchInvoiceDAOMySql searchInvoiceDAO;

//     DateTimeFormatter dateTimeFormatter;

//     @Override
//     public void initialize(URL location, ResourceBundle resources) {
//         Injector injector = Guice.createInjector(new MyModule());
//         InvoiceRepository invoiceRepository = injector.getInstance(InvoiceRepository.class);
//         viewILDAO = new ViewInvoiceListDAOMySql(invoiceRepository);
//         viewILPresenter = new GetInvoiceListPresenter();
//         viewILInputB = new GetInvoiceListUseCase(viewILPresenter, viewILDAO);
//         dateTimeFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

//         searchInvoicePresenterB = new SearchInvoicePresenter();
//         searchInvoiceDAO = new SearchInvoiceDAOMySql(invoiceRepository);
//         searchInvoiceInputB = new SearchInvoiceUseCase(searchInvoiceDAO, searchInvoicePresenterB);

//         col_maHD.setCellValueFactory(new PropertyValueFactory<>("id"));
//         col_hoTenKhachHang.setCellValueFactory(new PropertyValueFactory<>("customerName"));
//         col_loaiHoaDon.setCellValueFactory(new PropertyValueFactory<>("invoiceType"));
//         col_donGia.setCellValueFactory(new PropertyValueFactory<>("price"));
//         col_ngayThanhToan.setCellValueFactory(new PropertyValueFactory<>("billedDate"));
//         col_thanhTien.setCellValueFactory(new PropertyValueFactory<>("total"));
//         col_maPhong.setCellValueFactory(new PropertyValueFactory<>("roomId"));
//         fetchInvoiceTable();
//     }

//     private void fetchInvoiceTable() {
//         try {
//             viewILInputB.execute();
//             List<ViewInvoiceOutputDTO> invoiceData = viewILPresenter.getOutputDTOList();
//             List<InvoiceVM> invoiceVMs = convertDtoToVM(invoiceData);
//             ObservableList<InvoiceVM> invoiceList = FXCollections.observableList(invoiceVMs);
//             tb_invoice.setItems(invoiceList);
//             TableUtil.autoResizeColumns(tb_invoice);
//         } catch (InternalDataAccessException e) {
//             Alert alert = new Alert(AlertType.ERROR);
//             alert.setContentText(e.getMessage());
//             Stage alertStage = (Stage) alert.getDialogPane().getScene().getWindow();
//             alertStage.setAlwaysOnTop(true);
//             alert.setOnCloseRequest(event -> {
//                 ButtonType result = alert.getResult();
//                 if (result == ButtonType.OK) {
//                     Platform.exit();
//                 }
//             });
//             alert.showAndWait();
//         }
//     }

//     private void fetchSearchResult(String value) {
//         searchInvoiceInputB.execute(value);
//         List<ViewInvoiceOutputDTO> invoiceData = searchInvoicePresenterB.getSearchResult();
//         List<InvoiceVM> invoiceVMs = convertDtoToVM(invoiceData);
//         ObservableList<InvoiceVM> invoiceList = FXCollections.observableList(invoiceVMs);
//         tb_invoice.setItems(invoiceList);
//         TableUtil.autoResizeColumns(tb_invoice);
//     }

//     private List<InvoiceVM> convertDtoToVM(List<ViewInvoiceOutputDTO> dtoList) {
//         return dtoList.stream()
//                 .map(viewILDto -> convertToInvoiceVM(viewILDto))
//                 .toList();
//     }

//     private InvoiceVM convertToInvoiceVM(ViewInvoiceOutputDTO dto) {
//         return InvoiceVM.builder()
//                 .id(dto.getId())
//                 .customerName(dto.getCustomerName())
//                 .price(String.format("%.2f", dto.getPrice()))
//                 .invoiceType(localizeInvoiceType(dto.getInvoiceType()))
//                 .billedDate(dto.getBilledDate().format(dateTimeFormatter))
//                 .roomId(dto.getRoomId())
//                 .total(String.format("%.2f", dto.getTotal()))
//                 .build();
//     }

//     private String localizeInvoiceType(String type) {
//         switch (type.toLowerCase()) {
//             case "daily":
//                 return "Theo ngày";
//             case "hourly":
//                 return "Theo giờ";
//         }
//         return null;
//     }

//     @FXML
//     void onSearchInput(ActionEvent event) {
//         String searchInputValue = searchBar.getText().trim();
//         if(searchInputValue.isEmpty()) {
//             fetchInvoiceTable();
//         } else {
//             fetchSearchResult(searchInputValue);
//         }
//     }
// }
