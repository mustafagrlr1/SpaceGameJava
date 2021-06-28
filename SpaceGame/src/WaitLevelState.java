/**
 * shows the player mouse move, shows the score and goes the new level
 */

import java.awt.*;

public class WaitLevelState extends  State {



    public Rectangle newLevel ;
    public Rectangle quitButton ;
    public Rectangle scoreButton;

    public WaitLevelState(GamePanel game){
        super(game);
        scoreButton = new Rectangle(game.width / 2-250 , 600+30 , 250, 40);
        quitButton = new Rectangle(game.width / 2 + 300, 600, 250, 40);
        newLevel = new Rectangle(game.width/2-100 , game.height/2-100, 250, 40);
    }

    @Override
    public void tick() {

        if(game.getMouseManager().isLeftPressed() == true &&
                game.getMouseManager().getMouseX() >= quitButton.x &&
                game.getMouseManager().getMouseX() <= quitButton.x + 250 &&
                game.getMouseManager().getMouseY() >= quitButton.y &&
                game.getMouseManager().getMouseY() <= quitButton.y + 40){
            HighScore.getHighScores().add(GameState.score);
            HighScore.sort();
            GameState.score = 0;
            GameState.deadNumber = 0;
            game.newGame();
            game.setState(game.getMenuState());
        }
        if(game.getMouseManager().isLeftPressed() == true &&
                game.getMouseManager().getMouseX() >= newLevel.x &&
                game.getMouseManager().getMouseX() <= newLevel.x + 250 &&
                game.getMouseManager().getMouseY() >= newLevel.y &&
                game.getMouseManager().getMouseY() <= newLevel.y + 40){
            game.setLevel();
            game.newGame();
            GameState.deadNumber = 0;
            game.setState(game.getGameState());
        }

    }

    /**
     * shows method
     * @param g
     */
    @Override
    public void render(Graphics g) {
        Graphics2D grap = (Graphics2D)g;

        Font fnt1 = new Font("arial", Font.BOLD, 30);
        g.setFont(fnt1);
        g.setColor(Color.red);
        g.drawString("   NEW LEVEL", game.width/2 -100, game.height/2-100 + newLevel.height-10 );
        grap.draw(newLevel);


        g.drawString("SCORE:" + String.valueOf(GameState.score), quitButton.x-250,quitButton.y+30  );

        g.drawString("        RETURN", quitButton.x-40,quitButton.y+30  );
        grap.draw(quitButton);
        ((Graphics2D) g).setBackground(Color.BLACK);
    }
}
