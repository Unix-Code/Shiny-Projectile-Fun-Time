package backend;

import java.awt.Color;
import java.awt.Graphics;

/**
 *
 * @author David
 */
public class XPBar extends ValueBar {
    private Handler handler;
    
    public XPBar(double currValue, double maxValue, Handler handler) {
        super(currValue, maxValue);
        this.handler = handler;
    }

    public void tick() {
        this.calcValue();
    }

    public void render(Graphics g) {
        g.setColor(Color.BLUE);
        g.fillRect(handler.getPlayer().getX() + 50, handler.getPlayer().getY(), w, h);
        g.setColor(Color.DARK_GRAY);
        g.drawRect(handler.getPlayer().getX() + 49, handler.getPlayer().getY() - 1, w + 1, h + 1);
    }

    public void calcValue() {
        int exp = 0;
        for (int i = 0; i < handler.deadThings.size(); i++) {
            Character deadObject = handler.deadThings.get(i);
            exp += deadObject.getXPValue();
        }
        this.setCurrValue(exp);
        System.out.println("Current XP: " + this.getCurrValue());
    }
            
}
