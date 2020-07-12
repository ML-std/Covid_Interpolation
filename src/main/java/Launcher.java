import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.util.Objects;

public class Launcher extends Application {

    public Button button;

    public void start(Stage stage) {
        try {
            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getClassLoader().getResource("sample.fxml")));
            stage.setScene(new Scene(root));
            stage.setTitle("Interpolation");
            Image icon = new Image("interpolation.png");
            stage.getIcons().add(icon);
            stage.show();
        } catch (Exception e) {
           e.printStackTrace();
        }}
    public static void main(String[] args) {
        Application.launch(Launcher.class, args);
    }



}
