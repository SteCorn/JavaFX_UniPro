package Controller;

import com.jfoenix.controls.JFXButton;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

public class ConfirmDialog {

    @FXML
    private JFXButton yesB;

    @FXML
    private JFXButton noB;

    @FXML
    public void yes (ActionEvent e) {
        Platform.exit();
        System.exit(0);
    }

    @FXML
    public void no (ActionEvent e) {
        noB.getScene().getWindow().hide();
    }
}
