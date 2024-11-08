package claude;

import javafx.scene.input.KeyEvent;

public class InputManager {
    
    
    private Character character;
    private boolean movingLeft;
    private boolean movingRight;

    public InputManager(Character character) {
        this.character = character;
        this.movingLeft = false;
        this.movingRight = false;
    }

    public void keyPressed(KeyEvent event) {
        switch (event.getCode()) {
            case Q:
                startMovingRight();
                character.sprite.setScaleX(1);
                break;
            case D:
                startMovingLeft();
                character.sprite.setScaleX(-1);
                break;
        }
    }
    
    public void keyReleased(KeyEvent event) {
        switch (event.getCode()) {
            case Q:
                stopMovingRight();
                break;
            case D:
                stopMovingLeft();
                break;
        }
    }

    private void startMovingLeft() {
        movingLeft = true;
        character.sprite.setScaleX(-1);
    }

    private void startMovingRight() {
        movingRight = true;
        character.sprite.setScaleX(1);
    }

    private void stopMovingLeft() {
        movingLeft = false;
    }

    private void stopMovingRight() {
        movingRight = false;
    }

    public boolean isMovingLeft() {
        return movingLeft;
    }

    public boolean isMovingRight() {
        return movingRight;
    }
}
