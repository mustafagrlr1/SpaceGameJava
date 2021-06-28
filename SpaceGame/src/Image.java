/**
 * loads image to the game
 */

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Image {

    public static BufferedImage loadImage(String path){
        try {
            return ImageIO.read(new File(path)); // reads image from file
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }
        return null;
    }

}
