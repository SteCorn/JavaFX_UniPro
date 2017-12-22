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
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

import DBConnection.DBHandler;

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
                ResultSet res =  resState.executeQuery("SELECT name FROM sqlite_master WHERE type='table' AND name = 'user'");

                if(!res.next()){
                    //Costruzione della tabella
                    Statement state = connection.createStatement();
                    state.execute("CREATE TABLE user(company varchar(100), name varchar(60), surname varchar(60), username varchar(60), password varchar(60), email varchar(60), primary key(username));");
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
    public void signup(ActionEvent e0) throws SQLException {
        if(termsCB.isSelected()) {
            //Inserimento dati
            PreparedStatement prep = connection.prepareStatement("INSERT INTO user values(?, ?, ?, ?, ?, ?);");
            prep.setString(1, companyTF.getText());
            prep.setString(2, nameTF.getText());
            prep.setString(3, surnameTF.getText());
            prep.setString(4, usernameTF.getText());
            prep.setString(5, passwordTF.getText());
            prep.setString(6, emailTF.getText());
            prep.execute();
        }
    }

    @FXML
    public void returnToLogin(ActionEvent e1) throws IOException {
        signupB.getScene().getWindow().hide();

        Stage login = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("../resource/fxml/login.fxml"));
        login.setTitle("Log In");
        login.setScene(new Scene(root, 355, 400));
        login.setResizable(false);
        login.show();
    }

    @FXML
    public void checkBottomCheck(ActionEvent e2) throws IOException {
        signupB.setDisable(false);
    }

}