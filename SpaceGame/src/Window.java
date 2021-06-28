/**
 * window of the all the stages
 */

import javax.swing.JFrame;
import java.awt.*;

public class Window extends JFrame{

    private JFrame frame;
    private Canvas canvas;

    private String title;
    private int width, height;

    public Window(String title, int width, int height){
        this.title = title;
        this.width = width;
        this.height = height;

        createDisplay();
    }

    private void createDisplay(){
        frame = new JFrame(title);// title of the window
        frame.setSize(width, height); // size of window
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);// exit button does not work if it does not exist
        frame.setResizable(false);// we can not resize the window
        frame.setLocationRelativeTo(null);// windows pops up center of the screen
        frame.setVisible(true);// makes visible to window

        canvas = new Canvas();// cizmek icin guc veren tool
        canvas.setPreferredSize(new Dimension(width, height)); // istenen boy ve yukseklik
        canvas.setMaximumSize(new Dimension(width, height)); // maks i ve mini ekliyoruz
        canvas.setMinimumSize(new Dimension(width, height));
        canvas.setFocusable(false);
        frame.add(canvas); // canvasi frame e ekliyoruz
        frame.pack();// we will able to see canvas fully

    }

    public Canvas getCanvas(){
        return canvas;
    }

    public JFrame getFrame(){
        return frame;
    }
}
