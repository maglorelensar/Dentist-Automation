/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package disciotomasyonu;

import Daolar.HastaDao;
import disciotomasyonu.Tablolar.Hasta;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Callback;

/**
 * FXML Controller class
 *
 * @author g3250
 */
public class HastaKayitController implements Initializable {

    @FXML
    private TextField ad;
    @FXML
    private TextField soyad;
    @FXML
    private TextField TC;
    @FXML
    private TextField telefon;
    @FXML
    private DatePicker dogumTarihi;

    @FXML
    private TableView<Hasta> tableView;

    HastaDao hastaDao;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        hastaDao = new HastaDao();

        EditButtonAdd();
        DeleteButtonAdd();

        hastaListesi();

    }

    @FXML
    private void hastaKaydet(ActionEvent event) throws IOException {

        Hasta hasta = new Hasta(ad.getText(), soyad.getText(), TC.getText(), telefon.getText(), dogumTarihi.getValue().toString());

        hastaDao.Kaydet(hasta);
        hastaListesi();

        ad.setText(null);
        soyad.setText(null);
        TC.setText(null);
        telefon.setText(null);
        dogumTarihi.setValue(null);
    }

    private void hastaListesi() {
        ArrayList<Hasta> hastaListesi = hastaDao.ListeGetir();

        ObservableList<Hasta> data = tableView.getItems();
        data.clear();

        for (Hasta hasta : hastaListesi) {
            data.add(hasta);
        }

    }

    void HastaSil(Hasta hasta) {
        hastaDao.Sil(hasta);
        hastaListesi();
    }

    private void EditButtonAdd() {
        TableColumn actionCol = new TableColumn("Düzenle");
        actionCol.setCellValueFactory(new PropertyValueFactory<>("DUMMY"));

        Callback<TableColumn<Hasta, String>, TableCell<Hasta, String>> cellFactory
                = //
                new Callback<TableColumn<Hasta, String>, TableCell<Hasta, String>>() {
                    @Override
                    public TableCell call(final TableColumn<Hasta, String> param) {
                        final TableCell<Hasta, String> cell = new TableCell<Hasta, String>() {

                            final Button btnedit = new Button("Düzenle");

                            @Override
                            public void updateItem(String item, boolean empty) {
                                super.updateItem(item, empty);
                                if (empty) {
                                    setGraphic(null);
                                    setText(null);
                                } else {

                                    btnedit.setOnAction(event -> {
                                        Hasta hasta = getTableView().getItems().get(getIndex());
                                        HastaSil(hasta);

                                        ad.setText(hasta.getAdi());
                                        soyad.setText(hasta.getSoyadi());
                                        TC.setText(hasta.getTC());
                                        telefon.setText(hasta.getTelefon());
                                        String date = "";
                                        LocalDate localDate = null;
                                        DateTimeFormatter formatter = null;

                                        formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                                        localDate = LocalDate.parse(hasta.getDogumTarihi(), formatter);

                                        dogumTarihi.setValue(localDate);
                                    });
                                    setGraphic(btnedit);
                                    setText(null);
                                }
                            }
                        };
                        return cell;
                    }
                };

        actionCol.setCellFactory(cellFactory);
        tableView.getColumns().add(actionCol);
    }

    private void DeleteButtonAdd() {
        TableColumn actionCol = new TableColumn("Sil");
        actionCol.setCellValueFactory(new PropertyValueFactory<>("DUMMY"));

        Callback<TableColumn<Hasta, String>, TableCell<Hasta, String>> cellFactory
                = //
                new Callback<TableColumn<Hasta, String>, TableCell<Hasta, String>>() {
                    @Override
                    public TableCell call(final TableColumn<Hasta, String> param) {
                        final TableCell<Hasta, String> cell = new TableCell<Hasta, String>() {

                            final Button btn = new Button("Sil");

                            @Override
                            public void updateItem(String item, boolean empty) {
                                super.updateItem(item, empty);
                                if (empty) {
                                    setGraphic(null);
                                    setText(null);
                                } else {
                                    btn.setOnAction(event -> {
                                        Hasta hasta = getTableView().getItems().get(getIndex());
                                        Alert alert = new Alert(AlertType.INFORMATION, "Silmek İstediginize Emin misiniz? Hasta TC : " + hasta.getTC() + " ?", 
                                                ButtonType.YES, ButtonType.NO, ButtonType.CANCEL);
                                        alert.showAndWait();

                                        if (alert.getResult() == ButtonType.YES) {
                                            HastaSil(hasta);
                                        }

                                    });
                                    setGraphic(btn);

                                    setText(null);
                                }
                            }
                        };
                        return cell;
                    }
                };

        actionCol.setCellFactory(cellFactory);
        tableView.getColumns().add(actionCol);
    }

}
