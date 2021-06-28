/**
 * if game is over show this state
 */

import java.awt.*;

public class GameOverState extends  State {




    public Rectangle quitButton ;
    public Rectangle scoreButton;

    public GameOverState(GamePanel game){
        super(game);
        scoreButton = new Rectangle(game.width / 2-250 , 600+30 , 250, 40);
        quitButton = new Rectangle(game.width / 2 + 300, 600, 250, 40);

    }

    @Override
    public void tick() {

        // if player wants to go menu in the menu
        if(game.getMouseManager().isLeftPressed() == true &&
                game.getMouseManager().getMouseX() >= quitButton.x &&
                game.getMouseManager().getMouseX() <= quitButton.x + 250 &&
                game.getMouseManager().getMouseY() >= quitButton.y &&
                game.getMouseManager().getMouseY() <= quitButton.y + 40){
            HighScore.getHighScores().add(GameState.score);
            HighScore.sort();
            GameState.score = 0;
            GameState.deadNumber =0;
            game.newGame();
            game.setState(game.getMenuState());
        }

    }

    /**
     * shows the game over state
     * @param g
     */
    @Override
    public void render(Graphics g) {
        Graphics2D grap = (Graphics2D)g;

        g.drawImage(Assets.gameover, game.width/2 - 100, game.height/2 -100, 200, 200, null);


        Font fnt1 = new Font("arial", Font.BOLD, 30);
        g.setFont(fnt1);

        g.setColor(Color.red);
        g.drawString("SCORE:" + String.valueOf(GameState.score), quitButton.x-250,quitButton.y+30  );
        grap.draw(quitButton);
        g.drawString("        RETURN", quitButton.x-40,quitButton.y+30  );
        grap.draw(quitButton);
        ((Graphics2D) g).setBackground(Color.BLACK);
    }
}
