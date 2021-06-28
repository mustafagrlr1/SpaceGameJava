import java.awt.*;

/**
 * this plays to game starts
 */
public class Game {
    public static void main(String[] args){

        GamePanel game = new GamePanel("Proje", 1280, 720);


        while(true) {
            // thread starts in here
            game.start();
        }
    }

}
