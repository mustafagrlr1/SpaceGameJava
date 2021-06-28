import java.awt.*;

/**
 * Barricade of the game just like other creature
 */
public class Barricade extends Creature{
    private GamePanel game;

    public Barricade(GamePanel game, float x, float y) {
        super(x, y, Creature.DEFAULT_CREATURE_WIDTH, Creature.DEFAULT_CREATURE_HEIGHT);
        this.game = game;
        health = 100;
    }

    /**
     * tick does not do anything, it does not move
     */
    @Override
    public void tick() {

    }


    /**
     * if barricade's health is not 0 show the barricade
     * @param g
     */
    @Override
    public void render(Graphics g) {
        if (!isDead()) {
            g.drawImage(Assets.barricade, (int) x, (int) y, width, height, null);
        }
    }
}
