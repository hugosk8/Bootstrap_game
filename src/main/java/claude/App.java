package claude;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import java.io.IOException;
import javafx.scene.layout.Pane;

/**
 * JavaFX App
 */
public class App extends Application {

    private static Scene scene;
    private Pane root;
    private Character character;
    private String backgroundPath = "/claude/images/screen_background.jpg";

    @Override
    public void start(Stage stage) throws IOException {
        this.root = new Pane();
        scene = new Scene(this.root, 1280, 720);

        // Ajouter une image en fond
        Image backgroundImage = new Image(getClass().getResource(backgroundPath).toExternalForm());    
        ImageView backgroundImageView = new ImageView(backgroundImage);  
        backgroundImageView.setFitWidth(scene.getWidth());  
        backgroundImageView.setFitHeight(scene.getHeight());
        backgroundImageView.setPreserveRatio(false);

        root.getChildren().add(backgroundImageView);

        // Init le personnage
        this.character = new Character();
        double centerX = (scene.getWidth() / 2) - (character.sprite.getBoundsInLocal().getWidth() / 2);
        double centerY = (scene.getHeight() / 2) - (character.sprite.getBoundsInLocal().getHeight() / 2);
        character.sprite.setTranslateX(centerX);
        character.sprite.setTranslateY(centerY);

        root.getChildren().add(character.sprite);

        // Faire en sorte qu'il reste au centre de l'image
        scene.widthProperty().addListener((_, _, newVal) -> {
            character.sprite.setTranslateX((newVal.doubleValue() - character.sprite.getBoundsInLocal().getWidth()) / 2);
        });
        scene.heightProperty().addListener((_, _, newVal) -> {
            character.sprite.setTranslateY((newVal.doubleValue() - character.sprite.getBoundsInLocal().getHeight()) / 2);
        });

        stage.setScene(scene);
        stage.show();
    }

    static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }

    public static void main(String[] args) {
        launch();
    }
}