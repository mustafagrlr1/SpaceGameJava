/**
 * shows the high score state
 */

import java.awt.*;

/**
 * there are 5 high score needs to be showed
 */
public class HighScoreState extends State {
    public Rectangle firstHighScore;
    public Rectangle secondHighScore;
    public Rectangle thirdHighScore;
    public Rectangle fourthHighSCcore;
    public Rectangle fifthHighScore;
    public Rectangle quitButton ;

    public HighScoreState(GamePanel game){
        super(game);
        // place and size of the scores
        firstHighScore = new Rectangle(game.width / 2-100, 150, 250, 40);
        secondHighScore = new Rectangle(game.width / 2-100, 225, 250, 40);
        thirdHighScore = new Rectangle(game.width/ 2-100, 300, 250, 40 );
        fourthHighSCcore = new Rectangle(game.width/ 2-100, 375, 250, 40 );
        fifthHighScore = new Rectangle(game.width/ 2-100, 450, 250, 40 );
        quitButton = new Rectangle(game.width / 2 + 300, 600, 250, 40);
    }

    /**
     * if player wants to go back to menu
     */
    @Override
    public void tick() {

        if(game.getMouseManager().isLeftPressed() == true &&
                game.getMouseManager().getMouseX() >= quitButton.x &&
                game.getMouseManager().getMouseX() <= quitButton.x + 250 &&
                game.getMouseManager().getMouseY() >= quitButton.y &&
                game.getMouseManager().getMouseY() <= quitButton.y + 40){
            game.newGame();
            game.setState(game.getMenuState());
        }

    }

    /**
     * shows the all the scores
     * @param g
     */
    @Override
    public void render(Graphics g) {
        Graphics2D grap = (Graphics2D)g;
        g.drawImage(Assets.background,0,0 ,1280, 720, null);
        Font fnt0 = new Font("arial", Font.BOLD, 50);
        g.setFont(fnt0);
        g.setColor(Color.red);
        g.drawString("SPACE GAME",game.width / 2 - 200, 100);


        Font fnt1 = new Font("arial", Font.BOLD, 30);
        g.setFont(fnt1);

        g.setColor(Color.red);
        g.drawString(String.valueOf(HighScore.getHighScores().get(0)), firstHighScore.x,firstHighScore.y + 30 );
        grap.draw(firstHighScore);
        g.drawString(String.valueOf(HighScore.getHighScores().get(1)), secondHighScore.x,secondHighScore.y + 30 );
        grap.draw(secondHighScore);
        g.drawString(String.valueOf(HighScore.getHighScores().get(2)), thirdHighScore.x,thirdHighScore.y + 30 );
        grap.draw(thirdHighScore);
        g.drawString(String.valueOf(HighScore.getHighScores().get(3)), fourthHighSCcore.x,fourthHighSCcore.y + 30 );
        grap.draw(fourthHighSCcore);
        g.drawString(String.valueOf(HighScore.getHighScores().get(4)), fifthHighScore.x,fifthHighScore.y + 30 );
        grap.draw(fifthHighScore);
        g.drawString("      RETURN", quitButton.x ,quitButton.y + 30 );
        grap.draw(quitButton);
        ((Graphics2D) g).setBackground(Color.BLACK);
    }



}
