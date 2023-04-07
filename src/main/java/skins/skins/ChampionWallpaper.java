package skins.skins;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class ChampionWallpaper extends Application {
    @Override
    public void start(Stage stage) throws IOException {

        FXMLLoader loader  = new FXMLLoader(getClass().getResource("ChampionWallpaper.fxml"));
        Parent root = loader.load();
        ChampionWallpaperController controller = loader.getController();
        controller.setGetHostController(getHostServices());

        // Show the window
        Scene scene = new Scene(root, 1500, 700);
        stage.setTitle("Skins");

        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
         launch();
    }
}