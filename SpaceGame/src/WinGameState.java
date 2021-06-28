/**
 * shows this state if player wins the game
 */

import java.awt.*;


public class WinGameState extends  State {




    public Rectangle quitButton ;
    public Rectangle scoreButton;

    public WinGameState(GamePanel game){
        super(game);
        scoreButton = new Rectangle(game.width / 2-250 , 600+30 , 250, 40);
        quitButton = new Rectangle(game.width / 2 + 300, 600, 250, 40);

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

    }

    @Override
    public void render(Graphics g) {
        Graphics2D grap = (Graphics2D)g;

        g.drawImage(Assets.winGame, game.width/2 - 100, game.height/2 -100, 200, 200, null);


        Font fnt1 = new Font("arial", Font.BOLD, 30);
        g.setFont(fnt1);

        g.setColor(Color.red);
        g.drawString("SCORE:" + String.valueOf(GameState.score), quitButton.x-250,quitButton.y+30  );

        g.drawString("        RETURN", quitButton.x-40,quitButton.y+30  );
        grap.draw(quitButton);
        ((Graphics2D) g).setBackground(Color.BLACK);
    }
}
