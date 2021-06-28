/**
 * Shows all the game levels
 */

import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

public class GameState extends State{

    private Player player;
    private ArrayList<Enemy> enemy1;
    private ArrayList<Enemy> enemy2;
    private ArrayList<Enemy> enemy3;
    private ArrayList<Barricade> barricade;
    private int enemyDistance; // distance of enemy between each others
    private Bomb bomb;
    private Explosion explosion;
    private Explosion explosionEnemy;
    private Explosion explosionBarricade;
    public static int score = 0; // score of game
    public static int deadNumber;

    private Thread th;

    Stopwatch stopwatch; // shows the time

    private boolean lostGame = false;
    private boolean winGame = false;
    long lastTime, now;

    /**
     * constructor of game state
     * @param game
     */
    public GameState(GamePanel game){
        super(game);
        if(game.getLevel() == 1){ // if level 1 there is more distance between enemies
            enemyDistance = 150;
        }else if(game.getLevel() == 2){
            enemyDistance = 100;
        }else{
            enemyDistance = 50;
        }
        player = new Player(game,600, 600);
        enemy1 = new ArrayList<>();
        enemy2 = new ArrayList<>();
        enemy3 = new ArrayList<>();
        barricade = new ArrayList<>();
        for(int i=0 ; i<game.getLevel()*3 ; i++){
            enemy1.add(new Enemy(game, enemyDistance*(i+1) , 100, player));
            enemy2.add(new Enemy(game, enemyDistance*(i+1) , 200, player));
            enemy3.add(new Enemy(game, enemyDistance*(i+1) , 300, player));
        }
        for(int i=0 ; i<4 ; i++){
            barricade.add(new Barricade(game , 300+(i*200), 500));
        }

        lastTime = System.currentTimeMillis();

        bomb = null;

        stopwatch = new Stopwatch();
        stopwatch.start();

        // enemies feature can be change depends on levels
        if(game.getLevel() == 1){
            player.setSpeed(1);
            for(int i=0; i<3 ; i++){
                for(int j=0 ; j<game.getLevel()*3; j++){
                    enemy1.get(j).setEnemyImage(Assets.enemy2);
                    enemy1.get(j).setSpeed(3);
                }
            }
        }else if(game.getLevel() == 2){
            player.setSpeed(3);
            for(int i=0; i<3 ; i++){
                for(int j=0 ; j<game.getLevel()*3; j++){
                    enemy1.get(j).setEnemyImage(Assets.enemy2);
                    enemy1.get(j).setSpeed(5);
                    enemy2.get(j).setEnemyImage(Assets.enemy2);
                    enemy2.get(j).setSpeed(5);

                }
            }

        }else if(game.getLevel() == 3){
            player.setSpeed(5);
            for(int i=0; i<3 ; i++){
                for(int j=0 ; j<game.getLevel()*3; j++){
                    enemy1.get(j).setEnemyImage(Assets.enemy2);
                    enemy1.get(j).setSpeed(7);
                    enemy2.get(j).setEnemyImage(Assets.enemy2);
                    enemy2.get(j).setSpeed(7);
                    enemy3.get(j).setEnemyImage(Assets.enemy2);
                    enemy3.get(j).setSpeed(7);

                }
            }

        }
    }


