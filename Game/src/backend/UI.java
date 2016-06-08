package backend;

import java.awt.Color;
import java.awt.Graphics;

/**
 *
 * @author David
 */
public class UI extends Obj {
    // private ItemSlot[] items;
    private Handler handler;
    
    public UI(Handler handler) {
        super(3*Game.width/5, 0, 2*Game.width/5, Game.height, ID.UI);
    }
    
    public void tick() {
    }

    public void render(Graphics g) {
        g.setColor(Color.GRAY);
        g.fillRect(x, y, w, h);
    }
    
}
