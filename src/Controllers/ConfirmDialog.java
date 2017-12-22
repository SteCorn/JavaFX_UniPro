package Controllers;

import com.jfoenix.controls.JFXButton;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import java.io.IOException;

public class ConfirmDialog {

    @FXML
    private JFXButton yesB;

    @FXML
    private JFXButton noB;

    @FXML
    public void yes (ActionEvent e) throws IOException {
        Platform.exit();
        System.exit(0);
    }

    @FXML
    public void no (ActionEvent e) throws IOException {
        noB.getScene().getWindow().hide();
    }
}
