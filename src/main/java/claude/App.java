package claude;

import javafx.animation.AnimationTimer;
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
    private InputManager inputManager;
    private int screenWidth = 1280;
    private int screenHeight = 720;
    private Pane root;
    private Character character;
    private ImageView backgroundImageView;
    private String backgroundPath = "/claude/images/screen_background.jpg";
    // private int floorLevel = 475;
    private int backgroundScrollSpeed = 10;

    @Override
    public void start(Stage stage) throws IOException {
        this.root = new Pane();
        scene = new Scene(this.root, screenWidth, screenHeight);

        // Ajouter une image en fond
        Image backgroundImage = new Image(getClass().getResource(backgroundPath).toExternalForm());
        backgroundImageView = new ImageView(backgroundImage);
        backgroundImageView.setFitHeight(screenHeight);
        backgroundImageView.setPreserveRatio(true);
        root.getChildren().add(backgroundImageView);

        // Init le personnage
        this.character = new Character();
        double centerX = (scene.getWidth() / 2) - (character.sprite.getBoundsInLocal().getWidth() / 2);
        double centerY = (scene.getHeight() / 1.4) - (character.sprite.getBoundsInLocal().getHeight() / 2);
        character.sprite.setTranslateX(centerX);
        character.sprite.setTranslateY(centerY);
        root.getChildren().add(character.sprite);

        // Gestionnaire d'evenement pour deplacer le fond
        this.inputManager = new InputManager(this.character);

        scene.setOnKeyPressed(event -> inputManager.keyPressed(event));
        scene.setOnKeyReleased(event -> inputManager.keyReleased(event));

        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                updateAnimation();
            }
        };
        timer.start();

        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();

        scene.getRoot().requestFocus();
    }

    private void updateAnimation() {
        if (inputManager.isMovingLeft()) {
            backgroundImageView.setTranslateX(backgroundImageView.getTranslateX() - backgroundScrollSpeed);
        } else if (inputManager.isMovingRight()) {
            backgroundImageView.setTranslateX(backgroundImageView.getTranslateX() + backgroundScrollSpeed);
        }
        character.update();
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