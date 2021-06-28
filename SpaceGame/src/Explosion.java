/**
 * Explosion class of game
 */

import java.awt.*;

public class Explosion extends Creature{
    public Explosion(float x, float y, int width, int height) {
        super(x, y, width, height);

    }

    @Override
    public void tick() {
        width += 2;
        height += 2;
    }

    /**
     * shows the image of explosion
     * @param g
     */
    @Override
    public void render(Graphics g) {

        g.drawImage(Assets.explosionSpriteSheet, (int)x, (int)y, 48, 48, null);
    }
}
