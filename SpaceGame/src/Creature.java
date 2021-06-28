/**
 * Abstract class of players
 */

public abstract class Creature extends Entity {

    /**
     * Default features of players
     */
    protected static final int DEFAULT_HEALTH = 100;
    protected static final float DEFAULT_SPEED = 3.0f;
    public static final int DEFAULT_CREATURE_WIDTH = 34,
                            DEFAULT_CREATURE_HEIGHT = 34;

    protected int health;
    protected float speed;
    protected float xMove, yMove;
    protected boolean dead;

    public Creature(float x, float y, int width, int height) {
        super(x,y,width,height);
        health = DEFAULT_HEALTH;
        speed = DEFAULT_SPEED;
        xMove = 0;
        yMove = 0;
        dead = false;
    }

    /**
     * players health can be 0, so they are dead needs to be set
     * @return
     */
    public boolean isDead(){
        if(health > 0){
            return false;
        }
        return true;
    }

    /**
     * players automatically can move , default movement
     */
    public void move(){
        x += xMove;
        y += yMove;
    }

    //getter and setters
    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public float getSpeed() {
        return speed;
    }

    public void setSpeed(float speed) {
        this.speed = speed;
    }


}
