package Controllers;

import DBConnection.DBHandler;
import com.jfoenix.controls.JFXButton;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

public class Home_TW implements Initializable {

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
                        rs.getString("Azienda"),
                        rs.getString("Nome"),
                        rs.getString("Cognome"),
                        rs.getString("Username"),
                        rs.getString("Email"),
                        rs.getString("Password")
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
}
