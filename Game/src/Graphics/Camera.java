package Graphics;

import backend.Game;
import backend.Player;

/**
 *
 * @author David
 */
public class Camera {
    private int x, y;

    public Camera(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void tick(Player p) {
        x = -p.getX() + Game.width/2;
        y = -p.getY() + Game.height/2;
    }
    
    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    
    
}