    /**
     * calculates all the background stages
     */
    @Override
    public void tick() {

        now = System.currentTimeMillis();
        player.tick(); // players move

         //enemies move
        for(int i=0 ; i<game.getLevel()*3 ; i++){
            enemy1.get(i).tick();
            enemy2.get(i).tick();
            enemy3.get(i).tick();
        }
        for(int i=0 ; i<4 ; i++){
            barricade.get(i).tick();
        }

        // creates new bomb for the only one enemy
        if((now - lastTime) > 10000 ){
            Random random = new Random();

            int j = random.nextInt(3);
            int i = generateRandom(j);
            if( j == 2){
                if(!enemy3.get(i).isDead())
                    bomb = new Bomb(enemy3.get(i).getX()+10, enemy3.get(i).getY() + 36, player);
            }
            if( j == 1){
                if(!enemy2.get(i).isDead())
                    bomb = new Bomb(enemy2.get(i).getX()+10, enemy2.get(i).getY() + 36, player);
            }
            if( j == 0){
                if(!enemy1.get(i).isDead())
                    bomb = new Bomb(enemy1.get(i).getX()+10, enemy1.get(i).getY() + 36, player);
            }
            lastTime = now;
        }
        if(explosion != null){
            explosion.tick();
        }
        if(explosionBarricade != null){
            explosionBarricade.tick();
        }
        if(explosionEnemy != null){
            explosionEnemy.tick();
        }

        if(bomb != null ) {
            bomb.tick();
        }
    }
    private int generateRandom(int j){


        Random random = new Random();
        int i = random.nextInt(game.getLevel()*3);
        if( j == 2){
            if(!enemy3.get(i).isDead())
                return i;

        }
        if( j == 1){
            if(!enemy2.get(i).isDead()) {
                return i;
            }
        }
        if( j == 0) {
            if (!enemy1.get(i).isDead()) {
                return i;
            }
        }
        //to prevent the memory leak
        for(int k=0 ; k<3; k++){
            for(int m=0 ; m<game.getLevel()*3 ; m++){
                if(!enemy1.get(m).isDead()){
                    return m;
                }if(!enemy2.get(m).isDead()){
                    return m;
                }if(!enemy3.get(m).isDead()){
                    return m;
                }
            }
        }
        return i;

    }

    // explosion needs to be in the screen just 25 milisecond
    int bombSecond=0;
    int bombSecond1=0;
    int bombBarricade=0;

