/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.mycompany.app_maulana;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * FXML Controller class
 *
 * @author maula
 */
public class dashboardController implements Initializable {

    @FXML
    private Label static_totalCust;
    @FXML
    private Label static_custHarian;
    @FXML
    private AnchorPane dashboard_main;
    @FXML
    private Button custBtn;
    @FXML
    private AnchorPane custMenu;
    @FXML
    private AnchorPane cust_menu;
    @FXML
    private TableColumn<CustModel, java.util.Date> cust_custDate;
    @FXML
    private TableColumn<CustModel, Number> cust_custId;
    @FXML
    private TableColumn<CustModel, Number> cust_custTotal;
    @FXML
    private TableView<CustModel> cust_tableView;
    @FXML
    private Button close;
    @FXML
    private Button minimize;
    @FXML
    private Label username;
    @FXML
    private Button dashboardBtn;
    @FXML
    private Button foodBtn;
    @FXML
    private Button orderBtn;
    @FXML
    private Button signout;
    @FXML
    private AnchorPane dashboard_menu;
    @FXML
    private Label dashboard_TM;
    @FXML
    private Label dashboard_TI;
    @FXML
    private Label dashboard_C;
    @FXML
    private BarChart<?, ?> dashboard_NOC;
    @FXML
    private BarChart<?, ?> dashboard_IC;
    @FXML
    private AnchorPane food_menu;
    @FXML
    private TextField food_productID;
    @FXML
    private TextField food_productName;
    @FXML
    private ComboBox<?> food_type;
    @FXML
    private TextField food_price;
    @FXML
    private ComboBox<?> food_status;
    @FXML
    private Button food_add;
    @FXML
    private Button food_delete;
    @FXML
    private Button food_clear;
    @FXML
    private Button food_update;
    @FXML
    private TextField food_search;
    @FXML
    private TableView<categories> food_tableView;
    @FXML
    private TableColumn<categories, String> food_col_productID;
    @FXML
    private TableColumn<categories, String> food_col_productName;
    @FXML
    private TableColumn<categories, String> food_col_type;
    @FXML
    private TableColumn<categories, String> food_col_price;
    @FXML
    private TableColumn<categories, String> food_col_status;
    @FXML
    private Button homeBtn;
    @FXML
    private AnchorPane home_menu;
    @FXML
    private AnchorPane order_menu;
    @FXML
    private TableView<list_product> order_tableView;
    @FXML
    private TableColumn<list_product, String> order_col_productId;
    @FXML
    private TableColumn<list_product, String> order_col_productName;
    @FXML
    private TableColumn<list_product, String> order_col_type;
    @FXML
    private TableColumn<list_product, String> order_col_price;
    @FXML
    private TableColumn<list_product, String> order_col_quantity;
    @FXML
    private ComboBox<?> order_productID;
    @FXML
    private ComboBox<?> order_productName;
    @FXML
    private Spinner<Integer> order_quantity;
    @FXML
    private Label order_total;
    @FXML
    private TextField order_amount;
    @FXML
    private Label order_balance;
    @FXML
    private Button order_pay;
    @FXML
    private Button order_add;
    @FXML
    private Button order_receipt;
    @FXML
    private Button order_remove;
    @FXML
    private Button staticBtn;
    @FXML
    private AnchorPane static_menu;
    @FXML
    private Label static_total;

    //Start Display Show Username
    public void displayUsername() {
        String user = getDataUsers.username;
        user = user.substring(0, 1).toUpperCase() + user.substring(1);
        username.setText(user);
    }

    // End Display Show Username
    // START
    // FOOD
    // PAGE
    //Start selected TYPE AND STATUS
    private String[] status = {"Tersedia", "Tidak Tersedia"};

    public void foodStatus() {
        List<String> listStatus = new ArrayList<>();

        for (String data : status) {
            listStatus.add(data);
        }

        ObservableList listData = FXCollections.observableArrayList(listStatus);
        food_status.setItems(listData);
    }

    private String[] type = {"Cat Food", "Dog Food", "Lainnyya"};

