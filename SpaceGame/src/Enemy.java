/**
 * enemy of game
 */

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class Enemy extends Creature{
    private GamePanel game;
    private int go;
    public final int DEFAULT_POSITIONX;
    public final int DEFAULT_POSITIONY;
    private ArrayList<Bomb> bomb;
    long lastTime, now;
    Player player;
    private BufferedImage enemyImage;

    public Enemy(GamePanel game, float x, float y, Player player) {
        super(x, y, Creature.DEFAULT_CREATURE_WIDTH, Creature.DEFAULT_CREATURE_HEIGHT);
        this.game = game;
        DEFAULT_POSITIONX = (int) x;
        DEFAULT_POSITIONY = (int) y;
        go = 500;
        lastTime = System.currentTimeMillis();
        health = 100;
        this.player = player;
        bomb = new ArrayList<>();
        enemyImage = Assets.enemy;
    }


    @Override
    public void tick() { // if it is not dead goes right and left
        if(!isDead()){
            now = System.currentTimeMillis();
            x += getSpeed();
            if((int)x-DEFAULT_POSITIONX > 500){
                setSpeed(-getSpeed());
            }
            if((DEFAULT_POSITIONX-(int)x) > 0 ){
                setSpeed(-getSpeed());
            }
        }

    }

    /**
     * enemy image can be change depend on levels
     * @param enemyImage
     */
    public void setEnemyImage(BufferedImage enemyImage) {
        this.enemyImage = enemyImage;
    }

    /**
     * shows the enemy on the screen
     * @param g
     */
    @Override
    public void render(Graphics g) {
        if(!isDead()) {
            g.drawImage(enemyImage, (int) x, (int) y, width, height, null);
        }
    }
}