    /**
     * shows the images of players
     * @param g
     */
    @Override
    public void render(Graphics g){


        player.render(g);
        for(int i=0 ; i<game.getLevel()*3 ; i++){
            enemy1.get(i).render(g);
            enemy2.get(i).render(g);
            enemy3.get(i).render(g);
        }

        for(int i=0 ; i<4 ; i++){
            barricade.get(i).render(g);
        }
        // if there is a bomb ,it needs to be show
        // player and bomb could be in a collision, needs to be handled here
        if(bomb != null ) {
            bomb.render(g);
            if(bomb.getX() >= player.getX() && bomb.getX() <= player.getX() + player.getWidth()
                    && bomb.getY() >= player.getY() && bomb.getY() <= player.getY() + (player.getHeight())){
                explosion = new Explosion(player.getX(), player.getY(),0, 0);
                player.setHealth(player.getHealth()-50);
                if(player.getHealth() <= 0){
                    game.setGameOver(true);
                }
            }
        }

         //if there is explosion needs to be showed in here
        if(explosion != null){
            explosion.render(g);
            if(bombSecond == 25){
                explosion = null;
                bombSecond = 0;
            }
            bombSecond++;
        }
        // if there is a collision between player bullet and enemy , needs to be handled in here
        for(int j=0 ; j<player.getBullet().size() ; j++){
            for(int i=0 ; i<game.getLevel()*3 ; i++) {
                if(player.getBullet().get(j).getX() >= enemy1.get(i).getX() && !enemy1.get(i).isDead() &&
                    player.getBullet().get(j).getX() <= enemy1.get(i).getX() + enemy1.get(i).getWidth() &&
                    player.getBullet().get(j).getY() >= enemy1.get(i).getY() &&
                    player.getBullet().get(j).getY() <= enemy1.get(i).getY() + enemy1.get(i).getHeight()){
                    explosionEnemy = new Explosion(enemy1.get(i).getX(),enemy1.get(i).getY(),
                                               0, 0);
                    player.getBullet().get(j).setDead();
                    enemy1.get(i).setHealth(enemy1.get(i).getHealth()-10);
                    score += 10;
                    if(enemy1.get(i).getHealth() <= 0){
                        deadNumber++;
                    }
                }
                if(player.getBullet().get(j).getX() >= enemy2.get(i).getX() && !enemy2.get(i).isDead() &&
                        player.getBullet().get(j).getX() <= enemy2.get(i).getX() + enemy2.get(i).getWidth() &&
                        player.getBullet().get(j).getY() >= enemy2.get(i).getY() &&
                        player.getBullet().get(j).getY() <= enemy2.get(i).getY() + enemy2.get(i).getHeight()){
                    explosionEnemy = new Explosion(enemy2.get(i).getX(),enemy2.get(i).getY(),
                            0, 0);
                    player.getBullet().get(j).setDead();
                    enemy2.get(i).setHealth(enemy2.get(i).getHealth()-10);
                    score += 20;
                    if(enemy2.get(i).getHealth() <= 0){
                        deadNumber++;
                    }
                }
                if(player.getBullet().get(j).getX() >= enemy3.get(i).getX() && !enemy3.get(i).isDead() &&
                        player.getBullet().get(j).getX() <= enemy3.get(i).getX() + enemy3.get(i).getWidth() &&
                        player.getBullet().get(j).getY() >= enemy3.get(i).getY() &&
                        player.getBullet().get(j).getY() <= enemy3.get(i).getY() + enemy3.get(i).getHeight()){
                    explosionEnemy = new Explosion(enemy1.get(i).getX(),enemy3.get(i).getY(),
                            0, 0);
                    player.getBullet().get(j).setDead();
                    enemy3.get(i).setHealth(enemy3.get(i).getHealth()-10);
                    score += 30;
                    if(enemy3.get(i).getHealth() <= 0){
                        deadNumber++;
                    }
                }
            }
        }
        // if there is a collision between player bullet and barricade needs to be handled in here
        for(int j=0 ; j<player.getBullet().size() ; j++){
            for(int i=0 ; i<barricade.size() ; i++){
                if(player.getBullet().get(j).getX() >= barricade.get(i).getX() && !barricade.get(i).isDead() &&
                    player.getBullet().get(j).getX() <= barricade.get(i).getX() + barricade.get(i).getWidth() &&
                    player.getBullet().get(j).getY() >= barricade.get(i).getY() &&
                    player.getBullet().get(j).getY() >= barricade.get(i).getY() + barricade.get(i).getHeight()){
                    explosionBarricade = new Explosion(barricade.get(i).getX(),barricade.get(i).getY(),
                            0, 0);
                    player.getBullet().get(j).setDead();
                    barricade.get(i).setHealth(barricade.get(i).getHealth()-1);
                    score += 1;
                }
            }
        }

        // explosion of enemy
        if(explosionEnemy != null){

            explosionEnemy.render(g);
            if(bombSecond1 == 25){
                explosionEnemy = null;
                bombSecond1 = 0;
            }
            bombSecond1++;
        }
        // explosion of barricade
        if(explosionBarricade != null){
            explosionBarricade.render(g);
            if(bombBarricade == 25){
                explosionBarricade = null;
                bombBarricade = 0;
            }
            bombBarricade++;
        }
        // information about game
        g.drawString("Score:" + String.valueOf(score), 1100, 700);
        g.drawString("Health:" + String.valueOf(player.getHealth()), 1100, 690);
        g.drawString("Speed:" + String.valueOf(player.getSpeed()), 1200, 700);

        g.drawString("Time:" + stopwatch.getElapsedTimeSeconds(), 1200 , 690);

    }

    /**
     * timer of the game
     */
    public class Stopwatch {
        private long startTime;

        public void start() {
            startTime = System.currentTimeMillis();
        }

        public int getElapsedTimeSeconds() {
            return (int) ((int)(System.currentTimeMillis() - startTime) / 1000f);
        }
    }


}
