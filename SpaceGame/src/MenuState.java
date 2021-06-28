/**
 * shows the menu of the game
 */

import java.awt.*;


import static java.lang.System.exit;

public class MenuState extends State{

    public Rectangle playButton ;
    public Rectangle howToPlayButton ;
    public Rectangle highScoreButton ;
    public Rectangle quitButton ;

    public MenuState(GamePanel game){
        super(game);
        //size of the buttons
        playButton = new Rectangle(game.width / 2-100, 150, 250, 40);
        quitButton = new Rectangle(game.width / 2-100, 450, 250, 40);
        howToPlayButton = new Rectangle(game.width/ 2-100, 225, 250, 40 );
        highScoreButton = new Rectangle(game.width/ 2-100, 300, 250, 40 );

    }

    @Override
    public void tick() {
        if(game.getMouseManager().isLeftPressed() == true &&
                game.getMouseManager().getMouseX() >= playButton.x &&
                game.getMouseManager().getMouseX() <= playButton.x + 250 &&
                game.getMouseManager().getMouseY() >= playButton.y &&
                game.getMouseManager().getMouseY() <= playButton.y + 40){
            game.setState(game.getGameState());
        }
        if(game.getMouseManager().isLeftPressed() == true &&
                game.getMouseManager().getMouseX() >= howToPlayButton.x &&
                game.getMouseManager().getMouseX() <= howToPlayButton.x + 250 &&
                game.getMouseManager().getMouseY() >= howToPlayButton.y &&
                game.getMouseManager().getMouseY() <= howToPlayButton.y + 40){
            game.setState(game.getHowToPlayState());
        }
        if(game.getMouseManager().isLeftPressed() == true &&
                game.getMouseManager().getMouseX() >= highScoreButton.x &&
                game.getMouseManager().getMouseX() <= highScoreButton.x + 250 &&
                game.getMouseManager().getMouseY() >= highScoreButton.y &&
                game.getMouseManager().getMouseY() <= highScoreButton.y + 40){
            game.setState(game.getHighScoreState());
        }
        if(game.getMouseManager().isLeftPressed() == true &&
                game.getMouseManager().getMouseX() >= quitButton.x &&
                game.getMouseManager().getMouseX() <= quitButton.x + 250 &&
                game.getMouseManager().getMouseY() >= quitButton.y &&
                game.getMouseManager().getMouseY() <= quitButton.y + 40){
            HighScore.sort();
            HighScore.writeFile();
            exit(0);
        }

    }

    @Override
    public void render(Graphics g) {
        Graphics2D grap = (Graphics2D)g;

        Font fnt0 = new Font("arial", Font.BOLD, 50);
        g.setFont(fnt0);
        g.setColor(Color.red);
        g.drawString("  SPACE GAME",game.width / 2 - 200, 100);


        Font fnt1 = new Font("arial", Font.BOLD, 30);
        g.setFont(fnt1);

        g.setColor(Color.red);
        g.drawString("       PLAY", playButton.x,playButton.y + 30 );
        grap.draw(playButton);
        g.drawString(" HIGH SCORES", highScoreButton.x,highScoreButton.y + 30 );
        grap.draw(highScoreButton);
        g.drawString(" HOW TO PLAY", howToPlayButton.x, howToPlayButton.y+30);
        grap.draw(howToPlayButton);
        g.drawString("        QUIT", quitButton.x ,quitButton.y + 30 );
        grap.draw(quitButton);
    }




}
