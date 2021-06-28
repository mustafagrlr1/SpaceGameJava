/**
 * shows the how to play image
 */

import java.awt.*;


public class HowToPlayState extends State {

    public Rectangle quitButton ;

    public HowToPlayState(GamePanel game){
        super(game);

        quitButton = new Rectangle(game.width / 2+200, 600, 250, 40);


    }

    @Override
    public void tick() {

        if(game.getMouseManager().isLeftPressed() == true &&
                game.getMouseManager().getMouseX() >= quitButton.x &&
                game.getMouseManager().getMouseX() <= quitButton.x + 250 &&
                game.getMouseManager().getMouseY() >= quitButton.y &&
                game.getMouseManager().getMouseY() <= quitButton.y + 40){
            game.setState(game.getMenuState());
        }

    }

    @Override
    public void render(Graphics g) {
        Graphics2D grap = (Graphics2D)g;
        Font fnt0 = new Font("arial", Font.BOLD, 50);
        g.setFont(fnt0);
        g.setColor(Color.red);

        g.drawImage(Assets.howToPlay, 0, 0 , 1280, 720,  null);

        Font fnt1 = new Font("arial", Font.BOLD, 30);
        g.setFont(fnt1);


        g.drawString("        QUIT", quitButton.x ,quitButton.y + 30 );
        grap.draw(quitButton);
        ((Graphics2D) g).setBackground(Color.BLACK);
    }
}
