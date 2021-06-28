/**
 * super class of the all players
 */

import java.awt.*;


public abstract class Entity {

    /**
     * all the important features
     */
    protected float x,y;
    protected int width, height;


    public Entity(float x, float y, int width, int height){
        this.x = x;
        this.y = y;
        this.height = height;
        this.width = width;

    }



    public abstract void tick();

    public abstract void render(Graphics g);


    /**
     * getters and setters
     * @return
     */
    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }
}
