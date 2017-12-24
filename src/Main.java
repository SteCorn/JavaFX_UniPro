import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("View/fxml/login.fxml"));
        primaryStage.setTitle("Log In");
        primaryStage.setScene(new Scene(root, 355, 400));
        primaryStage.setResizable(false);
        primaryStage.show();
    }
}
