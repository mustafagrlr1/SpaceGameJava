/**
 * keybord moves
 */

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
public class KeyManager implements KeyListener {

    private boolean[] keys;
    public boolean  left, right, up;

    public KeyManager(){
        keys = new boolean[256];
    }

    public void tick(){
        left = keys[KeyEvent.VK_LEFT];
        right = keys[KeyEvent.VK_RIGHT];
        up = keys[KeyEvent.VK_SPACE];
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        keys[e.getKeyCode()] = true;
    }

    @Override
    public void keyReleased(KeyEvent e) {
        keys[e.getKeyCode()] = false;
    }
}
