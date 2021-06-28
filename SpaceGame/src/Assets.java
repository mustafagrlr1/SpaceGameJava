/**
 * images have been uploaded in here
 */

import java.awt.image.BufferedImage;

public class Assets {

    private static  int width = 32, height = 32;


    public Assets(int width, int height){
        this.width = width;
        this.height = height;
    }

    public static BufferedImage playerleft;
    public static BufferedImage playerright;
    public static BufferedImage player;
    public static BufferedImage background;
    public static BufferedImage background2;
    public static BufferedImage background3;
    public static BufferedImage enemy;
    public static BufferedImage enemy2;
    public static BufferedImage barricade;
    public static BufferedImage bullet;
    public static BufferedImage bomb;
    public static BufferedImage explosionSpriteSheet;
    public static BufferedImage[] explosionBomb;
    public static BufferedImage howToPlay;
    public static BufferedImage gameover;
    public static BufferedImage winGame;

    public static void init(){
        player = Image.loadImage("ship5.png");
        playerleft = player.getSubimage(210,420,210,420);
        playerright = player.getSubimage(630,420,210,420);
        player = player.getSubimage(0,420,210,420);

        background = (Image.loadImage("dunya.jpg"));
        background2 = (Image.loadImage("bg.jpg"));
        background3 = (Image.loadImage("bge.jpg"));
        enemy = (Image.loadImage("enemyShip.png"));
        enemy2 = (Image.loadImage("uzayli.png"));
        barricade = (Image.loadImage("barricade1.png"));
        bullet = (Image.loadImage("shot.png"));
        bomb = (Image.loadImage("bomb.png"));
        explosionSpriteSheet = (Image.loadImage("explosion.png"));
        explosionBomb = new BufferedImage[16];
      /*  explosionBomb[0] = explosionSpriteSheet.getSubimage(0, 0, 105, 110 );
        explosionBomb[1] = explosionSpriteSheet.getSubimage(105, 0, 105, 110 );
        explosionBomb[2] = explosionSpriteSheet.getSubimage(210, 0, 105, 110 );
        explosionBomb[3] = explosionSpriteSheet.getSubimage(315, 0, 105, 110 );
        explosionBomb[4] = explosionSpriteSheet.getSubimage(420, 0, 105, 110 );
        explosionBomb[5] = explosionSpriteSheet.getSubimage(525, 0, 105, 110 );
        explosionBomb[6] = explosionSpriteSheet.getSubimage(630, 0, 105, 110 );
        explosionBomb[7] = explosionSpriteSheet.getSubimage(735, 0, 105, 110 );
        explosionBomb[8] = explosionSpriteSheet.getSubimage(0, 105, 105, 110 );
        explosionBomb[9] = explosionSpriteSheet.getSubimage(105, 105, 105, 110 );
        explosionBomb[10] = explosionSpriteSheet.getSubimage(210, 105, 105, 110 );
        explosionBomb[11] = explosionSpriteSheet.getSubimage(315, 105, 105, 110 );
        explosionBomb[12] = explosionSpriteSheet.getSubimage(420, 105, 105, 110 );
        explosionBomb[13] = explosionSpriteSheet.getSubimage(525, 105, 105, 110 );
        explosionBomb[14] = explosionSpriteSheet.getSubimage(630, 105, 105, 110 );
        explosionBomb[15] = explosionSpriteSheet.getSubimage(735, 105, 105, 110 );
*/

        howToPlay = (Image.loadImage("howtoplay.jpg"));
        gameover = (Image.loadImage("gameover.png"));
        winGame = (Image.loadImage("wingame1.png"));
    }


}
