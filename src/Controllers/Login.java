package Controllers;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import java.sql.*;
import DBConnection.DBHandler;

public class Login implements Initializable {

    @FXML
    private JFXTextField usernameTF;
    @FXML
    private JFXCheckBox rememberCB;
    @FXML
    private JFXButton loginB;
    @FXML
    private JFXButton forgotB;
    @FXML
    private JFXButton signupB;
    @FXML
    private JFXPasswordField passwordPF;

    private Connection connection;
    private DBHandler handler;
    private static boolean hasData = false;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        System.out.println("Login is now loaded!");
    }

    @FXML
    public void login(ActionEvent e0) throws SQLException {
        handler = new DBHandler();
        try {
            connection = handler.getConnetion();
            PreparedStatement prep = connection.prepareStatement("SELECT username, password FROM user WHERE username = ? AND password = ?");

            prep.setString(1, usernameTF.getText());
            prep.setString(2, passwordPF.getText());

            ResultSet rs = prep.executeQuery();

            if(rs.next()) {
                hasData = true;
                System.out.println("Login in corso");

                dashboard();
            }
            else {
                System.out.println("Username o password errati");
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText(null);
                alert.setContentText("Username and password are not correct!");
                alert.show();
            }

        } catch (ClassNotFoundException e) {
            System.out.println("ClassNotFound");
            e.printStackTrace();
        } catch (SQLException e) {
            System.out.println("SQL");
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void signup (ActionEvent e) throws IOException {
        loginB.getScene().getWindow().hide();

        Stage signUp = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("../resource/fxml/signup.fxml"));
        signUp.setTitle("Sign up");
        signUp.setScene(new Scene(root, 328, 490));
        signUp.setResizable(false);
        signUp.show();



        signUp.setOnCloseRequest(event -> {
            event.consume();

        });
    }

    public void dashboard () throws IOException {
        loginB.getScene().getWindow().hide();

        Stage dashboard = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("../resource/fxml/home.fxml"));
        dashboard.setTitle("Dashboard");
        dashboard.setScene(new Scene(root, 790, 460));
        dashboard.setResizable(false);
        dashboard.show();
    }

}

