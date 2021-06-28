import java.awt.*;

public class Bullet extends Creature{

    private final int y_space = 12;
    private final int x_space = 2;
    Player player;
    private boolean dead;

    public Bullet(float x, float y, Player player) {
        super(x, y, 12, 2);
        this.player = player;
        dead = false;
    }

    @Override
    public void tick() {

        y -= y_space;
        if(y <= 0){
            dead = true;
        }
    }

    public void setDead(){
        this.dead = true;
    }

    public boolean isDead(){
        return dead;
    }
    @Override
    public void render(Graphics g) {
        g.drawImage(Assets.bullet, (int)x, (int)y , width,height, null);
    }
}
