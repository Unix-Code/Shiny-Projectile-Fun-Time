package backend;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.util.ArrayList;

/**
 *
 * @author David
 */
public class XPBar extends ValueBar {
    private Handler handler;
    private int level;
    
    public XPBar(double currValue, double maxValue, Handler handler) {
        super(currValue, maxValue);
        this.handler = handler;
        level = 1;
    }

    public void tick() {
        this.calcValue();
        this.x = handler.getPlayer().getX() - Game.width/2 + Game.width + 50;
        this.y = handler.getPlayer().getY() - Game.width/2 + 150;
        if (this.getCurrValue() >= this.getMaxValue()) {
            level++;
            double leftover = this.getCurrValue() - this.getMaxValue();
            System.out.println("LeftOver: " + leftover);
            this.setCurrValue(leftover);
            this.setMaxValue(this.getMaxValue() * (Math.pow(1.05, level)));
        }
    }

    public int getLevel() {
        return level;
    }

    public void render(Graphics g) {
        g.setColor(Color.BLUE);
        g.fillRect(x, y, (int) (w * (this.getCurrValue()/this.getMaxValue())), h);
        g.setColor(Color.DARK_GRAY);
        g.drawRect(x, y, w, h);
        g.setColor(Color.red);
        g.setFont(new Font("Helvetica", Font.BOLD, 16));
        g.drawString(String.valueOf(level), x + w/2 - 2, y - 8);
    }

    public void calcValue() {
        if (handler.deadThings.size() > 0) {
            for (int i = 0; i < handler.deadThings.size(); i++) {
                Enemy deadObject = handler.deadThings.get(i);
                this.gainXP(deadObject.getXPValue());
            }
            handler.deadThings = new ArrayList<>();            
            System.out.println("Current XP: " + this.getCurrValue() + "\nCurrent Level: " + level);
        }
    }
    
    private void gainXP(double xp) {
        this.setCurrValue(this.getCurrValue() + xp);
    } 
}
