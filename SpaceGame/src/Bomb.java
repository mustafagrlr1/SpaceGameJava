/**
 * Bomb of the enemy
 */

import java.awt.*;

public class Bomb extends Creature{

    /**
     * size of bomb
     */
    private final int y_space = 36;
    private final int x_space = 10;

    Player player;


    public Bomb(float x, float y, Player player) {
        super(x, y, 36, 10);
        this.player = player;

    }

    @Override
    public void tick() {
        y+=5;
    }

    /**
     * size does not change
     * @param g
     */
    @Override
    public void render(Graphics g) {
        g.drawImage(Assets.bomb, (int)x, (int)y, 36, 10, null);
    }
}
