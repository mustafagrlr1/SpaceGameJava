/**
 * player and spaceship of the game
 */

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class Player extends Creature{

    //Animatons
    private Animation animDown;

    private GamePanel game;
    private ArrayList<Bullet> bullet;
    BufferedImage playerImage;

    /**
     * constructor of player
     * @param game
     * @param x
     * @param y
     */
    public Player(GamePanel game, float x, float y) {
        super(x, y, Creature.DEFAULT_CREATURE_WIDTH, Creature.DEFAULT_CREATURE_HEIGHT);
        this.game = game;

        bullet = new ArrayList<>();
        health = 100;
        playerImage = Assets.player ;

      //  animDown = new Animation(500, Assets.player);
    }

    @Override
    public void tick() {

        getInput(); // gets right, left and space button
        move(); // moves the player
        for(Bullet b : bullet){ // moves the bullet
            b.tick();
        }
        /**
         * if bullet outside of the game ,delete it
         */
        for(int i=0 ; i<bullet.size() ; i++){
            if(bullet.get(i).isDead()){
                bullet.remove(i);
            }
        }
    }

    private void getInput(){
        xMove = 0;
        yMove = 0;
        // left and right move
        if(game.getKeyManager().left){
            xMove = -getSpeed();
            if(x < 0){
                x = 0;
            }
        }else if(game.getKeyManager().right){
            xMove = getSpeed();
            if(x > game.width - DEFAULT_CREATURE_WIDTH){
                x = game.width - DEFAULT_CREATURE_WIDTH;
            }
        }else{
            xMove = 0;
        }
        if(game.getKeyManager().up){
            bullet.add(new Bullet(this.getX(),this.getY(), this));
        }


    }

    /**
     * shows the player and moves
     * @param g
     */
    @Override
    public void render(Graphics g) {

        g.drawImage(getCurrentAnimationFrame(), (int)x, (int)y, 32,32 , null);
        g.setColor(Color.red);
        g.drawString("SPEED:"+ xMove , 0, 0);
        for(Bullet b : bullet){
            b.render(g);
        }
    }

    private BufferedImage getCurrentAnimationFrame(){
        if(xMove < 0){
            return Assets.playerleft;
        }else if(xMove > 0){
            return Assets.playerright;
        }else if(xMove == 0){
            return Assets.player;
        }
        return null;
    }





    public ArrayList<Bullet> getBullet() {
        return bullet;
    }
}
