package Controller;

import Model.DBConnection.DBHandler;
import com.jfoenix.controls.JFXButton;
import javafx.application.Application;
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
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import Model.Client;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

public class Home extends Application implements Initializable {

    @FXML
    private JFXButton homeB;

    @FXML
    private JFXButton profileB;

    @FXML
    private Pane userPanel;

    @FXML
    private Pane homePanel;

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

    private ObservableList<Client> list = FXCollections.observableArrayList();

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("../View/fxml/home.fxml"));
        primaryStage.setTitle("Dashboard");
        primaryStage.setScene(new Scene(root, 790, 460));
        primaryStage.setResizable(false);
        primaryStage.show();

        primaryStage.getScene().getWindow().setOnCloseRequest(event -> {
            event.consume();

            Stage sureEx = new Stage();
            sureEx.initModality(Modality.APPLICATION_MODAL);
            Parent parent;

            try {
                parent = FXMLLoader.load(getClass().getResource("../View/fxml/confirmDialog.fxml"));
                sureEx.setTitle("Are you sure?");
                sureEx.setScene(new Scene(parent, 315, 85));
                sureEx.setResizable(false);
                sureEx.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        aziendaCol.setCellValueFactory(new PropertyValueFactory<>("Azienda"));
        nameCol.setCellValueFactory(new PropertyValueFactory<>("Nome"));
        surnameCol.setCellValueFactory(new PropertyValueFactory<>("Cognome"));
        usernameCol.setCellValueFactory(new PropertyValueFactory<>("Username"));
        passwordCol.setCellValueFactory(new PropertyValueFactory<>("Password"));
        emailCol.setCellValueFactory(new PropertyValueFactory<>("Email"));


        handler = new DBHandler();
        try {
            connection = handler.getConnetion();
            loadDBData_ClienteData();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void loadDBData_ClienteData(){
        try {
            PreparedStatement prep = connection.prepareStatement("SELECT * FROM Cliente");
            ResultSet rs = prep.executeQuery();

            while(rs.next()){
                //Costruzione della tabella
                list.add(new Client(
                        rs.getString("Azienda"),
                        rs.getString("Nome"),
                        rs.getString("Cognome"),
                        rs.getString("Username"),
                        rs.getString("Password"),
                        rs.getString("Email")
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
        Parent root = FXMLLoader.load(getClass().getResource("../View/fxml/confirmDialog.fxml"));
        sureEx.setTitle("Are you sure?");
        sureEx.setScene(new Scene(root, 315, 85));
        sureEx.setResizable(false);
        sureEx.show();
    }

    @FXML
    private void handlePanel(ActionEvent event){
        if(event.getSource() == homeB)
            homePanel.toFront();
        else if(event.getSource() == profileB)
            userPanel.toFront();
    }
}
