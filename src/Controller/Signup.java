package Controller;

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
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

import Model.DBConnection.DBHandler;

public class Signup implements Initializable {

    @FXML
    private JFXTextField companyTF;

    @FXML
    private JFXTextField nameTF;

    @FXML
    private JFXTextField surnameTF;

    @FXML
    private JFXPasswordField passwordTF;

    @FXML
    private JFXTextField emailTF;

    @FXML
    private JFXCheckBox termsCB;

    @FXML
    private JFXButton signupB;

    @FXML
    private JFXButton alreadyMemberB;

    @FXML
    private JFXTextField usernameTF;

    private Connection connection;
    private DBHandler handler;
    private static boolean hasData = false;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        handler = new DBHandler();
        try {
            connection = handler.getConnetion();

            if(!hasData){
                hasData = true;

                Statement resState = connection.createStatement();
                ResultSet res =  resState.executeQuery("SELECT name FROM sqlite_master WHERE type='table' AND name = 'Cliente'");

                if(!res.next()){
                    //Costruzione della tabella
                    Statement state = connection.createStatement();
                    state.execute("create table Cliente (Username varchar(60) primary key, Azienda varchar(60), Nome varchar(60), Cognome varchar(60), Email varchar(60), Password varchar(60));");
                }
            }

        } catch (ClassNotFoundException e) {
            System.out.println("ClassNotFound");
            e.printStackTrace();
        } catch (SQLException e) {
            System.out.println("SQL");
            e.printStackTrace();
        }

        signupB.setDisable(true);
    }

    @FXML
    public void signup(ActionEvent e0) throws SQLException, IOException {
        if(termsCB.isSelected()) {
            //Inserimento dati
            PreparedStatement prep = connection.prepareStatement("INSERT INTO Cliente values(?, ?, ?, ?, ?, ?);");
            prep.setString(1, usernameTF.getText());
            prep.setString(2, companyTF.getText());
            prep.setString(3, nameTF.getText());
            prep.setString(4, surnameTF.getText());
            prep.setString(5, emailTF.getText());
            prep.setString(6, passwordTF.getText());
            prep.execute();

            loginScreen();
        }
    }

    @FXML
    public void returnToLogin(ActionEvent e1) throws IOException {
        loginScreen();
    }

    @FXML
    public void checkBottomCheck(ActionEvent e2) {
        signupB.setDisable(false);
    }

    private void loginScreen() throws IOException {
        signupB.getScene().getWindow().hide();

        Stage login = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("../View/fxml/login.fxml"));
        login.setTitle("Log In");
        login.setScene(new Scene(root, 355, 400));
        login.setResizable(false);
        login.show();
    }

}