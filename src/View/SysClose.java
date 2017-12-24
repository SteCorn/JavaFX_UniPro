package View;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class SysClose {
    public void close(Stage stage) {
        stage.setOnCloseRequest(event -> {
            event.consume();

            Stage sureEx = new Stage();
            sureEx.initModality(Modality.APPLICATION_MODAL);
            Parent parent;
            try {
                parent = FXMLLoader.load(getClass().getResource("View/fxml/confirmDialog.fxml"));
                sureEx.setTitle("Are you sure?");
                sureEx.setScene(new Scene(parent, 315, 85));
                sureEx.setResizable(false);
                sureEx.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }
}