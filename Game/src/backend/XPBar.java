package backend;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import javax.imageio.ImageIO;

/**
 *
 * @author David
 */
public class XPBar extends ValueBar {
    private Handler handler;
    private int level;
    
    private BufferedImage emptyBar;
    private BufferedImage fill;
    
    public XPBar(double currValue, double maxValue, Handler handler) {
        super(currValue, maxValue);
        this.handler = handler;
        level = 1;
        try {
            emptyBar = ImageIO.read(new File("res/user_interface/xpBar.png")).getSubimage(0, 0, 98, 15);
            fill = ImageIO.read(new File("res/user_interface/xpBar.png")).getSubimage(98, 0, 81, 15);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public void tick() {
        this.calcValue();
        this.x = handler.getPlayer().getX() + Game.width/2 + 46;
        this.y = handler.getPlayer().getY() - Game.height/2 + 140;
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
//        g.setColor(Color.BLUE);
//        g.fillRect(x, y, (int) (w * (this.getCurrValue()/this.getMaxValue())), h);
//        g.setColor(Color.DARK_GRAY);
//        g.drawRect(x, y, w, h);
        g.drawImage(emptyBar, x, y, null);
        if ((int) (fill.getWidth() * (this.getCurrValue()/this.getMaxValue())) > 0) g.drawImage(fill.getSubimage(0, 0, (int) (fill.getWidth() * (this.getCurrValue()/this.getMaxValue())), fill.getHeight()), x + 8, y, null);
        g.setColor(Color.red);
        g.setFont(new Font("Helvetica", Font.BOLD, 16));
        g.drawString("Level " + String.valueOf(level), x + w/3, y  - 102);
    }

    public void calcValue() {
        if (handler.deadThings.size() > 0) {
            for (int i = 0; i < handler.deadThings.size(); i++) {
                Enemy deadObject = handler.deadThings.get(i);
                this.gainXP(deadObject.getXPValue());
            }
            handler.deadThings = new ArrayList<>();            
//            System.out.println("Current XP: " + this.getCurrValue() + "\nCurrent Level: " + level);
        }
    }
    
    private void gainXP(double xp) {
        this.setCurrValue(this.getCurrValue() + xp);
    } 
}
