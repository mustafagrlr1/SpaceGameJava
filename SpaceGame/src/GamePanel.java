/**
 * All the states stars in here
 * Thread is in here
 */

import java.awt.*;
import java.awt.image.BufferStrategy;

public class GamePanel implements Runnable{

    private Window window; // window of the all the states
    public int width, height; /// window height and width
    public String title; // title of the game
    private boolean running = false; // is game running
    private Thread thread;
    private HighScore highScore; // holds gihgscores

    private BufferStrategy bs; // buffer a hidden computer screen within your computer
    private Graphics graphic; // rendering players bombs all stuff ext..

    private KeyManager keyManager; // players move left arrow going left, right arrow going right , space is making bullet
    private MouseManager mouseManager; // for menu state


    //States
    private State gameState; // all the levels embedded in here
    private State menuState; // entrance of the game
    private State howToPlayState; // I just want the show how to play the game
    private State gameOverState; // if game is over
    private State winGameState; // if player win the game
    private State highScoreState; // shows the 5 high score // reads from file and writes again
    private State waitLevelState; // if level changed, waits user signal

    //level
    private int level; // level of game
    private boolean gameOver = false; // is game over


    /**
     * Constructor of gamepanel
     * @param title of project
     * @param width of project
     * @param height of project
     */
    public GamePanel(String title, int width, int height){
        this.width = width;
        this.height = height;
        this.title = title;
        keyManager = new KeyManager();
        mouseManager = new MouseManager();
        level = 1;
        highScore = new HighScore(this);


    }

    /**
     * keybord control
     * @return
     */
    public KeyManager getKeyManager(){
        return keyManager;
    }

    /**
     * mouse control
     * @return
     */
    public MouseManager getMouseManager(){ return mouseManager;}

    /**
     * if game over or still running
     * @param over
     */
    public void setGameOver(boolean over){
        gameOver = over;
    }

    /**
     * level of game
     * @return
     */
    public int getLevel(){
        return level;
    }

    /**
     * increments level of the game
     */
    public void setLevel(){
        ++level;
    }

    /**
     * if level has been changed, players and enemys feature can be changed
     */
    public void newGame(){

        gameState = new GameState(this);
        menuState = new MenuState(this);
        howToPlayState = new HowToPlayState(this);
        gameOverState = new GameOverState(this);
        highScoreState = new HighScoreState(this);
        waitLevelState = new WaitLevelState(this);

    }


    /**
     * state can be changed depend on user
     * @param state
     */
    public void setState(State state){
        State.setState(state);
    }

    /**
     * state of the game
     * @return
     */
    public State getState(){
        return State.getState();
    }

    /**
     * gets menu state
     * @return
     */
    public State getMenuState(){
        return menuState;
    }

    /**
     * gets howToPlay state
     * @return
     */
    public State getHowToPlayState(){
        return howToPlayState;
    }

    /**
     * gets gameOverState
     * @return
     */
    public State getGameOverState(){
        return gameOverState;
    }


    public boolean getGameOver(){
        return gameOver;
    }

    /**
     * gets game state
     * @return
     */
    public State getGameState() {
        return gameState;
    }

    /**
     * gets highscore
     * @return
     */
    public State getHighScoreState(){
        return highScoreState;
    }


    /**
     * starts all the window and states
     */
    private void init(){
        window = new Window(title, width, height);
        window.getFrame().addKeyListener(keyManager);
        window.getFrame().addMouseListener(mouseManager);
        window.getFrame().addMouseMotionListener(mouseManager);
        window.getCanvas().addMouseListener(mouseManager);
        window.getCanvas().addMouseMotionListener(mouseManager);
        Assets.init();
        gameState = new GameState(this);
        menuState = new MenuState(this);
        howToPlayState = new HowToPlayState(this);
        gameOverState = new GameOverState(this);
        winGameState = new WinGameState(this);
        highScoreState = new HighScoreState(this);
        waitLevelState = new WaitLevelState(this);
        State.setState(menuState); // starts with menu state

    }

    /**
     * all the background state
     */
    private void tick(){
        keyManager.tick(); // if keybord has been clicked
        if(State.getState() != null && State.getState() == gameState){
            State.getState().tick();
        }else if(State.getState() != null && State.getState() == menuState){
            State.getState().tick();
        }else if(State.getState() != null && State.getState() == howToPlayState){
            State.getState().tick();
        }else if(State.getState() != null && State.getState() == gameOverState) {
            State.getState().tick();
        }else if(State.getState() != null && State.getState() == winGameState) {
            State.getState().tick();
        }
        else if(State.getState() != null && State.getState() == highScoreState) {
            State.getState().tick();
        }
        else if(State.getState() != null && State.getState() == waitLevelState) {
            State.getState().tick();
        }

    }

    /**
     * all the front and rendering process
     */
    public void render(){
         bs = window.getCanvas().getBufferStrategy();
        if(bs == null){
            window.getCanvas().createBufferStrategy(3); // if there is no buffered Strategy create 3 times
            return;
        }
        graphic = bs.getDrawGraphics();

        //clear the screen
        graphic.clearRect(0, 0, width, height); // clear all the drawing stuff
        //Draw here
        if(getLevel() == 1){
            graphic.drawImage(Assets.background3,0,0 ,width, height, null); // for level 1 background
        }else if(getLevel() == 2){ // for level 2 background
            graphic.drawImage(Assets.background2,0,0 ,width, height, null);
        }else if(getLevel() == 3){ // for level 3 background
            graphic.drawImage(Assets.background,0,0 ,width, height, null);
        }
        if(State.getState() != null){
            State.getState().render(graphic); // shows the current state
            if(getGameOver()){ // if game over  end the game
                setState(getGameOverState());
                setGameOver(false);

            }

        }

        //End drawing!
        bs.show();
        graphic.dispose();


    }

    /**
     * thread starts
     */
    @Override
    public void run() {

        init();

        int fps = 60;   // fps of the game
        double timePerTick = 1000000000/fps; // nanosaniye/fps
        double delta = 0;
        long now;
        long lastTime = System.nanoTime();

        while(running) {
            now = System.nanoTime();
            delta += (now - lastTime) / timePerTick;
            lastTime = now;

            if (delta >= 1) {

                tick();
                render();

                if(GameState.deadNumber == getLevel()*9){ // if level needs to be changed
                    if(getLevel() == 3){ // if level 3 has been completed finish the game
                        State.setState(winGameState);
                    }else {
                        State.setState(waitLevelState); // if other levels have been completed
                    }
                }
                delta--;
            }
        }

        stop();
    }

    /**
     *thread starts
     */
    public synchronized  void start(){
        if(running){ // if game is still running
            return;
        }
        running = true;
        thread = new Thread(this);
        thread.start();
    }

    public synchronized  void stop(){
        if(!running) // if game has been stopped earlier
            return;
        running = false;
        try {
            thread.join(); // close the thread safely
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
