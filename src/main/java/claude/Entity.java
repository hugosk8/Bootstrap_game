package claude;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public abstract class Entity {
    
    protected Image spriteSheet;
    protected ImageView sprite;
    protected double x;
    protected double y;

    public Entity(String filePath, double x, double y) {
        this.spriteSheet = new Image(getClass().getResource(filePath).toExternalForm());
        this.sprite = new ImageView(spriteSheet);
        this.x = x;
        this.y = y;

        this.sprite.setTranslateX(x);
        this.sprite.setTranslateY(y);
    }

    public double getX() {
        return this.x;
    }

    public double getY() {
        return this.y;
    }

    public abstract void update();
}
