/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.app_maulana;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Optional;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 *
 * @author maula
 */
public class loginController extends Application {

    @FXML
    private Button close;

    @FXML
    private Button login;

    @FXML
    private PasswordField password;

    @FXML
    private TextField username;
    @FXML
    private AnchorPane main_form;

    @FXML
    public void close() {
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

    //DATABASE
    private Connection connect;
    private PreparedStatement prepare;
    private ResultSet result;

    private double x = 0;
    private double y = 0;

    @FXML
    public void adminLogin() {

        String sql = "SELECT * FROM administrator WHERE username = ? and password  = ?";

        connect = database.connectDb();

        try {

            prepare = connect.prepareStatement(sql);
            prepare.setString(1, username.getText());
            prepare.setString(2, password.getText());

            result = prepare.executeQuery();

            Alert alert;

            if (username.getText().isEmpty() || password.getText().isEmpty()) {
                alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Terdapat Error");
                alert.setHeaderText(null);
                alert.setContentText("Silahkan isi kolom yang kosong");
                alert.showAndWait();
            } else {

                if (result.next()) {

                    getDataUsers.username = username.getText();

                    alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Pesan Pemberitahuan");
                    alert.setHeaderText(null);
                    alert.setContentText("Login Berhasil");
                    alert.showAndWait();

                    login.getScene().getWindow().hide();
                    Parent root = FXMLLoader.load(getClass().getResource("dashboard.fxml"));

                    Stage stage = new Stage();
                    Scene scene = new Scene(root);

                    root.setOnMousePressed((MouseEvent event) -> {
                        x = event.getSceneX();
                        y = event.getSceneY();
                    });

                    root.setOnMouseDragged((MouseEvent event) -> {
                        stage.setX(event.getScreenX() - x);
                        stage.setY(event.getSceneY() - y);
                    });

                    stage.initStyle(StageStyle.TRANSPARENT);
                    stage.setScene(scene);
                    stage.show();
                } else {
                    alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Terdapat Error");
                    alert.setHeaderText(null);
                    alert.setContentText("Username / Password Salah");
                    alert.showAndWait();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
    }

}
