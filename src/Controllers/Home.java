package Controllers;

import DBConnection.DBHandler;
import com.jfoenix.controls.JFXButton;
import com.sun.org.omg.CORBA.Initializer;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;


import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class Home implements Initializable {
    @FXML
    private JFXButton exitB;
    @FXML
    private TableView<Client> tableTW;

    @FXML
    private TableColumn<?, ?> aziendaCol;

    @FXML
    private TableColumn<?, ?> nameCol;

    @FXML
    private TableColumn<?, ?> surnameCol;

    @FXML
    private TableColumn<?, ?> usernameCol;

    @FXML
    private TableColumn<?, ?> emailCol;

    @FXML
    private TableColumn<?, ?> passwordCol;

    private Connection connection;
    private DBHandler handler;

    ObservableList<Client> list = FXCollections.observableArrayList();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        aziendaCol.setCellValueFactory(new PropertyValueFactory<>("Azienda"));
        nameCol.setCellValueFactory(new PropertyValueFactory<>("Nome"));
        surnameCol.setCellValueFactory(new PropertyValueFactory<>("Cognome"));
        usernameCol.setCellValueFactory(new PropertyValueFactory<>("Username"));
        emailCol.setCellValueFactory(new PropertyValueFactory<>("Email"));
        passwordCol.setCellValueFactory(new PropertyValueFactory<>("Password"));


        handler = new DBHandler();
        try {
            connection = handler.getConnetion();
            loadDBData();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    private void loadDBData(){
        try {
            PreparedStatement prep = connection.prepareStatement("SELECT * FROM user");
            ResultSet rs = prep.executeQuery();

            while(rs.next()){
                //Costruzione della tabella
                list.add(new Client(
                        rs.getString("company"),
                        rs.getString("name"),
                        rs.getString("surname"),
                        rs.getString("username"),
                        rs.getString("password"),
                        rs.getString("email")
                ));
                tableTW.setItems(list);
            }

            prep.close();
            rs.close();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void sureExit (ActionEvent e) throws IOException {
        Stage sureEx = new Stage();
        sureEx.initModality(Modality.APPLICATION_MODAL);
        Parent root = FXMLLoader.load(getClass().getResource("../resource/fxml/confirmDialog.fxml"));
        sureEx.setTitle("Are you sure?");
        sureEx.setScene(new Scene(root, 315, 85));
        sureEx.setResizable(false);
        sureEx.show();
    }

}
