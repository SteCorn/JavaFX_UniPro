package Controller;

import com.jfoenix.controls.JFXButton;
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
import Model.DBConnection.DBHandler;

public class Login implements Initializable {

    @FXML
    private JFXTextField usernameTF;
    @FXML
    private JFXButton loginB;
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
    public void login(ActionEvent e0) {
        handler = new DBHandler();
        try {
            connection = handler.getConnetion();
            PreparedStatement prep = connection.prepareStatement("SELECT Username, Password FROM Cliente WHERE Username = ? AND Password = ?");

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
        }
    }

    @FXML
    public void signup (ActionEvent e) throws IOException {
        signupB.getScene().getWindow().hide();

        Stage signUp = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("../View/fxml/signup.fxml"));
        signUp.setTitle("Sign up");
        signUp.setScene(new Scene(root, 328, 490));
        signUp.setResizable(false);
        signUp.show();
    }

    private void dashboard() {
        loginB.getScene().getWindow().hide();

        Stage stage = new Stage();
        Home home = new Home();
        try {
            home.start(stage);
        } catch (Exception e1) {
            e1.printStackTrace();
        }
    }
}