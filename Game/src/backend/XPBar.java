package backend;

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
        g.fillRect(x, y, w, h);
    }

    public void calcValue() {
        int exp = 0;
        for (int i = 0; i < handler.deadThings.size(); i++) {
            Character deadObject = handler.deadThings.get(i);
            exp += deadObject.getXPValue();
        }
        this.setCurrValue(exp);
    }
            
}