    public void foodType() {
        List<String> listType = new ArrayList<>();

        for (String data : type) {
            listType.add(data);
        }

        ObservableList listData = FXCollections.observableArrayList(listType);
        food_type.setItems(listData);
    }

    //End selected TYPE AND STATUS
    private Connection connect;
    private PreparedStatement prepare;
    private Statement statement;
    private ResultSet result;

    //Start create ADD, UPDATE , DELETE , SELECT TABLE, SEARCH, AND CLEAR BUTTON
    public void foodAdd() {
        String sql = "INSERT INTO categories (product_id , product_name, price, type, status)"
                + "VALUES (?,?,?,?,?)";

        connect = database.connectDb();

        try {
            prepare = connect.prepareStatement(sql);
            prepare.setString(1, food_productID.getText());
            prepare.setString(2, food_productName.getText());
            prepare.setString(3, food_price.getText());

            prepare.setString(4, (String) food_type.getSelectionModel().getSelectedItem());
            prepare.setString(5, (String) food_status.getSelectionModel().getSelectedItem());

            Alert alert;

            if (food_productID.getText().isEmpty()
                    || food_productName.getText().isEmpty()
                    || food_price.getText().isEmpty()
                    || food_type.getSelectionModel() == null
                    || food_status.getSelectionModel() == null) {

                alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Pesan Error");
                alert.setHeaderText("Silahkan isi yang masih kosong");
                alert.showAndWait();

            } else {
                String dataCheck = "SELECT product_id FROM categories WHERE product_id = '"
                        + food_productID.getText() + "'";

                connect = database.connectDb();

                statement = connect.createStatement();

                result = statement.executeQuery(dataCheck);

                if (result.next()) {
                    alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Pesan Error");
                    alert.setHeaderText("Product ID :" + food_productID.getText() + " Sudah Tersedia");
                    alert.showAndWait();
                } else {
                    prepare.executeUpdate();

                    alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Pesan Pemberitahuan");
                    alert.setHeaderText("Sukses Menambahkan Product");
                    alert.showAndWait();

                    // Tambah Data
                    foodShowData();

                    // Clear Data
                    foodClear();
                }

                prepare.executeQuery();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void foodUpdate() {

        String sql = "UPDATE categories SET product_name = '"
                + food_productName.getText() + "', type = '"
                + food_type.getSelectionModel().getSelectedItem() + "', price = '"
                + food_price.getText() + "', status = '"
                + food_status.getSelectionModel().getSelectedItem()
                + "' WHERE product_id = '" + food_productID.getText() + "'";

        connect = database.connectDb();

        try {

            Alert alert;

            if (food_productID.getText().isEmpty()
                    || food_productName.getText().isEmpty()
                    || food_type.getSelectionModel().getSelectedItem() == null
                    || food_price.getText().isEmpty()
                    || food_status.getSelectionModel().getSelectedItem() == null) {
                alert = new Alert(AlertType.ERROR);
                alert.setTitle("Pesan Error");
                alert.setHeaderText(null);
                alert.setContentText("Tolong isi yang masih kosong");
                alert.showAndWait();
            } else {

                alert = new Alert(AlertType.CONFIRMATION);
                alert.setTitle("Pesan Konfirmasi");
                alert.setHeaderText(null);
                alert.setContentText("Apakah anda yakin ingin mengganti Product Id: : "
                        + food_productID.getText() + "?");

                Optional<ButtonType> option = alert.showAndWait();

                if (option.get().equals(ButtonType.OK)) {

                    alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Pesan Pemberitahuan");
                    alert.setHeaderText("Update Sukses");
                    alert.showAndWait();

                    statement = connect.createStatement();
                    statement.executeUpdate(sql);

                    // TO SHOW THE DATA
                    foodShowData();
                    // TO CLEAR THE FIELDS
                    foodClear();

                } else {
                    alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Pesan Pemberitahuan");
                    alert.setHeaderText("Update di batalkan");
                    alert.showAndWait();
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void foodDelete() {

        String sql = "DELETE FROM categories WHERE product_id = '"
                + food_productID.getText() + "'";

        connect = database.connectDb();

        try {

            Alert alert;

            if (food_productID.getText().isEmpty()
                    || food_productName.getText().isEmpty()
                    || food_type.getSelectionModel().getSelectedItem() == null
                    || food_price.getText().isEmpty()
                    || food_status.getSelectionModel().getSelectedItem() == null) {
                alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Pesan Error");
                alert.setHeaderText("Tolong di isi yang masih kosong");
                alert.showAndWait();
            } else {

                alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Pesan Pemberitahuan");

                alert.setContentText("Apakah anda yakin ingin menghapus data Product ID: "
                        + food_productID.getText() + "?");

                Optional<ButtonType> option = alert.showAndWait();

                if (option.get().equals(ButtonType.OK)) {

                    alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Pesan Pemberitahuan");
                    alert.setHeaderText("Delete Sukses");
                    alert.showAndWait();

                    statement = connect.createStatement();
                    statement.executeUpdate(sql);

                    // TO SHOW THE DATA
                    foodShowData();
                    // TO CLEAR THE FIELDS
                    foodClear();

                } else {
                    alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Pesan Pemberitahuan");
                    alert.setHeaderText("Delete di cancel");
                    alert.showAndWait();
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void foodSelect() {
        categories catData = food_tableView.getSelectionModel().getSelectedItem();

        int num = food_tableView.getSelectionModel().getSelectedIndex();

        if ((num - 1) < -1) {
            return;
        }

        food_productID.setText(catData.getId());
        food_productName.setText(catData.getName());
        food_productID.setText(catData.getId());
        food_price.setText(String.valueOf(catData.getPrice()));
    }

    public void foodClear() {
        food_productID.setText("");
        food_productName.setText("");
        food_price.setText("");
        food_type.getSelectionModel().clearSelection();
        food_status.getSelectionModel().clearSelection();
    }

    public void foodSearch() {
        FilteredList<categories> filter = new FilteredList<>(foodList, e -> true);

        food_search.textProperty().addListener((observabl, newValue, oldValue) -> {

            filter.setPredicate(predicateCategories -> {

                if (newValue.isEmpty() || newValue == null) {
                    return true;
                }

                String searchKey = newValue.toLowerCase();

                if (predicateCategories.getId().toLowerCase().contains(searchKey)) {
                    return true;
                } else if (predicateCategories.getName().toLowerCase().contains(searchKey)) {
                    return true;
                } else if (predicateCategories.getType().toLowerCase().contains(searchKey)) {
                    return true;
                } else if (predicateCategories.getPrice().toString().contains(searchKey)) {
                    return true;
                } else if (predicateCategories.getStatus().toLowerCase().contains(searchKey)) {
                    return true;
                } else {
                    return false;
                }
            });
        });

        SortedList<categories> sortList = new SortedList<>(filter);

        sortList.comparatorProperty().bind(food_tableView.comparatorProperty());
        food_tableView.setItems(sortList);

    }

    //End create ADD, UPDATE , DELETE , SELECT TABLE, SEARCH, AND CLEAR BUTTON
    //Start OBSERVABLE LIST SHOW
    public ObservableList<categories> FoodShowListData() {

        ObservableList<categories> listData = FXCollections.observableArrayList();

        String sql = "SELECT * FROM categories";

        connect = database.connectDb();

        try {

            prepare = connect.prepareStatement(sql);

            result = prepare.executeQuery();

            categories cat;

            while (result.next()) {
                cat = new categories(result.getString("product_id"),
                        result.getString("product_name"), result.getDouble("price"),
                        result.getString("type"), result.getString("status"));

                listData.add(cat);

            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return listData;
    }

    private ObservableList<categories> foodList;

    public void foodShowData() {
        foodList = FoodShowListData();

        food_col_productID.setCellValueFactory(new PropertyValueFactory<>("id"));
        food_col_productName.setCellValueFactory(new PropertyValueFactory<>("name"));
        food_col_type.setCellValueFactory(new PropertyValueFactory<>("type"));
        food_col_price.setCellValueFactory(new PropertyValueFactory<>("price"));
        food_col_status.setCellValueFactory(new PropertyValueFactory<>("status"));

        food_tableView.setItems(foodList);
    }
    //End OBSERVABLE LIST SHOW

    // END
    // FOOD
    // PAGE
    // START
    // ORDER
    // PAGE
    // START ORDER ADD BTN 
    public void orderAdd() {

        orderCustomerId();
        orderTotal();

        String sql = "INSERT INTO list_product "
                + "(cust_id, product_id, product_name, product_type, product_price, product_qty, product_date) "
                + "VALUES(?,?,?,?,?,?,?)";

        connect = database.connectDb();

        try {
            String orderType = "";
            double orderPrice = 0;

            String checkingData = "SELECT * FROM categories WHERE product_id = '"
                    + order_productID.getSelectionModel().getSelectedItem() + "'";

            statement = connect.createStatement();
            result = statement.executeQuery(checkingData);

            if (result.next()) {
                orderType = result.getString("type");
                orderPrice = result.getDouble("price");
            }

            prepare = connect.prepareStatement(sql);
            prepare.setString(1, String.valueOf(customerId));
            prepare.setString(2, (String) order_productID.getSelectionModel().getSelectedItem());
            prepare.setString(3, (String) order_productName.getSelectionModel().getSelectedItem());
            prepare.setString(4, orderType);

            double totalPrice = orderPrice * qty;

            prepare.setString(5, String.valueOf(totalPrice));

            prepare.setString(6, String.valueOf(qty));

            Date date = new Date();
            java.sql.Date sqlDate = new java.sql.Date(date.getTime());

            prepare.setString(7, String.valueOf(sqlDate));

            prepare.executeUpdate();

            orderDisplayTotal();

            orderDisplayData();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void orderPay() {
        orderCustomerId();
        orderTotal();

        String sql = "INSERT INTO info_product (cust_id, product_total, product_date) VALUES(?,?,?)";

        connect = database.connectDb();

        try {

            Alert alert;

            if (balance == 0 || String.valueOf(balance) == "Rp 0.0" || String.valueOf(balance) == null
                    || total == 0 || String.valueOf(total) == "Rp 0.0" || String.valueOf(total) == null) {
                alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Pesan Error");
                alert.setHeaderText(null);
                alert.setContentText("Tidak valid");
                alert.showAndWait();
            } else {

                alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Pesan Konfirmasi");
                alert.setHeaderText(null);
                alert.setContentText("Apakah kamu yakin ?");
                Optional<ButtonType> option = alert.showAndWait();

                if (option.get().equals(ButtonType.OK)) {
                    prepare = connect.prepareStatement(sql);
                    prepare.setString(1, String.valueOf(customerId));
                    prepare.setString(2, String.valueOf(total));

                    Date date = new Date();
                    java.sql.Date sqlDate = new java.sql.Date(date.getTime());

                    prepare.setString(3, String.valueOf(sqlDate));

                    prepare.executeUpdate();

                    alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Pesan Informasi");
                    alert.setHeaderText(null);
                    alert.setContentText("Sukses");
                    alert.showAndWait();

                    order_total.setText("Rp 0.0");
                    order_balance.setText("Rp 0.0");
                    order_amount.setText("");

                } else {
                    alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Pesan Informasi");
                    alert.setHeaderText(null);
                    alert.setContentText("Batal");
                    alert.showAndWait();
                }

            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void orderDisplayTotal() {
        orderTotal();
        order_total.setText("Rp" + String.valueOf(total));

    }
    private ObservableList<list_product> dataOrder;

    public void orderDisplayData() {
        dataOrder = listDataOrder();

        order_col_productId.setCellValueFactory(new PropertyValueFactory<>("productId"));
        order_col_productName.setCellValueFactory(new PropertyValueFactory<>("name"));
        order_col_type.setCellValueFactory(new PropertyValueFactory<>("type"));
        order_col_price.setCellValueFactory(new PropertyValueFactory<>("price"));
        order_col_quantity.setCellValueFactory(new PropertyValueFactory<>("qty"));

        order_tableView.setItems(dataOrder);

    }

    private double total = 0;

    public void orderTotal() {
        orderCustomerId();

        String sql = "SELECT SUM(product_price) FROM list_product WHERE cust_id = " + customerId;

        connect = database.connectDb();

        try {
            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();

            if (result.next()) {
                total = result.getDouble("SUM(product_price)");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private double amount;
    private double balance;

    public void orderAmount() {
        orderTotal();

        Alert alert;

        if (order_amount.getText().isEmpty() || order_amount.getText() == null
                || order_amount.getText() == "") {
            alert = new Alert(AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("Tolong masukkan jumlah uang yang ingin dibayar");
            alert.showAndWait();
        } else {
            amount = Double.parseDouble(order_amount.getText());

            if (amount < total) {
                order_amount.setText("");
            } else {
                balance = (amount - total);
                order_balance.setText("Rp " + String.valueOf(balance));
            }
        }
    }

    // END ORDER ADD BTN
    public void orderProductId() {

        String sql = "SELECT product_id FROM categories WHERE status = 'Tersedia'";

        connect = database.connectDb();

        try {
            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();

            ObservableList data = FXCollections.observableArrayList();

            while (result.next()) {
                data.add(result.getString("product_id"));
            }

            order_productID.setItems(data);

            orderProductName();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void orderProductName() {

        String sql = "SELECT product_name FROM categories WHERE product_id = '"
                + order_productID.getSelectionModel().getSelectedItem() + "'";

        connect = database.connectDb();

        try {
            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();

            ObservableList data = FXCollections.observableArrayList();

            while (result.next()) {
                data.add(result.getString("product_name"));
            }

            order_productName.setItems(data);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private SpinnerValueFactory<Integer> spinner;

    private int qty;

    public void orderQuantity() {
        qty = order_quantity.getValue();
    }

    public void orderSpinner() {
        spinner = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 50, 0);

        order_quantity.setValueFactory(spinner);
    }

    private int customerId;

    public void orderCustomerId() {

        String sql = "SELECT cust_id FROM list_product";

        connect = database.connectDb();

        try {
            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();

            while (result.next()) {
                customerId = result.getInt("cust_id");
            }

            String checkingData = "SELECT cust_id FROM info_product";

            statement = connect.createStatement();
            result = statement.executeQuery(checkingData);

            int customerInfoId = 0;

            while (result.next()) {
                customerInfoId = result.getInt("cust_id");
            }

            if (customerId == 0) {
                customerId += 1;
            } else if (customerId == customerInfoId) {
                customerId += 1;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public ObservableList<list_product> listDataOrder() {

        orderCustomerId();

        ObservableList<list_product> data = FXCollections.observableArrayList();

        String sql = "SELECT * FROM list_product WHERE cust_id = " + customerId;

        connect = database.connectDb();

        try {
            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();

            list_product prod;

            while (result.next()) {
                prod = new list_product(result.getInt("id"),
                        result.getString("product_id"),
                        result.getString("product_name"),
                        result.getString("product_type"),
                        result.getDouble("product_price"),
                        result.getInt("product_qty"));

                data.add(prod);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return data;
    }

    // END
    // ORDER
    //PAGE
    //START LIST CUSTOMER
    private ObservableList<CustModel> dataCust;

    public void custDisplayData() {
        dataCust = listDataCust();

        cust_custId.setCellValueFactory(new PropertyValueFactory<>("custId"));
        cust_custTotal.setCellValueFactory(new PropertyValueFactory<>("productTotal"));
        cust_custDate.setCellValueFactory(new PropertyValueFactory<>("productDate"));

        cust_tableView.setItems(dataCust);

    }

    public ObservableList<CustModel> listDataCust() {
        ObservableList<CustModel> data = FXCollections.observableArrayList();

        String sql = "SELECT * FROM info_product;";
        connect = database.connectDb();
        try {
            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();
            while (result.next()) {
                int custId = result.getInt("cust_id");
                double productTotal = result.getDouble("product_total");
                java.util.Date productDate = result.getDate("product_date");

                CustModel custModel = new CustModel(custId, productTotal, productDate);
                data.add(custModel);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return data;
    }

    //END LIST CUSTOMER
    //START STATISCTICS
    private double getTotalKeuangan() {
        String sql = "SELECT SUM(product_total) AS total FROM info_product";
        connect = database.connectDb();

        try {
            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();

            if (result.next()) {
                return result.getDouble("total");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // Tutup resource, seperti koneksi dan pernyataan
            try {
                if (result != null) {
                    result.close();
                }
                if (prepare != null) {
                    prepare.close();
                }
                if (connect != null) {
                    connect.close();
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
        return 0.0;
    }

    private double getKeuanganHarian() {
        String sql = "SELECT SUM(product_total) AS total FROM info_product GROUP BY product_date";
        connect = database.connectDb();

        try {
            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();

            if (result.next()) {
                return result.getDouble("total");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // Tutup resource, seperti koneksi dan pernyataan
            try {
                if (result != null) {
                    result.close();
                }
                if (prepare != null) {
                    prepare.close();
                }
                if (connect != null) {
                    connect.close();
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
        return 0.0;
    }

    private int getTotalSemuaCustomer() {
        String sql = "SELECT COUNT(DISTINCT cust_id) AS total FROM info_product";
        connect = database.connectDb();

        try {
            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();

            if (result.next()) {
                return result.getInt("total");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // Tutup resource, seperti koneksi dan pernyataan
            try {
                if (result != null) {
                    result.close();
                }
                if (prepare != null) {
                    prepare.close();
                }
                if (connect != null) {
                    connect.close();
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
        return 0;
    }

    private int getTotalCustomerHarian() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String currentDate = dateFormat.format(new Date());

        String sql = "SELECT COUNT(DISTINCT cust_id) AS total FROM info_product WHERE product_date = ?";
        connect = database.connectDb();

        try {
            prepare = connect.prepareStatement(sql);
            prepare.setString(1, currentDate);
            result = prepare.executeQuery();

            if (result.next()) {
                return result.getInt("total");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // Tutup resource, seperti koneksi dan pernyataan
            try {
                if (result != null) {
                    result.close();
                }
                if (prepare != null) {
                    prepare.close();
                }
                if (connect != null) {
                    connect.close();
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
        return 0;
    }

    //END STATISTICS
    //Start SWITCH MAIN MENU
    public void switchMenu(ActionEvent e) {

        if (e.getSource() == foodBtn) {
            food_menu.setVisible(true);
            order_menu.setVisible(false);
            cust_menu.setVisible(false);
            home_menu.setVisible(false);
            static_menu.setVisible(false);

            orderBtn.setStyle("-fx-background-color : transparent; ");
            foodBtn.setStyle("-fx-background-color : linear-gradient(to bottom right, #196ec9, #0557ae); ");
            custBtn.setStyle("-fx-background-color : transparent; ");
            homeBtn.setStyle("-fx-background-color : transparent; ");
            staticBtn.setStyle("-fx-background-color : transparent; ");

            foodShowData();
            foodSearch();
        } else if (e.getSource() == orderBtn) {
            order_menu.setVisible(true);
            food_menu.setVisible(false);
            cust_menu.setVisible(false);
            home_menu.setVisible(false);
            static_menu.setVisible(false);

            foodBtn.setStyle("-fx-background-color : transparent; ");
            orderBtn.setStyle("-fx-background-color : linear-gradient(to bottom right, #196ec9, #0557ae); ");
            custBtn.setStyle("-fx-background-color : transparent; ");
            homeBtn.setStyle("-fx-background-color : transparent; ");
            staticBtn.setStyle("-fx-background-color : transparent; ");

            orderProductId();
            orderProductName();
            orderSpinner();
            orderDisplayData();
            orderDisplayTotal();
        } else if (e.getSource() == custBtn) {
            cust_menu.setVisible(true);
            food_menu.setVisible(false);
            order_menu.setVisible(false);
            home_menu.setVisible(false);
            static_menu.setVisible(false);

            foodBtn.setStyle("-fx-background-color : transparent; ");
            custBtn.setStyle("-fx-background-color : linear-gradient(to bottom right, #196ec9, #0557ae); ");
            orderBtn.setStyle("-fx-background-color : transparent; ");
            homeBtn.setStyle("-fx-background-color : transparent; ");
            staticBtn.setStyle("-fx-background-color : transparent; ");

            custDisplayData();
        } else if (e.getSource() == homeBtn) {
            home_menu.setVisible(true);
            cust_menu.setVisible(false);
            food_menu.setVisible(false);
            order_menu.setVisible(false);
            static_menu.setVisible(false);

            foodBtn.setStyle("-fx-background-color : transparent; ");
            homeBtn.setStyle("-fx-background-color : linear-gradient(to bottom right, #196ec9, #0557ae); ");
            orderBtn.setStyle("-fx-background-color : transparent; ");
            custBtn.setStyle("-fx-background-color : transparent; ");
            staticBtn.setStyle("-fx-background-color : transparent; ");

            custDisplayData();
        } else if (e.getSource() == staticBtn) {
            static_menu.setVisible(true);
            cust_menu.setVisible(false);
            home_menu.setVisible(false);
            food_menu.setVisible(false);
            order_menu.setVisible(false);

            foodBtn.setStyle("-fx-background-color : transparent; ");
            staticBtn.setStyle("-fx-background-color : linear-gradient(to bottom right, #196ec9, #0557ae); ");
            orderBtn.setStyle("-fx-background-color : transparent; ");
            custBtn.setStyle("-fx-background-color : transparent; ");

            double totalKeuangan = getTotalKeuangan();
            static_total.setText(String.format("%.2f", totalKeuangan));
            
            int totalSemuaCustomer = getTotalSemuaCustomer();
            static_totalCust.setText(String.format("%d", totalSemuaCustomer));
            int totalHarian = getTotalCustomerHarian();
            static_custHarian.setText(String.format("%d", totalHarian));
        }
    }
    //End SWITCH MAIN MENU

    //Start Initialize
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        displayUsername();
        foodStatus();
        foodType();
        foodShowData();

        orderProductId();
        orderProductName();
        orderSpinner();
        orderDisplayData();
        orderDisplayTotal();

        custDisplayData();

        double totalKeuangan = getTotalKeuangan();
        static_total.setText(String.format("%.2f", totalKeuangan));
        
        int totalSemuaCustomer = getTotalSemuaCustomer();
        static_totalCust.setText(String.format("%d", totalSemuaCustomer));
        int totalHarian = getTotalCustomerHarian();
        static_custHarian.setText(String.format("%d", totalHarian));

    }
    //End Initialize

    //start close , minimize , sign out
    private double x = 0;
    private double y = 0;

    @FXML
    private void close(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Pesan Pemberitahuan");
        alert.setHeaderText(null);
        alert.setContentText("Apakah anda yakin ingin keluar aplikasi ?");
        Optional<ButtonType> option = alert.showAndWait();

        try {
            if (option.get().equals(ButtonType.OK)) {

                System.exit(0);

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void minimize(ActionEvent event) {
        Stage stage = (Stage) dashboard_main.getScene().getWindow();
        stage.setIconified(true);

    }

    @FXML
    private void signOut(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Pesan Pemberitahuan");
        alert.setHeaderText(null);
        alert.setContentText("Apakah kamu yakin ingin Logout ?");
        Optional<ButtonType> option = alert.showAndWait();

        try {
            if (option.get().equals(ButtonType.OK)) {

                signout.getScene().getWindow().hide();

                Parent root = FXMLLoader.load(getClass().getResource("login.fxml"));

                Stage stage = new Stage();
                Scene scene = new Scene(root);

                stage.initStyle(StageStyle.TRANSPARENT);

                stage.setScene(scene);
                stage.show();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    //end close, minimize , sign out
}
