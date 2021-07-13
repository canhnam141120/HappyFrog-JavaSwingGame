
package keyboard;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyController implements KeyListener{
    
    private boolean press = false;
    private boolean release = true;
    
    public boolean isPress() {
        return press;
    }

    public void setPress(boolean press) {
        this.press = press;
    }

    public boolean isRelease() {
        return release;
    }

    public void setRelease(boolean release) {
        this.release = release;
    }
    
    @Override
    public void keyTyped(KeyEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void keyPressed(KeyEvent e) { //keyPressed nút mũi tên up
        if(e.getKeyCode() == KeyEvent.VK_UP){
            if(release){
                press = true;
                release = false;
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        release = true;
    }
    
}
