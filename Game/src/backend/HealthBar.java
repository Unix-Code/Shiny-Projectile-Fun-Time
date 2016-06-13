package backend;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Date;
import javax.imageio.ImageIO;

/**
 *
 * @author David
 */
public class HealthBar extends Obj{
    
    private int health;
    private final int fullHealth;
    private final Character owner;
    
    // For Disappearing Constant Health Bar
    private int lastHealth;
    private long thisTime;
    private long lastTime;
    
    private final int displayTime = 3000; // 3 seconds
    
    private BufferedImage emptyBar;
    private BufferedImage fill;
    private Handler handler;
    
    public HealthBar(int health, int x, int y, int w, int h, Character owner) {
        super(x, y - 15, w, h, ID.HealthMeter);
        this.health = health;
        this.fullHealth = health;
        this.owner = owner;
        
        lastTime = (new Date()).getTime();
        
        try {
            emptyBar = ImageIO.read(new File("res/user_interface/healthBar.png")).getSubimage(0, 0, 98, 15);
            fill = ImageIO.read(new File("res/user_interface/healthBar.png")).getSubimage(98, 0, 81, 15);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    
    public void tick() {
        if (owner.getId() == ID.Player) {
            x = owner.getX() + Game.width/2 + 46;
            y = owner.getY() - Game.height/2 + 98;
        }
        else {
            x = owner.getX();
            y = owner.getY() - 15;
            lastHealth = health;
            health = Game.clamp(health, 0, 100);
            // health -= (owner.getVelX() <= 0) ? 1 : -1; // Diagnostic
            // System.out.println("Health: " + health); // Diagnostic for Health count
        }
    }
    
    public void render(Graphics g) {
        if (owner.getId() == ID.Player) {
            g.drawImage(emptyBar, x, y, null);
            if ((int)(fill.getWidth() * ((double)health/fullHealth)) > 0 /*&& (int)(fill.getWidth() * ((double)health/fullHealth)) <= fill.getWidth()*/) g.drawImage(fill.getSubimage(0, 0, (int) (fill.getWidth() * (((double)health)/fullHealth)), fill.getHeight()), x + 8, y, null);
        }
        else {
            if (health == lastHealth) {
                thisTime = (new Date()).getTime();
            }
            else {
                lastTime = thisTime;
            }

            if (thisTime - lastTime >= displayTime) {
//                return;
            }

            Graphics2D g2d = (Graphics2D)g;
            g2d.setColor(Color.GRAY);
            g2d.drawRect(x, y, w, h);
            g2d.setColor(Color.GREEN);
            g2d.fillRect(x, y, (int) (w * ((double)health/fullHealth)), h);
        }
    }
    
    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }
}

