package claude;

public class Character extends Entity {
    
    private int speed;

    public Character() {
        super("/claude/images/ectoplasma_sprite.png", 80, 80 );
        this.speed = 5;

        this.sprite.setFitWidth(x);
        this.sprite.setFitHeight(y);
    }

    @Override
    public void update() {
        this.x += speed;
        this.sprite.setTranslateX(this.x);
    };
}
