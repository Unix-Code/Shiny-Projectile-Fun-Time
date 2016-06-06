package backend;

import java.awt.Color;
import java.awt.Graphics;

/**
 *
 * @author David
 */
public class UI extends Obj {
    // private ItemSlot[] items;
    private XPBar xpBar;
    private Handler handler;
    
    public UI(Handler handler) {
        super(3*Game.width/5, 0, 2*Game.width/5, Game.height, ID.UI);
        xpBar = new XPBar(0, 100, handler);
    }
    
    public void tick() {
        xpBar.tick();
    }

    public void render(Graphics g) {
        xpBar.render(g);
        g.setColor(Color.GRAY);
        g.fillRect(x, y, w, h);
    }
    
}
