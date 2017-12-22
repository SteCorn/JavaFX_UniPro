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
            Parent parent = null;
            try {
                parent = FXMLLoader.load(getClass().getResource("resource/fxml/confirmDialog.fxml"));
            } catch (IOException e) {
                e.printStackTrace();
            }
            sureEx.setTitle("Are you sure?");
            sureEx.setScene(new Scene(parent, 315, 85));
            sureEx.setResizable(false);
            sureEx.show();
        });
    }
}